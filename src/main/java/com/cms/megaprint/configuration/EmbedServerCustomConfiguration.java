package com.cms.megaprint.configuration;


import org.springframework.stereotype.Component;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class EmbedServerCustomConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private final VarConfig varConfig;

    public EmbedServerCustomConfiguration(VarConfig varConfig) {
        this.varConfig = varConfig;
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(varConfig.getPort());
        try {
            factory.setAddress(InetAddress.getByName(varConfig.getAddress()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
