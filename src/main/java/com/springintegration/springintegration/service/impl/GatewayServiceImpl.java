package com.springintegration.springintegration.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springintegration.springintegration.dto.StatesDTO;
import com.springintegration.springintegration.messagingGateway.MessagingGatewayService;
import com.springintegration.springintegration.service.GatewayService;

@Service
public class GatewayServiceImpl implements GatewayService {

	@Autowired
	MessagingGatewayService messagingService;

	@Override
	@Scheduled(cron = "0 0/30 11-12 * * *", zone = "Asia/Calcutta")
	public String send(String message) {
		String sendMessage = "Ebin ";
		return messagingService.send(sendMessage);
	}

	@Override
	public StringWriter convertToXml(StatesDTO statesDTO) throws JAXBException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StatesDTO.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			marshaller.marshal(statesDTO, sw);
			messagingService.convertToXml(sw);
			return sw;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		 File convFile = new File(file.getOriginalFilename());
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
		messagingService.upload(convFile);
		return  "Uploaded";
	}
}
