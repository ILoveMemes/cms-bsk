package com.cms.megaprint.configuration;

import com.cms.megaprint.model.SiteSection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.HashMap;

@ConstructorBinding
@ConfigurationProperties("mp")
@RequiredArgsConstructor
@Getter
public class VarConfig {

    private final String siteCaption;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;
    private final int port;
    private final String address;
    private final String defaultUsername;
    private final String defaultPassword;
    private final HashMap<String, SiteSection> siteSections;

}
