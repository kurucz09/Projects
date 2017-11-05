package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.ClientDAO;
import com.stupariu.DAO.LogDAO;
import com.stupariu.Entity.Client;
import com.stupariu.Entity.Log;

@Service
public class ClientService {

	@Autowired
	@Qualifier("clientSQL")
	private ClientDAO clientDAO;
	
	public Collection<Client> getAllClients() {
		return this.clientDAO.getAllClients();
	}

	public Client getClientById(String id) {
		return this.clientDAO.getClientById(id);
	}

	public void updateClient(Client cient) {
		this.clientDAO.updateClient(cient);
	}

	public void deleteClient(String id) {
		this.clientDAO.deleteClient(id);
	}

	public void insertClient(Client client) {
		this.clientDAO.insertClient(client);
	}

}
