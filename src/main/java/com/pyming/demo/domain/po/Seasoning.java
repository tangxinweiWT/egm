package com.pyming.demo.domain.po;

import com.pyming.demo.infrastructure.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

// 配料
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_seasoning")
public class Seasoning extends BasePO {
    private String name;
}
