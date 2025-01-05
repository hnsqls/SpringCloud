package com.ls.mp.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@ApiModel("分页通用VO")
@Data
public class PageVO <T> {
    private Long total;
    private Long pages;
    private List<T> data;



}
