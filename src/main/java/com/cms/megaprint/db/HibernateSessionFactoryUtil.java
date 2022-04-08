package com.cms.megaprint.db;

import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.model.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class HibernateSessionFactoryUtil {

    private final VarConfig varConfig;

    @Getter
    private SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(VarConfig varConfig) {
        this.varConfig = varConfig;
        sessionFactory = null;
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, varConfig.getDbUrl());
            properties.put(Environment.USER, varConfig.getDbUser());
            properties.put(Environment.PASS, varConfig.getDbPass());
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.POOL_SIZE, "100");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "none");

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(Picture.class);
            configuration.addAnnotatedClass(CommonValue.class);
            configuration.addAnnotatedClass(ServiceUnit.class);
            configuration.addAnnotatedClass(ServiceCategory.class);
            configuration.addAnnotatedClass(Teammate.class);
            configuration.addAnnotatedClass(TeammatesSocialNetwork.class);
            configuration.addAnnotatedClass(Goods.class);
            configuration.addAnnotatedClass(Message.class);
            configuration.addAnnotatedClass(Certificate.class);
            configuration.addAnnotatedClass(SiteSection.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            //System.out.println("[Exception!] " + e);
            e.printStackTrace();
        }
    }

}