package com.adnanarch.loginapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author: Adnan Rafique
 * Date: 5/8/2024
 * Time: 9:35 AM
 * Version: 1.0
 */

@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private String department;
    private String program;
    private String programTime;
    private Boolean isSubmitted;
    private Date submissionDate;
    private Boolean feeSubmitted;
}

