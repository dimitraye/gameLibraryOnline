package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="success")
public class Success {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long idSuccess;

    @Column(name = "description")
    private String description;

    @Column(name = "owned")
    private Boolean owned;
}
