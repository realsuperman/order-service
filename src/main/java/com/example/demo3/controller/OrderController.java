package com.example.demo3.controller;

import com.example.demo3.dto.OrderDto;
import com.example.demo3.jpa.OrderEntity;
import com.example.demo3.messagequeue.KafkaProducer;
import com.example.demo3.messagequeue.OrderProducer;
import com.example.demo3.service.OrderService;
import com.example.demo3.vo.RequestOrder;
import com.example.demo3.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;


    @GetMapping("/health_check")
    public String status(){
        return String.format("It's Working in Order Service on PORT %s"
                ,env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetails){
        log.info("Before add orders data");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetails,OrderDto.class);
        orderDto.setUserId(userId);

        OrderDto createOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = mapper.map(createOrder,ResponseOrder.class);

//        orderDto.setOrderId(UUID.randomUUID().toString());
//        orderDto.setTotalPrice(orderDetails.getQty()*orderDetails.getUnitPrice());
//
//        kafkaProducer.send("example-catalog-topic", orderDto); // 카프카에 데이터 전달
//        orderProducer.send("orders",orderDto);

//        ResponseOrder responseOrder = mapper.map(orderDto,ResponseOrder.class);
        log.info("After add orders data");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId){
        log.info("Before retrieve orders data");
        Iterable<OrderEntity> orderList = orderService.getOrderByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v->{
            result.add(new ModelMapper().map(v,ResponseOrder.class));
        });
        log.info("After retrieve orders data");

//        try{
//            Thread.sleep(1000);
//            if(1==1) throw new RuntimeException("장애 발생");
//        }catch(InterruptedException e){
//            log.warn(e.getMessage());
//        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
