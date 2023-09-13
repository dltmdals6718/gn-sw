package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Spot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpotRepository {

  private final EntityManager em;

  public void save(Spot spot){    //jpa가 저장하는 로직
    em.persist(spot);
  }

  public Spot findOne(Long id){
    return em.find(Spot.class, id);
  }

  public List<Spot> findAll(){  //조회된 결과를 반환
    List<Spot> result = em.createQuery("select s from Spot s", Spot.class).getResultList();
    return result;
  }   //sql은 테이블에 대해 쿼리를 하지만 여기서는 객체에 대해 쿼리를 만듦

  public List<Spot> findByName(String userName){
    return em.createQuery("select s from Spot s where s.userName = :userName", Spot.class)
        .setParameter("userName",userName).getResultList();
  }

}