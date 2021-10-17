package com.example.demo.firstentity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  private String country;
}
