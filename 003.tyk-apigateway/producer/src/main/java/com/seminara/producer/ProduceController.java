package com.seminara.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Random;
import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController("/")
public class ProduceController {

    private Random random = new Random();

    @GetMapping("/produce")
    public ResponseEntity<String> getString() throws UnknownHostException {
        var randomNumber = random.nextInt((100000 - 100) + 1) + 100;
        var currentDate = LocalDateTime.now();
        InetAddress inetAddress = InetAddress.getLocalHost();
        var hostname = inetAddress.getHostName();

        var result = "\n\tProduced by host: " +hostname+ "\n\tDate: " +currentDate.toString()+ "\n\tRandom data: " +randomNumber;
        return ResponseEntity.ok(result);
    }

}
