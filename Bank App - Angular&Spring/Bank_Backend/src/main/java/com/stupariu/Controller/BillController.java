package com.stupariu.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.Entity.Bill;
import com.stupariu.Service.BillService;

@RestController
@RequestMapping("/bills")
@CrossOrigin
public class BillController {
	@Autowired
	private BillService billService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Bill> getAllBillsOfClient(@PathVariable("id") String id) {
		return this.billService.getAllBills(id);
	}
	
	@RequestMapping(value="/update/{billId}",method = RequestMethod.GET)
	@ResponseBody
	public void changeBillState(@PathVariable("billId") String billId) {
		this.billService.changeBillState(billId);
	}
	
}
