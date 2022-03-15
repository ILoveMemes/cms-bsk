package com.cms.megaprint.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "teammates_social_network")
@Data
public class TeammatesSocialNetwork {

    @Id
    @SequenceGenerator(name = "teammatesSocialNetworkHS", sequenceName = "teammates_social_network_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teammatesSocialNetworkHS")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teammateId")
    @JsonBackReference
    private Teammate teammate;

    @Column(name = "pictureId")
    private Long pictureId;

    @Column(name = "link")
    private String link;

}
