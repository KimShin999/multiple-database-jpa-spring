package com.example.demo.secondentity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "app_user")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String username;
}
