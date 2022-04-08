package com.cms.megaprint.configuration;


import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@Component
public class EmbedServerCustomConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private final VarConfig varConfig;
    private final CommonValueService commonValueService;

    public EmbedServerCustomConfiguration(VarConfig varConfig, CommonValueService commonValueService) {
        this.varConfig = varConfig;
        this.commonValueService = commonValueService;
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
