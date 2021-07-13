package com.rookies.ecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private String errorCode;
    private Object data;
    private String succesCode;
}
