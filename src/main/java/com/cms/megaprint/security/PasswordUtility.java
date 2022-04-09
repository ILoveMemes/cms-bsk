package com.cms.megaprint.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordUtility {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String crypt(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
