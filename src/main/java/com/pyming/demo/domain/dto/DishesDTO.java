package com.pyming.demo.domain.dto;

import com.pyming.demo.domain.enums.DishesType;
import lombok.Data;

import java.util.List;

@Data
public class DishesDTO {
    private String name;
    
    private List<String> ingredientIds;
    
    private List<String> seasoningIds;
    
    private String des;
    
    private String cookDes;
    
    private DishesType dishesType;
}
