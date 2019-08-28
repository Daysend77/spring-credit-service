package com.credit.repositories;

import com.credit.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

	Country findByName(String name);
}
