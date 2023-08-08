package com.example.skillback.common.domain.category.dto;

import com.example.skillback.common.domain.category.entity.Category;

public class CategoryResponse {

    private String parentCategory;
    private String categoryName;

    public CategoryResponse(Category category) {
        this.categoryName = category.getName();
        this.parentCategory = category.getParent().getName();
    }
}
