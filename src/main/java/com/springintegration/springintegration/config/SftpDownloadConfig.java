package com.springintegration.springintegration.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
@Component
@Slf4j
public class SftpDownloadConfig {

	@Autowired
	SftpConfig stConfig;

	// Fetch DATA From Source Directory...
	@Bean()
	public SftpInboundFileSynchronizer synchronizer() {
		SftpInboundFileSynchronizer sync = new SftpInboundFileSynchronizer(stConfig.factory());
		sync.setDeleteRemoteFiles(true);
		sync.setRemoteDirectory("/pub/example");
		sync.setFilter(new SftpSimplePatternFileListFilter("*.txt"));
		return sync;
	}

	// Store DATA To Destination Directory.
	@Bean
	@InboundChannelAdapter(channel = "fileDownload", poller = @Poller(fixedDelay = "55555000"))
	public MessageSource<File> sftpMessageSource() {
		SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(
				synchronizer());
		source.setLocalDirectory(new File("C:\\Users\\Kran-PC-1511\\Desktop\\Ebin B Kran\\Destination"));
		source.setAutoCreateLocalDirectory(true);
		source.setMaxFetchSize(10);
		return source;
	}

	// Here we can handle message based on logic...
	@Bean
	@ServiceActivator(inputChannel = "fileDownload")
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				log.info("now sending the payload: " + message.getPayload());
				log.info("sent the payload");
			}
		};
	}
}
