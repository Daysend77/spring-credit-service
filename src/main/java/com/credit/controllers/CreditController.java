package com.credit.controllers;

import com.credit.entities.Claim;
import com.credit.services.ClaimService;
import com.credit.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditController {

	@Autowired
	ClaimService claimService;

	@Autowired
	CountryService countryService;


	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<String> index() {
		return new ResponseEntity<>("Сервис приема заявок", HttpStatus.OK);
	}


	//Подача заявки на кредит
	@PutMapping("/claim")
		public ResponseEntity<String> addClaim(@RequestParam(value = "amount", required = true) int amount,
							 @RequestParam(value = "term", required = true) int term,
							 @RequestParam(value = "firstName", required = true) String firstName,
							 @RequestParam(value = "lastName", required = true) String lastName,
							 @RequestParam(value = "clientId", required = true) long clientId,
							 @RequestParam(value = "country", required = true) String country) {

		try {
			String message = claimService.addClaim(amount, term, firstName, lastName, clientId, country);
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	//Получение списка всех одобренных кредитов
	@GetMapping(value = "/claims", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Claim> getClaims() {
		return claimService.getClaims();
	}


	//Получение списка одобренных кредитов у пользователя
	@GetMapping(value = "/claims/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Claim> getClientClaims(@PathVariable(name="clientId", required=true) long clientId) {
		return claimService.getClientClaims(clientId);
	}


	//Изменение лимита заявок у страны
	@PostMapping("/limit")
	public ResponseEntity<String> setCountryLimit(@RequestParam(value = "country", required = true) String country,
												  @RequestParam(value = "limit", required = true) int limit) {
		try {
			countryService.setCountryLimit(country, limit);
			return new ResponseEntity<>("Лимит заявок изменен на " + limit + " для страны " + country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Неверное название страны", HttpStatus.BAD_REQUEST);
		}
	}

}
