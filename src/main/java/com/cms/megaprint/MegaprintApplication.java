package com.cms.megaprint;

import com.cms.megaprint.configuration.VarConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VarConfig.class)
public class MegaprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaprintApplication.class, args);
	}

}
