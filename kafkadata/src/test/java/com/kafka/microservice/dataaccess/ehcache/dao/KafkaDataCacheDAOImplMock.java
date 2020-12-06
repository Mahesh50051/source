package com.kafka.microservice.dataaccess.ehcache.dao;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kafka.microservice.businessobjects.KafkaDataFixture;
import com.kafka.microservice.dataaccess.ehcache.dao.KafkaDataCacheDAO;

@Repository("alternateDatesCacheDAO")
public class KafkaDataCacheDAOImplMock implements KafkaDataCacheDAO
{

	private HashMap<String, String> messageRepository = new HashMap<>();

	@Autowired
	private KafkaDataFixture fixture;

	@PostConstruct
	public void mockDataSetup()
	{
		String alternateDates;
		alternateDates = fixture.createJsonSuccessResponse();
		messageRepository.put("4274111", alternateDates);
		alternateDates = fixture.createJsonEmptyResponse();
		messageRepository.put("4274349", alternateDates);
		messageRepository.put("4274312", null);
		messageRepository.put("4274112", alternateDates.replace("searchId", "startId"));

	}

	@Override
	public String getAlternateDates(String searchId)
	{
		return messageRepository.get(searchId);
	}

	@Override
	public void setAlternateDates(String searchId, String alternateDates)
	{
		messageRepository.put(searchId, alternateDates);
	}

}
