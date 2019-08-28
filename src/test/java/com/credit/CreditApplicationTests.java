package com.credit;

import com.credit.entities.Claim;
import com.credit.entities.Client;
import com.credit.entities.Country;
import com.credit.services.ClaimService;
import com.credit.services.ClientService;
import com.credit.services.CountryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CreditApplication.class)
@SpringBootTest
public class CreditApplicationTests {

	@Autowired
	ClaimService claimService;

	@Autowired
	CountryService countryService;

	@Autowired
	ClientService clientService;


	@Test
	public void testAddCountry() {
		String testCountryName = "TestCountryName1";

		countryService.addCountry(new Country(testCountryName));
		Country country = countryService.findByName(testCountryName);
		countryService.deleteAll();

		Assert.assertEquals(country.getName(), testCountryName);
	}


	@Test
	public void testChangeCountryLimit() {
		String testCountryName = "TestCountryName2";
		int countryLimit = 555;

		countryService.addCountry(new Country(testCountryName));
		countryService.setCountryLimit(testCountryName, countryLimit);
		Country country = countryService.findByName(testCountryName);
		countryService.deleteAll();

		Assert.assertEquals(country.getClaimLimit(), countryLimit);
	}


	@Test
	public void testAddSimpleClaim() {
		String testCountryName = "TestCountryName3";
		String message = claimService.addClaim(1000,12, "Ivan", "Ivanov", 40,  testCountryName);
		Claim claim = claimService.findByClientId(40);
		claimService.deleteAll();

		Assert.assertEquals(claim.getClient().getId(), 40);
		Assert.assertEquals(message, "Заявка добавлена на рассмотрение");
	}


	@Test
	public void testAddClaimWhereClientBlocked() {
		Country country = new Country("TestCountryName4");
		String firstName = "Petr";
		String lastName = "Petrov";
		long clientId = 55;

		clientService.addClient(new Client(clientId, firstName, lastName, true, country));
		String message = claimService.addClaim(5000, 6, firstName, lastName, clientId, "TestCountryName4");
		claimService.deleteAll();

		Assert.assertEquals(message, "Пользователь заблокирован, заявка отклонена");

	}


	@Test
	public void testGetClaims() {
		claimService.addClaim(1000, 12, "Ivan", "Ivanov", 20, "TestCountryName5");
        List<Claim> claims = claimService.getClaims();
		claimService.deleteAll();

		Assert.assertNotNull(claims);
	}


	@Test
	public void testGetClientClaims() {

		claimService.addClaim(1000, 12, "Ivan", "Ivanov", 10, "TestCountryName5");
		List<Claim> claims = claimService.getClientClaims(10);
		claimService.deleteAll();

		Assert.assertNotNull(claims);
	}
}
