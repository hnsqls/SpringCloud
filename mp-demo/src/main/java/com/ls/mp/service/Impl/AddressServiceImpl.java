package com.ls.mp.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.ls.mp.pojo.Address;

import com.ls.mp.pojo.User;
import com.ls.mp.service.AddressService;
import com.ls.mp.mapper.AddressMapper;
import com.ls.mp.service.UserService;
import com.ls.mp.vo.AddressVO;
import com.ls.mp.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
* @author 26611
* @description 针对表【address】的数据库操作Service实现
* @createDate 2025-01-04 17:22:06
*/
@Service

public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{



    /**
     * 根据id查询地址和用户信息
     * @param id
     * @return
     */

    @Override
    public AddressVO queryAddressAndUserById(Long id) {
        Address address = getById(59);
        AddressVO addressVO = BeanUtil.copyProperties(address, AddressVO.class);

        User user = Db.lambdaQuery(User.class)
                .eq(User::getId, address.getUserId()).one();

        addressVO.setUserVO(BeanUtil.copyProperties(user, UserVo.class));

        return addressVO;
    }
}




