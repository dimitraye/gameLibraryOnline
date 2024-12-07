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
@Table(name="SUCCESS")
public class Success {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDSUCCESS")
    private long idSuccess;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OWNED")
    private Boolean owned;
}
