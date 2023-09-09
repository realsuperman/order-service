package com.example.demo3.messagequeue;

import com.example.demo3.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderDto send(String topic, OrderDto orderDto){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try{
            jsonInString = mapper.writeValueAsString(orderDto);
        }catch(JsonProcessingException ex){
            throw new RuntimeException();
        }

        kafkaTemplate.send(topic, jsonInString); // 카프카에게 토픽과 메시지 전달
        log.info("Kafka Producer sent data from the Order microservice: {} ",orderDto);
        return orderDto;

    }
}
