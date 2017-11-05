package com.stupariu.DAO;

import java.util.Collection;

import com.stupariu.Entity.Bill;

public interface BillDAO {

	Collection<Bill> getAllBills(String id);
	
	void changeBillState(String billId);
	
}
