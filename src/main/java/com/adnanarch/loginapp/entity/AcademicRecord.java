package com.adnanarch.loginapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Adnan Rafique
 * Date: 5/8/2024
 * Time: 9:36 AM
 * Version: 1.0
 */

@Getter
@Setter
@Entity
@Table(name = "academic_records")
public class AcademicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private String examination;
    private String year;
    private String boardUniversity;
    private String rollNumber;
    private Integer marksObtained;
    private Integer maximumMarks;
    private String divisionGrade;
}
