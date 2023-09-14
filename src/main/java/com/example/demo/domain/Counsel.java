package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Counsel {
  @Id @GeneratedValue
  @Column(name = "COUNSEL_ID")
  private Long id;

  private String email;

  private String context;


}
