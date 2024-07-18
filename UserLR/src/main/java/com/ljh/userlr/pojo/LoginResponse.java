package com.ljh.userlr.pojo;

import lombok.Data;


@Data
public class LoginResponse {
    private String access_token;
    private String token_type;
    private String message;


}
