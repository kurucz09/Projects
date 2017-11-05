package com.stupariu.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.Entity.Bill;

@Repository("billSQL")
public class MySQLBillDAO implements BillDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class BillRowMapper implements RowMapper<Bill> {

		@Override
		public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bill bill = new Bill();
			bill.setBillId(rs.getInt("billId"));
			bill.setSenderAccount(rs.getString("sender"));
			bill.setAmmound(rs.getString("sum"));
			bill.setDateCreated(rs.getString("dateCreated"));
			bill.setReceiverAccount(rs.getString("receiver"));
			bill.setState(rs.getString("state"));
			return bill;
		}

	}
	
	@Override
	public Collection<Bill> getAllBills(String id) {
		final String sqlStatement = "SELECT * FROM bills where cnp=?";
		List<Bill> bills = jdbcTemplate.query(sqlStatement, new BillRowMapper(),id);
		return bills;
	}

	@Override
	public void changeBillState(String id) {
		
		final String sqlStatement = "UPDATE bills SET state=? where billId = ? ";
		final String newState = "solved";
		jdbcTemplate.update(sqlStatement, new Object[] { newState,id });
	}

}
