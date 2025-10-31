package com.pyming.demo.infrastructure.common.page;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
public class PageInfo {
    @ApiModelProperty(value = "当前页码", example = "1")
    private Integer page = 1;
    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;
    public Pageable of() {
        return PageRequest.of(this.page - 1, this.pageSize);
    }

    public Pageable of(Sort sort) {
        return PageRequest.of(this.page - 1, this.pageSize, sort);
    }

    /**
     * 默认按时间排序
     *
     * @return
     */
    public Pageable ofSort() {
        return PageRequest.of(this.page - 1, this.pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
