package com.example.demo3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaOrderDto implements Serializable {
    private Schema schema; // header의 느낌
    private Payload payload; // 진짜로 보낼 몸체
}
