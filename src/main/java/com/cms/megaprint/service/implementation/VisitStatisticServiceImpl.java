package com.cms.megaprint.service.implementation;

import com.cms.megaprint.model.VisitStatistic;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.repository.intface.VisitStatisticRepository;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.service.intface.VisitStatisticService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;

@Service
public class VisitStatisticServiceImpl extends DefaultServiceImpl<VisitStatistic, Long> implements VisitStatisticService {

    private HashSet<String> uniqueIpList;
    private ZonedDateTime lastUpdate;

    public VisitStatisticServiceImpl(CrudRepo<VisitStatistic, Long> repo) {
        super(repo);
        uniqueIpList = new HashSet<>();
        lastUpdate = null;
    }


    @Override
    public void addVisitToStatistic(String ip, ZonedDateTime time) {
        time = time.truncatedTo(ChronoUnit.DAYS);
        VisitStatisticRepository visitStatisticRepository = (VisitStatisticRepository)repo;
        if (lastUpdate == null) {
            lastUpdate = time;
        } else {
            if (lastUpdate.plusDays(1).isEqual(time) || lastUpdate.plusDays(1).isBefore(time)) {
                lastUpdate = time;
                uniqueIpList.clear();
            }
        }
        uniqueIpList.add(ip);

        Optional<VisitStatistic> statOpt = visitStatisticRepository.findByDate(lastUpdate);

        if (statOpt.isEmpty()) {
            VisitStatistic stat = new VisitStatistic();
            stat.setStatTime(lastUpdate);
            stat.setAllPageLoadsCount(1L);
            stat.setUniqueUsersCount((long) uniqueIpList.size());
            visitStatisticRepository.save(stat);
        } else {
            statOpt.get().setAllPageLoadsCount(statOpt.get().getAllPageLoadsCount() + 1);
            statOpt.get().setUniqueUsersCount(statOpt.get().getUniqueUsersCount() > uniqueIpList.size() ?
                statOpt.get().getUniqueUsersCount() : (long) uniqueIpList.size());
            visitStatisticRepository.update(statOpt.get());
        }
    }
}
