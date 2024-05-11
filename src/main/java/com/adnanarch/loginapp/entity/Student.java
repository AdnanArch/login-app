package com.adnanarch.loginapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Author: Adnan Rafique
 * Date: 5/8/2024
 * Time: 9:33 AM
 * Version: 1.0
 */


@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String email;
    private String password;
    private String name;
    private String fathersName;
    private String cnic;
    private String nationality;
    private String domicile;
    private String religion;
    private String gender;
    private String bloodGroup;
    private Date dateOfBirth;
    private String fathersOccupation;
    private Double fathersMonthlyIncome;
    private String address;
    private String telephoneNumber;
    private String cellNumber;
    private String emergencyContactName;
    private String emergencyContactAddress;
    private String emergencyContactTelephone;
    private String emergencyContactCellNumber;
    private String placeOfStayDuringStudies;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AcademicRecord> academicRecords;
}

