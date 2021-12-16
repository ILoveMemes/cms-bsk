package com.cms.megaprint.controllers;

import com.cms.megaprint.configuration.VarConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final VarConfig varConfig;

    public IndexController(VarConfig varConfig) {
        this.varConfig = varConfig;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        model.addAttribute("site_caption", varConfig.getSiteCaption());

        model.addAttribute("top_caption", "МегаПринт | Борисоглебск");
        model.addAttribute("top_phone", "8 (473 54) 6-58-88");
        model.addAttribute("top_email","megaprintbsk@mail.ru");

        model.addAttribute("center_welcome", "Добро пожаловать в");
        model.addAttribute("center_company_name", "МегаПринт");
        model.addAttribute("center_text", "Мы благодарим Вас за то, что Вы и обратились именно в наш сервисный центр, и мы гарантируем, что Вы не останетесь разочарованны нашим сервисом.");
        // три колонки в index.html

        model.addAttribute("text", "Hello, World!");
        return "index";
    }

}
