package com.am.spring.jpa.postgresql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.am.spring.jpa.postgresql.model.JsonJournal;

public interface JsonJournalRepository extends JpaRepository<JsonJournal, Long> {
	Optional<JsonJournal> findByJsoncode(String jsoncode);
	
	List<JsonJournal> findByJsoncodeContaining(String jsoncode);

	//JsonJournal save(JsonJournal jsonJournal);
}
