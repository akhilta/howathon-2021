package com.sapient.teamsApi.Data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sapient.teamsApi.DataDocuments.FilterCitationCollection;

public interface FilterCitationTableRepo extends MongoRepository<FilterCitationCollection, String> {

	List<FilterCitationCollection> findByEmail(String email);
}
