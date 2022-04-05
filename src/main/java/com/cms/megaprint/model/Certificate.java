package com.cms.megaprint.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {

    @Id
    @SequenceGenerator(name = "certificatesHS", sequenceName = "certificates_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificatesHS")
    private Long id;

    @Column(name = "pictureId")
    private Long pictureId;

    @Column(name = "text")
    private String text;

}
