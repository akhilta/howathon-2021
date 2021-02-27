package com.sapient.teamsApi.Data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sapient.teamsApi.DataDocuments.CitationCollection;
import com.sapient.teamsApi.DataDocuments.FilterCitationCollection;

public interface CitationTableRepo extends MongoRepository<CitationCollection,String> {

	List<CitationCollection> findByToEmail(String toEmail);
	
	
}
