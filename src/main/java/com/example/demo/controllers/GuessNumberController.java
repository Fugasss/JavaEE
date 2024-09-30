package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
@RestController
@RequestMapping("/guess")
public class GuessNumberController {

    private Integer number;

    public GuessNumberController() {
        generateNumber();
    }

    private void generateNumber(){
        number = new Random().nextInt(100);
    }

    @GetMapping("/{value}")
    public String GuessNumber(@PathVariable Integer value){
        if(value > number){
            return "lower";
        }

        if(value < number){
            return "bigger";
        }
        
        generateNumber();

        return "You are right! The number is %d! Wanna try again?".formatted(value);
    }
}