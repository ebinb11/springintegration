package com.springintegration.springintegration.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlRootElement(name = "states")
@XmlType(propOrder = { "stateName", "stateCode"})
public class StatesDTO {

	private String stateName;
	private String stateCode;
}
