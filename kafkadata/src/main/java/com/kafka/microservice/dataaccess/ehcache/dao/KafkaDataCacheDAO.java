package com.kafka.microservice.dataaccess.ehcache.dao;

public interface KafkaDataCacheDAO
{
	public String getAlternateDates(String searchId);

	public void setAlternateDates(String searchId, String alternateDates);

}
