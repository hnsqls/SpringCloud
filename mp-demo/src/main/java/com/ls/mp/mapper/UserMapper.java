package com.ls.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ls.mp.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    @Update("update user set balance = balance - #{money} ${ew.customSqlSegment}")
    void updatemoneyById(@Param("ew") LambdaUpdateWrapper<User> updateWrapper,@Param("money") Integer money);
}
