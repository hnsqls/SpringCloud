package com.ls.mp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户多条件查询DTO")
@Data
public class UserQueryDTO extends PageDTO{

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("最小账户余额")
    private Integer minBalance;
    @ApiModelProperty("最大账户余额")
    private Integer maxBalance;
}
