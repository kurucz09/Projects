package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.BillDAO;
import com.stupariu.Entity.Bill;

@Service
public class BillService {

	@Autowired
	@Qualifier("billSQL")
	private BillDAO billDao;
	
	public Collection<Bill> getAllBills(String id){
		return this.billDao.getAllBills(id);
	}
	
	public void changeBillState(String billId){
		
		this.billDao.changeBillState(billId);
	}
	
}
