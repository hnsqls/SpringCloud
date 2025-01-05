package com.ls.mp.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("分页通用")
@Data
public class PageDTO {
    private Long pageNo = 1L;
    private Long pageSize = 10L;
    private String sortBy;
    private Boolean isAsc = false;
}
