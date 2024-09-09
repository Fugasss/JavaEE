package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {
    
    private static AtomicInteger number = new AtomicInteger();

    @GetMapping
    public Test GetTest(){
        return new Test("Yay", number.incrementAndGet());
    }
}
