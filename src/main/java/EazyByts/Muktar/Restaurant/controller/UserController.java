package EazyByts.Muktar.Restaurant.controller;


import EazyByts.Muktar.Restaurant.model.Users;
import EazyByts.Muktar.Restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Users Register(@RequestBody Users user){

       return userService.createUser(user);
    }

    @PostMapping("/login")
    public String Login(@RequestBody Users user){
        return userService.verify(user);

    }

}
