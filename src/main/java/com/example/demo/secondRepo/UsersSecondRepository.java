package com.example.demo.secondRepo;

import com.example.demo.firstentity.Users;
import com.example.demo.secondentity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersSecondRepository extends JpaRepository<AppUser, Integer> {

}
