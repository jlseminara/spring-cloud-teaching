package com.seminara.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
public class ConsumerController {

    private final ProducerFeignClient producerFeignClient;

    @Autowired
    public ConsumerController(ProducerFeignClient producerFeignClient) {
        this.producerFeignClient = producerFeignClient;
    }

    @GetMapping("/consume")
    public ResponseEntity<String> geMapping() {
        var dataObtained = producerFeignClient.getData();
        return ResponseEntity.ok("Data from producer: " + dataObtained);
    }

}
