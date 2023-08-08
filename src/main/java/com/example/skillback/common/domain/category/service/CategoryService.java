package com.example.skillback.common.domain.category.service;

import com.example.skillback.common.domain.category.dto.CategoryRequest;
import com.example.skillback.common.domain.category.dto.CategoryResponse;
import com.example.skillback.common.domain.user.entity.User;
import java.util.List;

public interface CategoryService {

    void createCategory(CategoryRequest categoryRequest, User user);

    List<CategoryResponse> getCategory();

    void deleteCategory(Long categoryId, User user);
}
