package com.springintegration.springintegration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
public class SftpConfig {

	// set SFTP Properties here...
		public DefaultSftpSessionFactory factory() {
			DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
			factory.setHost("192.168.0.128");
			factory.setPort(22);
			factory.setAllowUnknownKeys(true);
			factory.setUser("kran");
			factory.setPassword("night123");
			return factory;
		}
}
