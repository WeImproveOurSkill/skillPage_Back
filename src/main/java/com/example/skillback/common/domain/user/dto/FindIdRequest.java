package com.example.skillback.common.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindIdRequest {

    private String username;
    private String phoneNumber;

}
