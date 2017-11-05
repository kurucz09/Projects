package com.stupariu.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.Entity.Client;
import com.stupariu.Service.ClientService;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Client> getAllClients() {
		return this.clientService.getAllClients();
	}

	@RequestMapping(value = "/{cnp}", method = RequestMethod.GET)
	@ResponseBody
	public Client getClientById(@PathVariable("cnp") String cnp) {
		return this.clientService.getClientById(cnp);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateClient(@RequestBody Client client) {
		this.clientService.updateClient(client);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteClient(@PathVariable("id") String id) {
		this.clientService.deleteClient(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertClient(@RequestBody Client client) {
		this.clientService.insertClient(client);
	}

}
