package com.cms.megaprint.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "visit_statistic")
@Data
public class VisitStatistic {

    @Id
    @SequenceGenerator(name = "visitStatisticHS", sequenceName = "visit_statistic_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitStatisticHS")
    private Long id;

    @Column(name = "statTime")
    private ZonedDateTime statTime;

    @Column(name = "allPageLoadsCount")
    private Long allPageLoadsCount;

    @Column(name = "uniqueUsersCount")
    private Long uniqueUsersCount;

}
