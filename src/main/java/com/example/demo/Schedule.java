package com.example.demo;

import lombok.Data;

@Data
public class Schedule {
    private long id;

    private long member_id;

    private String title;

    private String f_schedule;

    private String l_schedule;

    private String description;

    private int recommended;

}
