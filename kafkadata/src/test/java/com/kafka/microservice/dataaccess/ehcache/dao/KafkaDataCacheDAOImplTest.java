package com.kafka.microservice.dataaccess.ehcache.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.kafka.microservice.TestApplication;
import com.kafka.microservice.dataaccess.ehcache.dao.KafkaDataCacheDAO;
import com.kafka.microservice.kafka.KafkaTestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = TestApplication.class)
@DirtiesContext
public class KafkaDataCacheDAOImplTest extends KafkaTestBase
{

	@Autowired
	private KafkaDataCacheDAO alternateDatesCacheDAO;

	@Test
	public void testAlternateDatesCache()
	{
		alternateDatesCacheDAO.setAlternateDates("key", "Message");
		String messageReceived = alternateDatesCacheDAO.getAlternateDates("key");
		Assert.assertEquals("Message", messageReceived);
	}

}