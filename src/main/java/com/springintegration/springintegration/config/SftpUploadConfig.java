package com.springintegration.springintegration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;

@Configuration
public class SftpUploadConfig {

	@Autowired
	SftpConfig sftpConfig;

	@Bean
	@ServiceActivator(inputChannel = "toSftpUpload")
	public SftpMessageHandler sftpUpload() {
		SftpMessageHandler handler = new SftpMessageHandler(sftpConfig.factory());
		handler.setRemoteDirectoryExpression(new LiteralExpression("/home/ebinb11/Downloads"));
		return handler;
	}
}
