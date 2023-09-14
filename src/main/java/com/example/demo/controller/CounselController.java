package com.example.demo.controller;

import com.example.demo.DTO.CounselForm;
import com.example.demo.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CounselController {
  private final CounselService counselService;

  @GetMapping(value = "/counsels/new")
  public String createForm(Model model) {
    model.addAttribute("counselForm", new CounselForm());
    return "index";
  }


  @PostMapping(value = "/counsels/new")
  public String create(@Valid CounselForm form, BindingResult result) {
    counselService.saveCounsel(form);
    if (result.hasErrors()) {
      return "index";
    }
    return "redirect:/";
  }
}



