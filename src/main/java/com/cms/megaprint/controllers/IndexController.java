package com.cms.megaprint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        model.addAttribute("site_caption", "Ремонт компьютеро в Борисоглебске");

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
