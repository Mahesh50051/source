package com.kafka.microservice.businessobjects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaDataRequest
{

	private String searchId;

	public String getSearchId()
	{
		return searchId;
	}

	public void setSearchId(String searchId)
	{
		this.searchId = searchId;
	}

}
