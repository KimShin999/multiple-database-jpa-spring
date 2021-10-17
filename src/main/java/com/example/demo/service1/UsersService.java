package com.example.demo.service1;

import com.example.demo.firstentity.Users;
import com.example.demo.firstRepo.UsersRepository;
import com.example.demo.secondRepo.UsersSecondRepository;
import com.example.demo.secondentity.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Service
@AllArgsConstructor
@EnableTransactionManagement
public class UsersService {

  private final UsersRepository usersRepository;

  private final UsersSecondRepository usersSecondRepository;

  public List<Users>  findAllUser(){
    List<Users> a = usersRepository.findAll();
    return a;
  }

  public List<AppUser>  findAllUserSecond(){
    List<AppUser> a = usersSecondRepository.findAll();
    return a;
  }


}
