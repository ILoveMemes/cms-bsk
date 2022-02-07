package com.cms.megaprint.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service_unit")
@Data
public class ServiceUnit {

    @Id
    @SequenceGenerator(name = "serviceUnitHS", sequenceName = "service_unit_id", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "serviceUnitHS")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "showOnMain")
    private boolean showOnMain;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ServiceCategory category;

}
