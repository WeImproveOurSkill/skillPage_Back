package com.example.skillback.common.domain.category.controller;

import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;

import com.example.skillback.common.domain.category.dto.CategoryRequest;
import com.example.skillback.common.domain.category.dto.CategoryResponse;
import com.example.skillback.common.domain.category.entity.Category;
import com.example.skillback.common.domain.category.service.CategoryService;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryController.CATEGORY_API_URI)
public class CategoryController {

    private final CategoryService categoryService;
    public static final String CATEGORY_API_URI = "/category";

    @PostMapping("/create")
    public ResponseEntity<StatusResponse> createCategory(
        @RequestBody CategoryRequest categoryRequest, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        categoryService.createCategory(categoryRequest, userDetails.getUser());
        return RESPONSE_CREATED;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategory() {
        List<CategoryResponse> categoryResponseList = categoryService.getCategory();
        return ResponseEntity.ok().body(categoryResponseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusResponse> deleteCategory(
        @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}
