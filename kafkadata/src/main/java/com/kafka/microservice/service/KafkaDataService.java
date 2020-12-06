package com.kafka.microservice.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.microservice.businessobjects.KafkaData;
import com.kafka.microservice.businessobjects.KafkaDataRequest;
import com.kafka.microservice.businessobjects.KafkaDataResponse;
import com.kafka.microservice.dataaccess.ehcache.dao.KafkaDataCacheDAO;

/**
 * The service will retrieve the alternate dates for a given search Id (Kafka Session Id) from the ehCache. If the data
 * is not found will return a business error. The service converts the String JSON to the Response Object and throws
 * Business Error if any exception during the conversion This service implementation is bound to the controller GET
 * method. *
 * 
 * 
 * @author mahesh
 *
 */

@Service
public class KafkaDataService
{

	@Autowired
	private KafkaDataCacheDAO alternateDatesCacheDAO;

	@Value("${app.minalternatedates}")
	private int minAlternateDates;

	/**
	 * Service method to to Get Data from Ehcache
	 * 
	 * @param KafkaDataRequest
	 *            containing kafka session id (search Id)
	 * @return AlternateDatesResponse containing the Alternate Dates JSON
	 */	
	public KafkaDataResponse retrieveAlternateDates(KafkaDataRequest request)
	{

		KafkaDataResponse alternateDatesResponse = null;

		// Get Data from EHCACHE Cache
		String getJsonData = alternateDatesCacheDAO.getAlternateDates(request.getSearchId());
		// Convert JSON to Java Object
		if (getJsonData != null && !getJsonData.isEmpty())
		{
			try
			{
				alternateDatesResponse = new ObjectMapper().readValue(getJsonData, KafkaDataResponse.class);
			}
			catch (JsonParseException | JsonMappingException e1)
			{
				throw new RuntimeException("Exception Occured in retrieveAlternateDates");
			}
			catch (IOException e1)
			{
			  throw new RuntimeException("Exception Occured in retrieveAlternateDates");
			}
		}
		else
		{
		  throw new RuntimeException("Exception Occured in retrieveAlternateDates");
		}

		return alternateDatesResponse;
	}

}
