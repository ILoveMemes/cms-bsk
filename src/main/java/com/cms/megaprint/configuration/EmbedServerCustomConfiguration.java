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

    /*private void refreshSiteSection(String sectionName) {
        Optional<CommonValue> vCaption;
        Optional<CommonValue> vVisible;
        boolean anyChange;

        anyChange = false;
        SiteSection siteSection = varConfig.getSiteSections().get(sectionName);
        vCaption = commonValueService.findByKey("site_section_" + sectionName + "_caption");
        if (vCaption.isPresent()) {
            siteSection.setCaption(vCaption.get().getValue());
            anyChange = true;
        }
        vVisible = commonValueService.findByKey("site_section_" + sectionName + "_visible");
        if (vVisible.isPresent()) {
            siteSection.setVisible(Boolean.parseBoolean(vVisible.get().getValue()));
            anyChange = true;
        }
        if (anyChange) {
            varConfig.getSiteSections().put(sectionName, siteSection);
        }
    }*/

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {

        // refresh configuration before server starts
        /*refreshSiteSection("home");
        refreshSiteSection("service");
        refreshSiteSection("teammate");
        refreshSiteSection("goods");
        refreshSiteSection("contact");
        refreshSiteSection("certificate");
        refreshSiteSection("carousel");*/

        factory.setPort(varConfig.getPort());
        try {
            factory.setAddress(InetAddress.getByName(varConfig.getAddress()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
