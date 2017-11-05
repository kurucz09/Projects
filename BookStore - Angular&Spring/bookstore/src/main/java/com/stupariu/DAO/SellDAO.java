package com.stupariu.DAO;

import static com.stupariu.Constants.SellsURL;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Repository;

import com.stupariu.Entity.Sell;
import com.stupariu.Entity.SellsList;

@Repository("sellXML")
public class SellDAO {

	public SellDAO(){
		
	}
	
	private void writeSellsToXML(SellsList sellsList){
		try{
			File sells = new File(SellsURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(SellsList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(sellsList, sells);
		}catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private SellsList readSellsFromXML(){
		SellsList sellsList = new SellsList();
		try {

			File file = new File(SellsURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(SellsList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			sellsList = (SellsList) jaxbUnmarshaller.unmarshal(file);

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return sellsList;
	}
	
	public SellsList getAllSells() {
		SellsList sellsList = readSellsFromXML();
		return sellsList;
	}

	public void insertSell(Sell sell) {
		SellsList sellsList = readSellsFromXML();
		sellsList.getSells().add(sell);
		writeSellsToXML(sellsList);
	}

}
