package com.sapient.teamsApi.DataDocuments;

import java.util.Date;

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
@Document(collection="citationTable")
public class CitationCollection {

	@Id
	private String id;

	private String from;
	
	private String to;
	
	private String citation;
	
	private String from_email;
	
	private String to_email;
	
	private Date timestamp;
	
	
	
	
}
