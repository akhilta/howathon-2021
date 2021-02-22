package com.sapient.teamsApi.Data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sapient.teamsApi.DataDocuments.MapperCollection;

public interface MapperTableRepo extends MongoRepository<MapperCollection, String> {

	List<MapperCollection> findByJsonId(String string);

}
