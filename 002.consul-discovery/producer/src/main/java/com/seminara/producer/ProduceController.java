package com.seminara.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Random;


@RestController("/")
public class ProduceController {

    private Random random = new Random();

    @GetMapping("/produce")
    public ResponseEntity<String> getString(){
        var randomNumber = random.nextInt((100000 - 100) + 1) + 100;
        var currentDate = LocalDateTime.now();
        return ResponseEntity.ok(currentDate.toString() + "   ---   " + randomNumber);
    }

}
