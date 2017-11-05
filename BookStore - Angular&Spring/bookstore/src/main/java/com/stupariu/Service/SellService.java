package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.BookDAO;
import com.stupariu.DAO.SellDAO;
import com.stupariu.Entity.Message;
import com.stupariu.Entity.Sell;

@Service
public class SellService {

	@Autowired
	@Qualifier("sellXML")
	private SellDAO sellDAO;
	
	public Collection<Sell> getAllSells(){
		Collection<Sell> collect = this.sellDAO.getAllSells().getSells();
		return collect;
	}
	
	public Message insertSell(Sell sell){
		int items = sell.getBook().getNrOfItems();
		int itemsToSub = Integer.parseInt(sell.getQuantity());
		Message msg = new Message();
		if(items<=0){
			msg.setMessage("failed");
		} else if(items < itemsToSub){
			msg.setMessage("failed");
		} else{
			sell.getBook().setNrOfItems(items - itemsToSub);
			BookDAO bookDAO = new BookDAO();
			bookDAO.updateBook(sell.getBook());
			msg.setMessage("success");
			this.sellDAO.insertSell(sell);
		}
		return msg;
		
	}
	
}
