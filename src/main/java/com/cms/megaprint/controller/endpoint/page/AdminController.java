package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private final CommonValueService commonValueService;

    public AdminController(CommonValueService commonValueService) {
        this.commonValueService = commonValueService;
    }

    // temporary path - admin2
    @RequestMapping("/admin2")
    public String admin(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), cValue.getValue());
        }

        /*model.addAttribute("site_caption", "Ремонт компьютеро в Борисоглебске");

        model.addAttribute("top_caption", "МегаПринт | Борисоглебск");
        model.addAttribute("top_phone", "8 (473 54) 6-58-88");
        model.addAttribute("top_email","megaprintbsk@mail.ru");

        model.addAttribute("center_welcome", "Добро пожаловать в");
        model.addAttribute("center_company_name", "МегаПринт");
        model.addAttribute("center_text", "Мы благодарим Вас за то, что Вы и обратились именно в наш сервисный центр, и мы гарантируем, что Вы не останетесь разочарованны нашим сервисом.");
        // три колонки в index.html

        model.addAttribute("text", "Hello, World!");*/
        return "index_admin";
    }

}
