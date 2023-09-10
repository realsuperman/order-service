package com.example.demo3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field { // DB 컬럼의 느낌
    private String type;
    private boolean optional;
    private String field;
}