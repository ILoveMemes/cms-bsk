package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.VisitStatistic;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface VisitStatisticRepository extends CrudRepo<VisitStatistic, Long> {

    Optional<VisitStatistic> findByDate(ZonedDateTime dateTime);

}
