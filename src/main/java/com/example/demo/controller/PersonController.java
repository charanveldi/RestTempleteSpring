package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Persons;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	public ResponseEntity<List<Persons>> getAllPersons() {

		List<Persons> per = personService.getAllPersons();
		
		return new ResponseEntity<List<Persons>>(per,new HttpHeaders(),HttpStatus.OK);

	}
	
	@GetMapping("/{personid}")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Persons> getById(@PathVariable("personid") int id) throws RecordNotFoundException{
		
		Persons per=personService.getPersonsRecordById(id);
		
		return new ResponseEntity<Persons>(per,new HttpHeaders(),HttpStatus.OK );
		
	}
	
	@PostMapping
	public ResponseEntity<Persons> createOrUpdatePerson(@RequestBody Persons per) throws RecordNotFoundException{
		
		Persons p=personService.createOrUpdatePersons(per);
		return new ResponseEntity<Persons>(p,new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deletePersonById(@PathVariable("id") int id) throws RecordNotFoundException{
		
		personService.deletePersonById(id);
		return HttpStatus.FORBIDDEN;
	}

}
