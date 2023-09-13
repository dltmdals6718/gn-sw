package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SpotReview {
  @Id @GeneratedValue
  @Column(name = "SPOT_REVIEW_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Spot spot;

  private String content;
}
