package com.example.demo.repository;

import com.example.demo.domain.Counsel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CounselRepository {

  private final EntityManager em;

  public void save(Counsel counsel){
    em.persist(counsel);
  }
}
