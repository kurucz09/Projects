package com.stupariu.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SellsList", propOrder = { "sellsList" })
@XmlRootElement(name = "SellsList")
public class SellsList {

	@XmlElement(name = "Sell", required = true)
    private List<Sell> sellsList = new ArrayList<Sell>();
	
	public SellsList(){
		
	}

	public List<Sell> getSells() {
		return sellsList;
	}

	public void setSells(List<Sell> sellsList) {
		this.sellsList = sellsList;
	}
	
}
