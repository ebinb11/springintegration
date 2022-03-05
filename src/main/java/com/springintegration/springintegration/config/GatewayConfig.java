package com.springintegration.springintegration.config;

import java.io.StringWriter;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;

@Configuration
public class GatewayConfig {

	@ServiceActivator(inputChannel = "sendMessage")
	public String toUpper(String str) {
		System.out.println("Received message from messageRequest : " + str);
		return str.toUpperCase();
	}

	@ServiceActivator(inputChannel = "convertToXml")
	public StringWriter convertToXml(StringWriter str) {
		System.out.println("Received message from messageRequest : " + str);
		return str;
	}
}
