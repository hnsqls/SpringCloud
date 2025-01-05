package com.ls.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Value;

@Getter
public enum UserEnum {
    NORMAL(1,"正常"),
    FREEZE(2,"冻结");
    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    UserEnum(Integer value, String desc) {

        this.value = value;
        this.desc = desc;
    }

}
