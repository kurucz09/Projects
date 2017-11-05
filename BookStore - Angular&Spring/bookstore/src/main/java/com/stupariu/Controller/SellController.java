package com.stupariu.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.Entity.Message;
import com.stupariu.Entity.Sell;
import com.stupariu.Service.SellService;

@RestController
@RequestMapping("/sells")
@CrossOrigin
public class SellController {

	@Autowired
	private SellService sellService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Sell> getAllSells() {
		return this.sellService.getAllSells();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message sellBooks(@RequestBody Sell sell) {
		return this.sellService.insertSell(sell);
	}
	
}
