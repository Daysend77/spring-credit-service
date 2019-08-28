package com.credit.repositories;

import com.credit.entities.Claim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim,Long> {

	List<Claim> findByConfirmed(boolean confirmed);

	List<Claim> findByClientIdAndConfirmed(long clientId, boolean confirmed);

	Claim findByClientId(long clientId);
}
