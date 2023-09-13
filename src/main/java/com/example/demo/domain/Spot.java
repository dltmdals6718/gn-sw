package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Spot {
  @Id @GeneratedValue
  @Column(name = "SPOT_ID")
  private Long id;

  @OneToMany(mappedBy = "spot")
  private List<SpotReview> spotReviews = new ArrayList<>();

  @OneToMany(mappedBy = "schedule")
  private List<ScheduleReview> scheduleReviews = new ArrayList<>();

  private String userName;
  private String address;
  private String description;



}
