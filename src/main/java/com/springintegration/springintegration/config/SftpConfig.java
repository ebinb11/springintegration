package com.springintegration.springintegration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
public class SftpConfig {

	// set SFTP Properties here...
		public DefaultSftpSessionFactory factory() {
			DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
			factory.setHost("test.rebex.net");
			factory.setPort(22);
			factory.setAllowUnknownKeys(true);
			factory.setUser("demo");
			factory.setPassword("password");
			return factory;
		}
}
