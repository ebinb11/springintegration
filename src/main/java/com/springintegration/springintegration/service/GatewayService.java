package com.springintegration.springintegration.service;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;

import org.springframework.web.multipart.MultipartFile;

import com.springintegration.springintegration.dto.StatesDTO;

public interface GatewayService {

	public String send(String message);

	public StringWriter convertToXml(StatesDTO statesDTO) throws JAXBException;

	public String uploadFile(MultipartFile file) throws IOException;

}
