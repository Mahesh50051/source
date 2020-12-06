package com.kafka.microservice.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kafka.microservice.businessobjects.KafkaDataRequest;
import com.kafka.microservice.businessobjects.KafkaDataResponse;
import com.kafka.microservice.businessobjects.BaseResponseObject;
import com.kafka.microservice.service.KafkaDataProducerService;
import com.kafka.microservice.service.KafkaDataService;

/**
 * 
 * This controller serves HEAD and GET requests
 * 
 * @author Mahesh
 *
 */
@Controller
@RequestMapping("/alternatedates")
public class KafkaDataController
{

	@Autowired
    KafkaDataService alternateDatesService;
	
	@Autowired
	KafkaDataProducerService alternateDatesKafkaProducerService;

	/**
	 * HTTP.GET method for JSON content
	 * 
	 * 
	 * @param request
	 *            (searchId as part of the path variable)
	 * @return
	 */
	
	@GetMapping(value = "/searchId/{searchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
    KafkaDataResponse retrieveAlternateDatesJson(@Valid KafkaDataRequest request)
	{
		return retrieveAlternateDates(request);
	}

	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private KafkaDataResponse retrieveAlternateDates(KafkaDataRequest request)
	{
		return alternateDatesService.retrieveAlternateDates(request);
	}
	
	/**
	 * HTTP.POST method for JSON content
	 * 
	 * 
	 * @param request
	 *            (searchId as part of the path variable)
	 * @return
	 */	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BaseResponseObject postAlternateDatesResultsJson(@RequestBody String request)
	{
		return alternateDatesKafkaProducerService.sendAlternateDatesResults(request);
	}
}
