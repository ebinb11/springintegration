package com.springintegration.springintegration.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springintegration.springintegration.dto.StatesDTO;
import com.springintegration.springintegration.service.GatewayService;

@RestController
@RequestMapping("gateway")
public class GatewayController {

	@Autowired
	GatewayService gatewayService;
	
	@GetMapping("send")
	public ResponseEntity<String> send(@RequestParam String message) {
		String response = gatewayService.send(message);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("convertToXml")
	public ResponseEntity<StringWriter> convertToXml(@RequestBody StatesDTO statesDTO) throws JAXBException {
		StringWriter response = gatewayService.convertToXml(statesDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String response = gatewayService.uplaodFile(file);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
