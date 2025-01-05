package com.ls.mp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ls.mp.enums.UserEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(autoResultMap = true)
public class User {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 详细信息
     */
//    private String info;

    /**
     * 详细信息
     */

    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;


    /**
     * 使用状态（1正常 2冻结）
     */
    private UserEnum status;

    /**
     * 账户余额
     */
    private Integer balance;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}