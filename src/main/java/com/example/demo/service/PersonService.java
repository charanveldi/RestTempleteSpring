package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entity.Persons;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.PersonsRepository;


@Service
public class PersonService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	private PersonsRepository repo;

	public List<Persons> getAllPersons() {
		
		logger.info("Trying to get all Persons Records");

		List<Persons> personsList = repo.findAll();
		if (personsList.size() > 0) {
			return personsList;
		} else {
			return new ArrayList<Persons>();
		}

	}

	public Persons getPersonsRecordById(int id) throws RecordNotFoundException {
		
		logger.info("Id received is:{}",id);

		Optional<Persons> persons = repo.findById(id);
		if (persons.isPresent()) {

			return persons.get();
		} else {
			throw new RecordNotFoundException("Record is not found for the particular id");
		}

	}

	public Persons createOrUpdatePersons(Persons person) throws RecordNotFoundException {

		Optional<Persons> per = repo.findById(person.getId());

		if (per.isPresent()) {
			Persons p = per.get();
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setAge(person.getAge());

			p = repo.save(p);
			return p;

		} else {
			person = repo.save(person);
			return person;

		}

	}

	public void deletePersonById(int id) throws RecordNotFoundException {
		Optional<Persons> person = repo.findById(id);
		if (person.isPresent()) {
			repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record found with a particular id");
		}
	}


}
