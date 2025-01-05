package com.ls.mp.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ls.mp.dto.UserDTO;
import com.ls.mp.dto.UserQueryDTO;
import com.ls.mp.mapper.AddressMapper;
import com.ls.mp.mapper.UserMapper;
import com.ls.mp.pojo.Address;
import com.ls.mp.pojo.User;
import com.ls.mp.service.AddressService;
import com.ls.mp.service.UserService;
import com.ls.mp.vo.PageVO;
import com.ls.mp.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("用户管理接口")
@RequestMapping(value = "/users")
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService  userService;

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        //dto -> po
        User user = BeanUtil.copyProperties(userDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        //dto -> po
        userService.removeById(id);

    }

    @ApiOperation("根据id查询用户-同时查询地址")
    @GetMapping("/{id}")
    public UserVo getUserById(@PathVariable("id") Long id) {

        return  userService.queryUserAndAddressById(id);

    }





    @ApiOperation("根据ids查询用户")
    @GetMapping()
    public List<UserVo> getUserByIds(@RequestParam("ids") List<Long> ids) {

        List<User> users = userService.listByIds(ids);
//        List<UserVo> list = new ArrayList<>();
//        // user - use vo
//        for (User user : users) {
//            UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
//            list.add(userVo);
//        }
//
//        return list;

        return  BeanUtil.copyToList(users, UserVo.class);

    }



    @ApiOperation("根据id扣减金额")
    @PutMapping("/{id}/deduction/{money}")
    public void deductionMoneyUserById(@PathVariable("id") Long id, @PathVariable("money") Integer money) {

        userService.deductionMoneyUserById(id, money);



    }


    @ApiOperation("根据复杂条件查询用户")
    @GetMapping("/list")
    public List<UserVo> queryUserByMethod(UserQueryDTO  userQueryDTO) {

        String username = userQueryDTO.getUsername();
        Integer status = userQueryDTO.getStatus();
        Integer minBalance = userQueryDTO.getMinBalance();
        Integer maxBalance = userQueryDTO.getMaxBalance();

        List<User> users = userService.queryUserByMethod(username, status, minBalance, maxBalance);


        return BeanUtil.copyToList(users, UserVo.class);
    }



    @ApiOperation("根据复杂条分页件查询用户")
    @GetMapping("/pages")
    public PageVO<UserVo> queryPagesUserByMethod(UserQueryDTO  userQueryDTO) {

        return userService.queryPagesUserByMethod(userQueryDTO);

    }




}
