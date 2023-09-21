package com.example.relations.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String day;
    private Integer startTime;
    private Integer endTime;

    @ManyToOne
//    @JoinColumn(name = " ") //
    private Instructor instructor;

    @ManyToMany(mappedBy = "attending")
    private List<Student> students;
}
