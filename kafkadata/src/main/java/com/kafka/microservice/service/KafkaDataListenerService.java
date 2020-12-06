package com.kafka.microservice.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.kafka.microservice.dataaccess.ehcache.dao.KafkaDataCacheDAO;

/**
 * The service will continuously listen to the configured topic and will consume the messages 
 * . Then will put the key and value to the ehCache. The key will be the search id from the JSON and the value is
 * the entire JSON
 * 
 * 
 * @author mahesh
 *
 */
@Service
public class KafkaDataListenerService
{
	private static Logger alternateDateslogger = LoggerFactory.getLogger("ALTDATES");

	private final static String errorInfo = "Service=AlternateDatesKafkaListenerService, Status=Error, alternateDateString=";

	@Autowired
	private KafkaDataCacheDAO alternateDatesCacheDAO;

	public KafkaDataCacheDAO getAlternateDatesCacheDAO()
	{
		return alternateDatesCacheDAO;
	}

	public void setAlternateDatesCacheDAO(KafkaDataCacheDAO alternateDatesCacheDAO)
	{
		this.alternateDatesCacheDAO = alternateDatesCacheDAO;
	}

	/**
	 * Service method consumes the message from the configured topic.
	 * 
	 * 
	 * @param request
	 * @throws Exception
	 */
	@KafkaListener(topics = "${kafka.topic}")
	public void consume(String message)
	{
		setKafkaDataIntoCache(message);
	}

	/**
	 * Service method stores key and valye pair in EHCache
	 * 
	 * @param request
	 * @throws JSONException
	 */
	private void setKafkaDataIntoCache(String message)
	{
		JSONObject json;
		String searchId = "";

		try
		{
			json = new JSONObject(message);
			searchId = json.getString("searchId");

			alternateDatesCacheDAO.setAlternateDates(searchId, message);
			alternateDateslogger.info(message);
		}
		catch (Exception e)
		{
			alternateDateslogger.error(errorInfo + e.getLocalizedMessage());
		}

	}

}
