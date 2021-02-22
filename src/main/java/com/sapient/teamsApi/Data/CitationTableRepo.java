package com.sapient.teamsApi.Data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sapient.teamsApi.DataDocuments.CitationCollection;

public interface CitationTableRepo extends MongoRepository<CitationCollection,String> {

}
