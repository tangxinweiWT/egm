package com.pyming.demo.domain.po;

import com.pyming.demo.domain.enums.DishesType;
import com.pyming.demo.infrastructure.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

// 菜品
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_dishes")
public class Dishes extends BasePO {
    // 名称
    private String name;
    
    // 描述
    private String des;
    
    // 烹饪说明
    private String cookDes;
    
    // 菜品类型
    @Enumerated(EnumType.STRING)
    private DishesType dishesType;
}
