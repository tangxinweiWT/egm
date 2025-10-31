package com.pyming.demo.domain.enums;

public enum IngredientType {
    MEAT("肉类"),
    VEGETABLE("蔬菜"),
    FRUIT("水果");

    private final String description;

    IngredientType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
