package com.cms.megaprint.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "common_values")
@Data
public class CommonValue {

    @Id
    @SequenceGenerator(name = "cValuesHS", sequenceName = "c_values_id", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cValuesHS")
    private Long id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

}
