package com.rookies.ecommerceapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private LocalDateTime time = LocalDateTime.now();
    private String errorCode;
    private Object data;
    private String successCode;

    public ResponseDto(String errorCode, Object data, String successCode) {
        this.errorCode = errorCode;
        this.data = data;
        this.successCode = successCode;
    }

}
