package com.pyming.demo.infrastructure.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页实体对象
 */
@Data
@ApiModel
public class PageResult {
    @ApiModelProperty("当前页码")
    private int page;
    @ApiModelProperty("一共多少页")
    private int pages;
    @ApiModelProperty("一共多少条")
    private long count;
    @ApiModelProperty("每页数据量")
    private int pageSize;
    @ApiModelProperty("数据")
    List<?> items;
}
