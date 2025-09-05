package com.seminara.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@FeignClient(name = "producer", url = "http://tyk-service:8080/producer")
public interface ProducerFeignClient {
    @GetMapping("/produce")
    String getData();
}
