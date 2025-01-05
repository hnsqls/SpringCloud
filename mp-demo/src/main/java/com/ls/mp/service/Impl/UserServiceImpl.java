package com.ls.mp.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.mp.dto.UserQueryDTO;
import com.ls.mp.enums.UserEnum;
import com.ls.mp.mapper.UserMapper;
import com.ls.mp.pojo.Address;
import com.ls.mp.pojo.User;
import com.ls.mp.service.AddressService;
import com.ls.mp.service.UserService;
import com.ls.mp.vo.AddressVO;
import com.ls.mp.vo.PageVO;
import com.ls.mp.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

    private  final UserMapper userMapper;
    private  final AddressService addressService;

    /**
     *  根据id 和money 扣减
     * @param id
     * @param money
     */
    @Override
    public void deductionMoneyUserById(Long id, Integer money) {
        User user = getById(id);
        if (user == null || user.getStatus() != UserEnum.NORMAL) {
            throw  new RuntimeException("用户不存在");
        }
        if (user.getBalance() < money) {
            throw  new RuntimeException("余额不足");
        }
        lambdaUpdate().eq(User::getId,id)
                .set(User::getBalance,user.getBalance()-money)
                .update();

         user = getById(id);
         //如果扣减后为0 就冻结
         if (user.getBalance()  == 0) {
             lambdaUpdate().eq(User::getId,id)
                     .set(User::getStatus,2)
                     .update();
         }
//        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(User::getId,id);
//        userMapper.updatemoneyById(updateWrapper,money);

    }

    /**
     * 根据条件查询用户信息
     * @param username
     * @param status
     * @param minBalance
     * @param maxBalance
     * @return
     */
    @Override
    public List<User> queryUserByMethod(String username, Integer status, Integer minBalance, Integer maxBalance) {

        return  lambdaQuery().like(username != null, User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();
    }

    /**
     * 根据id 查用户以及地址
     * @param id
     * @return
     */
    @Override
    public UserVo queryUserAndAddressById(Long id) {
        User user = getById(id);
        if (user == null || user.getStatus() == UserEnum.FREEZE) {
            throw  new RuntimeException("用户不存在");
        }
        UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);

        List<Address> list = addressService.lambdaQuery().eq(Address::getUserId, id).list();
        if (list != null || list.size() != 0) {
            userVo.setAddressList(BeanUtil.copyToList(list, AddressVO.class));
        }

        return userVo;
    }


    /**
     * 分页查询
     *
     * @param
     * @return
     */
    @Override
    public PageVO<UserVo> queryPagesUserByMethod(UserQueryDTO userQueryDTO) {
        String username = userQueryDTO.getUsername();
        Integer status = userQueryDTO.getStatus();
        Long pageNo = userQueryDTO.getPageNo();
        Long pageSize = userQueryDTO.getPageSize();
        String sortBy = userQueryDTO.getSortBy();
        Boolean isAsc = userQueryDTO.getIsAsc();

        //构造分页
        Page<User> page = Page.of(pageNo, pageSize);
        //排序条件
        if (sortBy != null) {
            page.addOrder(new OrderItem().setColumn(sortBy).setAsc(isAsc));
        }else {
            //默认排序 降序
            page.addOrder(new OrderItem().setColumn("create_time").setAsc(false));
        }
        //构造查询条件
        page = lambdaQuery().like(username != null, User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .page(page);


        //获取所需结果
        List<User> records = page.getRecords();

        Long total = page.getTotal();
        Long pages = page.getPages();

        //封装结果
        List<UserVo> userVos = BeanUtil.copyToList(records, UserVo.class);
        PageVO<UserVo> userVoPageVO = new PageVO<>();
        userVoPageVO.setTotal(total);
        userVoPageVO.setPages(pages);
        userVoPageVO.setData(userVos);

        return userVoPageVO;
    }

}