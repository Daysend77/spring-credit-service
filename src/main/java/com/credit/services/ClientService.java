package com.credit.services;

import com.credit.entities.Client;
import com.credit.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	public Client findClientById(Long id){
		return clientRepository.findById(id).orElse(null);
	}


	@Transactional
	public void addClient(Client client){
		clientRepository.save(client);
	}

}
