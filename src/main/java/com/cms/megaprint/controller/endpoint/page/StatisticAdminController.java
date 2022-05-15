package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.service.intface.VisitStatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticAdminController {

    private final VisitStatisticService visitStatisticService;

    public StatisticAdminController(VisitStatisticService visitStatisticService) {
        this.visitStatisticService = visitStatisticService;
    }

    @RequestMapping("/admin/stat")
    public String stat(Model model) {

        model.addAttribute("statistic", visitStatisticService.findAll());

        return "admin/stat";

    }
}
