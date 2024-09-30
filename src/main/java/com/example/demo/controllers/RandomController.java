package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.time.temporal.*;
import java.util.stream.*;
import java.util.Random;
import java.util.*;
import java.lang.StringBuilder;
import java.math.BigInteger;

@RestController
// @RequestMapping("")
public class RandomController {
    
    @GetMapping("/currentTime")
    public String GetCurrentTime(){
        return LocalDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString();
    }

    @GetMapping("/api")
    public int[] GetNumbers(@RequestParam Integer q){
        return IntStream.range(1, q + 1).toArray();
    }

    @GetMapping("/random_number")
    public String GetRandomNumber(){
        return String.valueOf(new Random().nextInt(500) + 1);
    }

    @GetMapping("/fib")
    public String GetFibbonachi(@RequestParam Integer number){
        return CalculateFibbonachi(number, new HashMap<>()).toString();
    }

    private static BigInteger CalculateFibbonachi(int number, Map<Integer, BigInteger> cache){
        if(number <= 0){
            return new BigInteger("0");
        }
        if(number == 1){
            return new BigInteger("1");
        }
        BigInteger one, two;
        if(!cache.containsKey(number - 2)){
            one = CalculateFibbonachi(number - 2, cache);
            cache.put(number - 2, one);
        }
        else{
            one = cache.get(number - 2);
        }
        
        if(!cache.containsKey(number - 1)){
            two = CalculateFibbonachi(number - 1, cache);
            cache.put(number - 1, two);
        }
        else{
            two = cache.get(number - 1);
        }

        return one.add(two);
    }

    @GetMapping("/{value}")
    public String GetReversed(@PathVariable String value){
        return reverseString(value); 
    }

    private static String reverseString(String value){
        return new StringBuilder(value).reverse().toString();
    }
}
