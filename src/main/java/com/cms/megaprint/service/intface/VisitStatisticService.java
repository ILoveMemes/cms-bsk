package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.VisitStatistic;
import com.cms.megaprint.service.common.CrudService;

import java.time.ZonedDateTime;

public interface VisitStatisticService extends CrudService<VisitStatistic, Long> {

    void addVisitToStatistic(String ip, ZonedDateTime time);

}
