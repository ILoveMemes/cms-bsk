package com.cms.megaprint.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@Data
public class Picture {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "picturesHS", sequenceName = "pictures_id", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "picturesHS")
    private int id;

    @Column(name = "data")
    @Lob
    private byte[] data;

    @Column(name = "name")
    private String name;

}
