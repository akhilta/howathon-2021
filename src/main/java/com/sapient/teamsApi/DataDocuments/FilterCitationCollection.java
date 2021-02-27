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
@Document(collection = "filterCitationTable")
public class FilterCitationCollection {

	@Id
	private String id;

	private String name;

	private String email;

	private String citationType;

	private int points;

	private Date timestamp;

	private String type;

	public FilterCitationCollection(String name, String email, String citationType, int points, Date timestamp,
			String type) {
		super();
		this.name = name;
		this.email = email;
		this.citationType = citationType;
		this.points = points;
		this.timestamp = timestamp;
		this.type = type;
	}

}
