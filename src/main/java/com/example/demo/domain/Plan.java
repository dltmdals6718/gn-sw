package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Plan {
  @Id @GeneratedValue
  @Column(name = "PLAN_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Schedule schedule;

  private Date date;
  private int dayNumber;
  private String description;


}
