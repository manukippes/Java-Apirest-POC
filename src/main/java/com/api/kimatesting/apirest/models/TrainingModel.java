package com.api.kimatesting.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "training")
public class TrainingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training", nullable = false)
    private Long trainingId;
    @Column(unique = true)
    private String name;
    private String icon;
}
