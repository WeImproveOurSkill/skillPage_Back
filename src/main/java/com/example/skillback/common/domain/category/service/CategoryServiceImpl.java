package com.example.skillback.common.domain.category.service;

import com.example.skillback.common.domain.category.dto.CategoryRequest;
import com.example.skillback.common.domain.category.dto.CategoryResponse;
import com.example.skillback.common.domain.category.entity.Category;
import com.example.skillback.common.domain.category.repository.CategoryRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createCategory(CategoryRequest categoryRequest, User user) {

        Category category = Category.builder()
            .categoryEnum(categoryRequest.getCategoryEnum())
            .name(categoryRequest.getCategoryName())
            .parent(categoryRepository.findById(categoryRequest.getParentId()).orElse(null))
            .build();

        categoryRepository.save(category);

    }

    @Override
    @Transactional
    public List<CategoryResponse> getCategory() {
        List<Category> categories = categoryRepository.findAllOrderByParentIdAscNullsFirstCategoryIdAsc();
        return categories.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId, User user) {
        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지않습니다"));
    }
}
