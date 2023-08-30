package com.example.skillback.fixture;

import com.example.skillback.common.dtos.PageDto;

public class UtilFixture {

    public static final PageDto PAGE_DTO = PageDto.builder()
        .page(1)
        .isAsc(true)
        .keyword("key")
        .build();

}
