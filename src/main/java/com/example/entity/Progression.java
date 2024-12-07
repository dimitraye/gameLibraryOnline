package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="PROGRESSION")
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDPROGRESSION")
    private long idProgression;

    @Column(name = "DETAILS_PROGRESSION")
    private String detailsProgression;

    @Column(name = "PERCENTAGE_COMPLETION")
    private int percentageCompletion;
}
