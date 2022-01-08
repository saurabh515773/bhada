package com.bhada.valueObject;

import lombok.Data;

@Data
public class AuthBodyVO {

    private String id;

    private String password;
    
    private String keyIndex;
}