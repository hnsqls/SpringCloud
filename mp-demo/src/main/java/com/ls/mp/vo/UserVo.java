package com.ls.mp.vo;

import com.ls.mp.enums.UserEnum;
import com.ls.mp.pojo.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@ApiModel(description = "用户VO信息")
@Data
public class UserVo {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;


    @ApiModelProperty("详细信息")
    private UserInfo info;

    @ApiModelProperty("使用状态（1正常 2冻结）")
    private UserEnum status;

    @ApiModelProperty("账户余额")
    private Integer balance;

    /**
     * 地址
     */
    private List<AddressVO> addressList;


}
