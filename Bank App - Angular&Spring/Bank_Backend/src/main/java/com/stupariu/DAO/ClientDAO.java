package com.stupariu.DAO;

import java.util.Collection;

import com.stupariu.Entity.Client;

public interface ClientDAO {
	
	Collection<Client> getAllClients();

	Client getClientById(String id);

	void updateClient(Client cient);

	void deleteClient(String id);

	void insertClient(Client cient);

}
