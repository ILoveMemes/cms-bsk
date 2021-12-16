package com.cms.megaprint.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("mp")
@RequiredArgsConstructor
@Getter
public class VarConfig {

    private final String siteCaption;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;


}
