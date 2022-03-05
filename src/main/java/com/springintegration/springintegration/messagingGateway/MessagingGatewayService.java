package com.springintegration.springintegration.messagingGateway;

import java.io.File;
import java.io.StringWriter;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway
public interface MessagingGatewayService {

	@Gateway(requestChannel = "sendMessage")
	public String send(String message);

	@Gateway(requestChannel = "convertToXml")
	public StringWriter convertToXml(StringWriter sw);

	@Gateway(requestChannel = "toSftpUpload")
	String upload(File file);

}
