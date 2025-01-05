package com.ls.mp.service;

import com.baomidou.mybatisplus.extension.conditions.query.ChainQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.mp.dto.UserQueryDTO;
import com.ls.mp.pojo.User;
import com.ls.mp.vo.PageVO;
import com.ls.mp.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<User> {
    void deductionMoneyUserById(Long id, Integer money);

    List<User> queryUserByMethod(String username, Integer status, Integer minBalance, Integer maxBalance);

    UserVo queryUserAndAddressById(Long id);


    PageVO<UserVo> queryPagesUserByMethod(UserQueryDTO userQueryDTO);
}
