package com.credit.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//Название страны
	private String name;

	//Лимит заявок
	private int claimLimit = 10;

	//Клиенты проживающие в данной стране
	@JsonBackReference
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Client> clients;


	public Country() {
	}

	public Country(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClaimLimit() {
		return claimLimit;
	}

	public void setClaimLimit(int claimLimit) {
		this.claimLimit = claimLimit;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
