package com.pyming.demo.domain.po;

import com.pyming.demo.infrastructure.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_dishes_rel")
public class DishesRel extends BasePO {
    private String dishesId;
    
    private String ingredientJSONInfo;
    
    private String seasoningJSONInfo;
}
