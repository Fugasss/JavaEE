package com.example.demo.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final List<User> users;

    public UsersController() {
        users = new ArrayList<User>();
    }

    @GetMapping("/")
    public List<User> GetAllUsers(@RequestParam Integer page, @RequestParam Integer size){
        if(page <= 0 || size <= 0){
            return new ArrayList<User>();
        }

        if((page - 1)*size > users.size()){
            return new ArrayList<User>();
        }

        List<User> filteredUsers = new ArrayList<>(users);
        filteredUsers.sort((o1, o2) -> o1.name.compareTo(o2.name));

        if(size >= filteredUsers.size()){
            return filteredUsers;
        }

        if(page * size >= filteredUsers.size() - 1){
            return filteredUsers.subList((page - 1) * size, filteredUsers.size());
        }
        
        return filteredUsers.subList((page - 1) * size,  page * size);
    }

    @GetMapping(value = "/{id}")
    public String GetUserById(@PathVariable Integer id) {
        var user = users.stream().filter(x->x.id==id).findFirst();
        if(user.isPresent()){
            return "User with ID %d found!".formatted(id);
        }

        return "User with ID %d not found!".formatted(id);
    }

    @PostMapping("/")
    public String CreateUser(@RequestBody User user){

        if(user.name.isBlank()){
            return "User's name cannot be empty!";
        }

        if(user.age < 18){
            return "User's age less than 18!";
        }

        user.id = users.size() + 1;
        
        users.add(user);

        return "ok";
    }

    @PutMapping("/{id}")
    public String UpdateUser(@PathVariable Integer id, @RequestBody User newUserInfo){
        var user = users.stream().filter(x->x.id==id).findFirst();
        
        if(user.isEmpty()){
            return "User with ID %d not found!".formatted(id);
        }

        if(newUserInfo.name.isBlank()){
            return "User's name cannot be empty!";
        }

        if(newUserInfo.age < 18){
            return "User's age less than 18!";
        }
        
        User existingUser = user.get();

        existingUser.age = newUserInfo.age;
        existingUser.name = newUserInfo.name;
        
        return "User with ID %d updated!".formatted(id);
    }
}
