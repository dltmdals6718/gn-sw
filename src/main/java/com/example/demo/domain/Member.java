package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
public class Member {

    @Id
    private Long id;
    private String nickname;
    private String email;

}
