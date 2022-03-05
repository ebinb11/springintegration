package com.springintegration.springintegration.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.messaging.Message;

@Configuration
public class SftpUploadConfig {

	@Autowired
	SftpConfig sftpConfig;

	@ServiceActivator(inputChannel = "toSftpUpload")
	public String sftpUpload(Message<?> message) {
		System.out.println("Received message from messageRequest : " + message);
		SftpMessageHandler handler = new SftpMessageHandler(sftpConfig.factory());
		handler.setRemoteDirectoryExpression(new LiteralExpression("DESTINATION_DIR"));
		handler.setFileNameGenerator(new FileNameGenerator() {
			@Override
			public String generateFileName(Message<?> message) {
				if (message.getPayload() instanceof File) {
					System.out.println("message payload sending now " + message.getPayload());
					return ((File) message.getPayload()).getName();
				} else {
					throw new IllegalArgumentException("File expected as payload.");
				}
			}
		});
		return "Uploaded";
	}
}
