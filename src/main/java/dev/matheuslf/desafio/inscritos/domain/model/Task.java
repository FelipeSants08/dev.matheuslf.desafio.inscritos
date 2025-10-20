package dev.matheuslf.desafio.inscritos.domain.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
