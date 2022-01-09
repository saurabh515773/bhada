package com.bhada.valueObject;

import lombok.Data;

@Data
public class AuthBodyVO {

    private Integer id;

    private String password;
    
    private Integer keyIndex;
}