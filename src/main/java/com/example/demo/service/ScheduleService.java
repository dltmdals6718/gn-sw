package com.example.demo.service;

import com.example.demo.domain.Schedule;
import com.example.demo.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
  private final ScheduleRepository scheduleRepository;

  @Transactional
  public void saveSchedule(Schedule schedule){
    scheduleRepository.save(schedule);
  }


}