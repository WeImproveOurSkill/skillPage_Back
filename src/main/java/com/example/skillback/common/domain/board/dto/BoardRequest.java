package com.example.skillback.common.domain.board.dto;

import com.example.skillback.common.enums.BoardEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardRequest {

    private BoardEnum category;
    private String content;
}
