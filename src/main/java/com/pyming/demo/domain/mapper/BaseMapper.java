package com.pyming.demo.domain.mapper;

import com.pyming.demo.domain.dto.BaseJson;
import com.pyming.demo.domain.po.Ingredient;
import com.pyming.demo.domain.po.Seasoning;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BaseMapper {
    List<BaseJson> toBaseJsonByIngredient(List<Ingredient> po);

    List<BaseJson> toBaseJsonBySeasoning(List<Seasoning> po);
}
