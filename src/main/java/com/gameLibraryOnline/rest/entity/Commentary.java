package com.gameLibraryOnline.rest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name="commentary")
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long idCommentary;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "creationDate")
    private Date creationDate;



}
