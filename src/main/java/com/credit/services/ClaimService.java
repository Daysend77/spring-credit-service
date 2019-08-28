package com.credit.services;

import com.credit.entities.Claim;
import com.credit.entities.Client;
import com.credit.entities.Country;
import com.credit.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ClaimService {

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	ClientService clientService;

	@Autowired
	CountryService countryService;

	public List<Claim> getClaims() {
		return claimRepository.findByConfirmed(true);
	}

	public List<Claim> getClientClaims(long clientId) {
		return claimRepository.findByClientIdAndConfirmed(clientId, true);
	}

	public Claim findByClientId(long clientId){
		return claimRepository.findByClientId(clientId);
	}

	@Transactional
	public void deleteAll() {
		claimRepository.deleteAll();
	}


	@Transactional
	public String addClaim(int amount, int term, String firstName, String lastName, long clientId, String countryName) {

		Client client = clientService.findClientById(clientId);

		if(client != null && client.isBlocked()) {
			return "Пользователь заблокирован, заявка отклонена";
		}

		Country country = countryService.findByName(countryName);
		country = Objects.nonNull(country) ? country : new Country(countryName);
		client = Objects.nonNull(client) ? client : new Client(clientId, firstName, lastName, country);
		Claim claim = new Claim(amount, term, client);

		claimRepository.save(claim);
		return "Заявка добавлена на рассмотрение";
	}
}
