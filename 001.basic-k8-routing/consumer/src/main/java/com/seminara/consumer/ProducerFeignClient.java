package com.seminara.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@FeignClient(name = "ProducerClient", url = "${producer.url}")
public interface ProducerFeignClient {

    @GetMapping(value = "/produce")
    String getData();
}
