package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@RestController
public class GreetingController {

    @GetMapping(value = "/greet/")
    public String GetGreetName(@RequestParam(required = false, defaultValue = "") String name) {
        return "Hello %s".formatted(name);
    }

    @PostMapping(value = "/book/",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Book PostBook(@RequestBody Book request){
        request.received = true;
        return request;
    }
}
