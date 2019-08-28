package com.credit.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//Имя
	private String firstName;

	//Фамилия
	private String lastName;

	//Черный список (true - заблокирован)
	private boolean blocked = false;

	//Заявки на кредит данного клиента
	@JsonBackReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Claim> claims;

	//Страна
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id")
	private Country country;


	public Client() {
	}

	public Client(Long id, String firstName, String lastName, Country country) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
	}

	public Client(long id, String firstName, String lastName, boolean blocked, Country country) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.blocked = blocked;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
