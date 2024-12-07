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
@Table(name="COMMENTARY")
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDCOMMENTARY")
    private long idCommentary;

    @Column(name = "COMMENTARY")
    private String commentary;

    @Column(name = "CREATION_DATE")
    private Date creationDate;
}
