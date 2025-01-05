package com.ls.mp.service;

import com.ls.mp.pojo.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.mp.vo.AddressVO;

/**
* @author 26611
* @description 针对表【address】的数据库操作Service
* @createDate 2025-01-04 17:22:06
*/
public interface AddressService extends IService<Address> {

    AddressVO queryAddressAndUserById(Long id);
}
