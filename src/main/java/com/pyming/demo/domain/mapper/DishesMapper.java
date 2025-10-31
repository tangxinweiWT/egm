package com.pyming.demo.domain.mapper;

import com.pyming.demo.domain.dto.DishesDTO;
import com.pyming.demo.domain.po.Dishes;
import org.mapstruct.Mapper;

@Mapper
public interface DishesMapper {
    Dishes toPO(DishesDTO dto);
}
