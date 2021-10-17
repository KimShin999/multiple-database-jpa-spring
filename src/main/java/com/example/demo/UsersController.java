package com.example.demo;

import com.example.demo.firstentity.Users;
import com.example.demo.secondentity.AppUser;
import com.example.demo.service1.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {

  private final UsersService usersService;

  @GetMapping("/test")
  private List<Users>  findAllUsers1(){
    return usersService.findAllUser();
  }

  @GetMapping("/test/second")
  private List<AppUser>  findAllUserSecond(){
    return usersService.findAllUserSecond();
  }
}
