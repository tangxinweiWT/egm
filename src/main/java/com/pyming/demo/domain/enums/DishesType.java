package com.pyming.demo.domain.enums;

public enum DishesType {
    MEAT("荤菜"),
    VEGETABLE("素菜"),
    SOUP("汤类"),
    DRINK("饮品");

    private final String description;

    DishesType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
