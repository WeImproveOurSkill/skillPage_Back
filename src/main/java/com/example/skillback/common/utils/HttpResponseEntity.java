package com.example.skillback.common.utils;

import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.enums.ResponseMessages;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponseEntity {

    public static final ResponseEntity<StatusResponse> RESPONSE_OK =
        ResponseEntity.status(HttpStatus.OK).body(
            StatusResponse.valueOf(ResponseMessages.SUCCESS));

    public static final ResponseEntity<StatusResponse> RESPONSE_CREATED =
        ResponseEntity.status(HttpStatus.CREATED)
            .build();

    public static final ResponseEntity<StatusResponse> RESPONSE_UPDATE =
        ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED)
            .build();

    public static final ResponseEntity<StatusResponse> RESPONSE_DELETED =
        ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build();


}
