package com.pyming.demo.repository;

import com.pyming.demo.domain.po.Ingredient;
import com.pyming.demo.infrastructure.common.base.BaseRepository;
import org.eclipse.jdt.internal.compiler.ast.LabeledStatement;

import java.util.List;

public interface IngredientRepository extends BaseRepository<Ingredient> {
    List<Ingredient> findBYIdIN(List<String> ids);
}