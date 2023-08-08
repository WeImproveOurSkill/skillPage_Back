package com.example.skillback.common.domain.category.dto;

import com.example.skillback.common.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryRequest {

    private Long parentId;
    private String categoryName;
    private CategoryEnum categoryEnum;

}
