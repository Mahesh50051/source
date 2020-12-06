package com.kafka.microservice.dataaccess.ehcache.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * DAO Implementation used store kafka data into ehcache for Alternate Dates
 * 
 * This service implementation is bound to the Server class method. *
 * 
 * 
 * @author mahesh
 *
 */

@Component
public class KafkaDataCacheDAOImpl implements KafkaDataCacheDAO
{

	@Autowired
	private CacheManager manager;

	private Cache cache;

	@PostConstruct
	public void init()
	{
		cache = manager.getCache("kafkaConsumerCache");
	}

	/**
	 * Service method to Get Data from Cache Manager
	 * 
	 * 
	 * @param request
	 * @return String
	 */
	public String getAlternateDates(String searchId)
	{
		String data = null;
		if (cache != null)
		{
			ValueWrapper wrapper = cache.get(searchId);
			if (wrapper != null)
			{
				data = wrapper.get().toString();
			}
		}

		return data;
	}

	/**
	 * Service method to Set Data into Cache Manager
	 * 
	 * @param String
	 * @param String
	 */
	public void setAlternateDates(String searchId, String alternateDates)
	{
		cache.put(searchId, alternateDates);

	}

}
