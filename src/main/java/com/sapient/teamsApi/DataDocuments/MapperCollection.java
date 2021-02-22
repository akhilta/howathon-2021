package com.sapient.teamsApi.DataDocuments;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="mapperTable")
public class MapperCollection {

	@Id
	private String id;
	
	private String email;
	
	private String jsonId;
	
	private String name;
	
	private int points;
	
	private String lastrefreshed;
	
}
