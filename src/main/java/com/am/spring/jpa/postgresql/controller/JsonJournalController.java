package com.am.spring.jpa.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.am.spring.jpa.postgresql.model.JsonJournal;
import com.am.spring.jpa.postgresql.repository.JsonJournalRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/jsonservice/api")
public class JsonJournalController {

	@Autowired
	JsonJournalRepository jsonJournalRepository;

	@GetMapping("/journals")
	public ResponseEntity<List<JsonJournal>> getAllJournals(@RequestParam(required = false) String jsoncode) {
		try {
			List<JsonJournal> jsonJournals = new ArrayList<JsonJournal>();

			if (jsoncode == null) {
				jsonJournalRepository.findAll().forEach(jsonJournals::add);
			}
			else {
				Optional<JsonJournal> jsonJournalData = jsonJournalRepository.findByJsoncode(jsoncode);
				
				if(jsonJournalData.isPresent()) {
					jsonJournals.add(jsonJournalData.get());
				}
			}

			if (jsonJournals.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(jsonJournals, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/journals/{jsoncode}")
	public ResponseEntity<JsonJournal> getJournalsByJournalcode(@PathVariable("jsoncode") String jsoncode) {
		Optional<JsonJournal> jsonJournalData = jsonJournalRepository.findByJsoncode(jsoncode);

		if (jsonJournalData.isPresent()) {
			return new ResponseEntity<>(jsonJournalData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/journals")
	public ResponseEntity<JsonJournal> createJournals(@RequestBody JsonJournal jsonJournal) {
		try {
			JsonJournal _jsonJournal = jsonJournalRepository
					.save(new JsonJournal(jsonJournal.getJsoncode(), jsonJournal.getJsontext()));
			return new ResponseEntity<>(_jsonJournal, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/journals/{jsoncode}")
	public ResponseEntity<JsonJournal> updateJournals(@PathVariable("id") long id, @RequestBody JsonJournal jsonJournal) {
		Optional<JsonJournal> jsonJournalData = jsonJournalRepository.findById(id);

		if (jsonJournalData.isPresent()) {
			JsonJournal _jsonJournal = jsonJournalData.get();
			_jsonJournal.setJsoncode(jsonJournal.getJsoncode());
			_jsonJournal.setJsontext(jsonJournal.getJsontext());

			return new ResponseEntity<>(jsonJournalRepository.save(_jsonJournal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/journals/{jsoncode}")
	public ResponseEntity<HttpStatus> deleteJournal(@PathVariable("id") long id) {
		try {
			jsonJournalRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	@DeleteMapping("/journals")
	public ResponseEntity<HttpStatus> deleteAllJournals() {
		try {
			jsonJournalRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	*/
}
