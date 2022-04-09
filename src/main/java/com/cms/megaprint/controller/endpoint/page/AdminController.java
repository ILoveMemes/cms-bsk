package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.exception.AuthenticationException;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.model.PasswordChangeDto;
import com.cms.megaprint.model.User;
import com.cms.megaprint.security.PasswordUtility;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.MessageService;
import com.cms.megaprint.service.intface.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private final CommonValueService commonValueService;
    private final MessageService messageService;
    private final PasswordUtility passwordUtility;
    private final UserService userService;

    public AdminController(CommonValueService commonValueService, MessageService messageService, PasswordUtility passwordUtility, UserService userService) {
        this.commonValueService = commonValueService;
        this.messageService = messageService;
        this.passwordUtility = passwordUtility;
        this.userService = userService;
    }

    @RequestMapping("/admin")
    public String admin(Model model, Authentication authentication) {

        PasswordChangeDto passwordChangeDto = new PasswordChangeDto();
        passwordChangeDto.setNewUname(authentication.getName());

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), cValue.getValue());
        }
        model.addAttribute("unreadMessageCount", messageService.getUnreadMessageCount());
        model.addAttribute("passwordChangeDto", passwordChangeDto);

        return "admin/index";
    }

    @RequestMapping("/admin/change_password")
    public String changePassword(Model model, Authentication authentication, @ModelAttribute PasswordChangeDto passwordChangeDto) {

        User user;
        try {
           user = userService.findByUsername(authentication.getName()).orElseThrow(AuthenticationException::new);
        } catch (AuthenticationException ex) {
            model.addAttribute("errorMsg", "Ошибка аутентификации");
            return "admin/change_pass_result";
        }
        if (passwordUtility.doPasswordsMatch(passwordChangeDto.getOldPass(), user.getPassword())) {
            if (!passwordChangeDto.getNewUname().equals("")) {
                if (passwordChangeDto.getNewPass().equals(passwordChangeDto.getNewPassConf())) {
                    user.setUsername(passwordChangeDto.getNewUname());
                    user.setPassword(passwordUtility.crypt(passwordChangeDto.getNewPass()));
                    userService.update(user);
                } else {
                    model.addAttribute("errorMsg", "Новые пароли не совпадают");
                }
            } else {
                model.addAttribute("errorMsg", "Имя пользователя не может быть пустым");
            }
        } else {
            model.addAttribute("errorMsg", "Введен неверный пароль");
        }

        return "admin/change_pass_result";
    }

}
