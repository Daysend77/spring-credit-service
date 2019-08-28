package com.credit.services;

import com.credit.entities.Country;
import com.credit.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;


	public Country findByName(String name){
		return countryRepository.findByName(name);
	}


	@Transactional
	public void setCountryLimit(String name, int claimLimit) {
		Country country = countryRepository.findByName(name);
		country.setClaimLimit(claimLimit);
		countryRepository.save(country);
	}


	@Transactional
	public void addCountry(Country country){
		countryRepository.save(country);
	}


	@Transactional
	public void deleteAll(){
		countryRepository.deleteAll();
	}

}
