package com.pyming.demo.domain.po;

import com.pyming.demo.domain.enums.IngredientType;
import com.pyming.demo.infrastructure.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

// 食材
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ingredient")
public class Ingredient extends BasePO {
    private String name;
    
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;
}
