package com.cms.megaprint.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teammates")
@Data
public class Teammate {

    @Id
    @SequenceGenerator(name = "teammatesHS", sequenceName = "teammates_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teammatesHS")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photoId")
    private Long photoId;

    @Column(name = "position")
    private String position;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teammate")
    List<TeammatesSocialNetwork> socialNetworks;

}
