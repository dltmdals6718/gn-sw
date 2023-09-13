package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
  @Id @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  private String gender;
  private String email;
  private String password;
  private String userName;

  @OneToMany(mappedBy = "member")
  private List<Schedule> schedules = new ArrayList<>();


}
