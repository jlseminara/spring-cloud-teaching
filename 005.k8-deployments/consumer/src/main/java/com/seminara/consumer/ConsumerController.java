package com.seminara.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Random;
import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController("/")
public class ConsumerController {

    private Random random = new Random();
    private final ProducerFeignClient producerFeignClient;

    @Autowired
    public ConsumerController(ProducerFeignClient producerFeignClient) {
        this.producerFeignClient = producerFeignClient;
    }

    @GetMapping("/consume")
    public ResponseEntity<String> geMapping() throws UnknownHostException {
        var randomNumber = random.nextInt((100000 - 100) + 1) + 100;
        var currentDate = LocalDateTime.now();
        InetAddress inetAddress = InetAddress.getLocalHost();
        var hostname = inetAddress.getHostName();

        var dataObtained = producerFeignClient.getData();
        var result = "Consumer in host: " +hostname+ "\nDate: " +currentDate.toString()+
                "\nRandom data generated: " +randomNumber + "\nData received: \n" + dataObtained;

        return ResponseEntity.ok(result);
    }

}
