package com.example.demo.service;

import com.example.demo.DTO.CounselForm;
import com.example.demo.domain.Counsel;
import com.example.demo.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CounselService {
  private final CounselRepository counselRepository;

  @Transactional
  public void saveCounsel(CounselForm counselForm) {
    Counsel counsel = new Counsel();
    counsel.setEmail(counselForm.getEmail());
    counsel.setContext(counselForm.getContext());
    counselRepository.save(counsel);
  }


}
