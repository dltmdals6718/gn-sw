package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

  private final EntityManager em;

  public void save(Schedule schedule){
    if(schedule.getId() == null){
      em.persist(schedule);
    } else{
      em.merge(schedule);
    }
  }

  public Schedule findOne(Long id)  {
    return em.find(Schedule.class, id);
  }

  public List<Schedule> findAll() {
  return em.createQuery("select s from Schedule s", Schedule.class).getResultList();
  }

  public List<Schedule> findByName(Schedule userName){
    return em.createQuery("select s from Schedule s where s.userName = :userName", Schedule.class)
        .setParameter("userName",userName).getResultList();
  }
}
