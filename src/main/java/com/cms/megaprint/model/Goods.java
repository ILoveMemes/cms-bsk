package com.cms.megaprint.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Data
public class Goods {

    @Id
    @SequenceGenerator(name = "goodsHS", sequenceName = "goods_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goodsHS")
    private Long id;

    @Column(name = "imageId")
    private Long imageId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "fullDescription")
    private String fullDescription;

    @Column(name = "showOnMain")
    private boolean showOnMain;

}
