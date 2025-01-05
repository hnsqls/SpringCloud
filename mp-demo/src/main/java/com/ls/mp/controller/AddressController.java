package com.ls.mp.controller;


import com.ls.mp.service.AddressService;
import com.ls.mp.vo.AddressVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("地址接口")
@RequestMapping(value = "/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    /**
     * 根据id查询地址和用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AddressVO queryAddressAndUserById(@PathVariable("id") Long id) {
        return addressService.queryAddressAndUserById(id);


    }
}
