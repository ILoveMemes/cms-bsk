package com.cms.megaprint.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_category")
@Data
public class ServiceCategory {

    @Id
    @SequenceGenerator(name = "serviceCategoryHS", sequenceName = "service_category_id", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "serviceCategoryHS")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pictureId")
    private Long pictureId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    List<ServiceUnit> units;

}
