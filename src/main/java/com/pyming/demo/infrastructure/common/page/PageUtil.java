package com.pyming.demo.infrastructure.common.page;

import org.springframework.data.domain.Page;

/**
 * 分页工具类
 */
public class PageUtil {
    public static PageResult toPage(Page<?> page) {
        PageResult pageResult = new PageResult();
        pageResult.setPages(page.getTotalPages());
        pageResult.setCount(page.getTotalElements());
        pageResult.setItems(page.getContent());
        pageResult.setPageSize(page.getSize());
        pageResult.setPage(page.getNumber() + 1);
        return pageResult;
    }
}
