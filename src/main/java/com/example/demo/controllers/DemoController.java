package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {

    private static final AtomicInteger number = new AtomicInteger();

    @GetMapping(value = "/")
    public DemoData GetTest() {
        return new DemoData("Yay", number.incrementAndGet());
    }

    @GetMapping(value = "/{name}")
    public DemoData GetOptional1(@PathVariable(required = false) String name) {
        return new DemoData("Optional 1: %s".formatted(name), -100);
    }

    @GetMapping(value = "/{name}/{age}")
    public DemoData GetInfoFromPath(@PathVariable String name, @PathVariable Integer age) {
        return new DemoData(name, age);
    }

    @GetMapping(value = "/{name}/{age}/{text}")
    public DemoData GetOptional2(@PathVariable String name, @PathVariable Integer age, @PathVariable Optional<String> text) {
        return new DemoData(name + ": " + text.orElse("no text provided"), age);
    }

}
