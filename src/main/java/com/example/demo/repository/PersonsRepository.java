package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persons;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {
	
	@Query(value="select age from Persons where first_name= ?1 ",nativeQuery = true)
	public int getAge(String first_name);

}
