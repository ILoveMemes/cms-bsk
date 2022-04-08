package com.cms.megaprint.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "site_sections")
@Data
public class SiteSection {

    @Id
    @SequenceGenerator(name = "siteSectionsHS", sequenceName = "site_sections_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siteSectionsHS")
    private Long id;

    @Column(name = "keyId")
    private String key;

    @Column(name = "caption")
    private String caption;

    @Column(name = "visible")
    private boolean visible;

}
