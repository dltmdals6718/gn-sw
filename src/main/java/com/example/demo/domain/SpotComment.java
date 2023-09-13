package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SpotComment {
  @Id @GeneratedValue
  @Column(name = "SPOT_COMMENT_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Spot spot;


}
