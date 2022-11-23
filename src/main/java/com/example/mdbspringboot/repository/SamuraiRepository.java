package com.example.mdbspringboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.model.Samurai;

public interface SamuraiRepository extends MongoRepository<Samurai, String> {
	@Query("{name:'?0'}")
	Samurai findItemByName(String name);

	@Query(value = "{clan: '?0'}")
	List<Samurai> findAll(String clan);

	public long count();
}