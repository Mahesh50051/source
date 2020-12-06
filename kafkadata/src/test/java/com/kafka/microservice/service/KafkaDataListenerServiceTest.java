package com.kafka.microservice.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.kafka.microservice.TestApplication;
import com.kafka.microservice.dataaccess.ehcache.dao.KafkaDataCacheDAO;
import com.kafka.microservice.kafka.KafkaTestBase;
import com.kafka.microservice.service.KafkaDataListenerService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = TestApplication.class)
@DirtiesContext
public class KafkaDataListenerServiceTest extends KafkaTestBase
{
	// set count down latch to 2 seconds
	private final long TIME_OUT = 2000;

	@Mock
	private KafkaDataCacheDAO alternateDatesCacheDAO;

	@Autowired
	private KafkaDataListenerService service;

	@Test
	public void testJsonErrorConsume() throws Exception
	{
		// send the message
		String messagePosted = "{\"messageId\":\"1000\"}";
		template.sendDefault(messagePosted);
		LOGGER.debug("test-sender sent message='{}'", messagePosted);
		new CountDownLatch(1).await(TIME_OUT, TimeUnit.MILLISECONDS);
		String messageReceived = service.getAlternateDatesCacheDAO().getAlternateDates("1000");
		Assert.assertNull(messageReceived);
	}

	@Test
	public void testConsume() throws Exception
	{
		// send the message
		String messagePosted = "{\"searchId\":\"1000\"}";
		template.sendDefault(messagePosted);
		LOGGER.debug("test-sender sent message='{}'", messagePosted);
		new CountDownLatch(1).await(TIME_OUT, TimeUnit.MILLISECONDS);
		String messageReceived = service.getAlternateDatesCacheDAO().getAlternateDates("1000");
		Assert.assertEquals(messagePosted, messageReceived);

		service.setAlternateDatesCacheDAO(null);

		template.sendDefault(messagePosted);
		LOGGER.debug("test-sender sent message='{}'", messagePosted);
		new CountDownLatch(1).await(TIME_OUT, TimeUnit.MILLISECONDS);
		service.setAlternateDatesCacheDAO(null);
		Assert.assertNull(service.getAlternateDatesCacheDAO());
		service.setAlternateDatesCacheDAO(alternateDatesCacheDAO);
		String messageErrorReceived = service.getAlternateDatesCacheDAO().getAlternateDates("1000");
		Assert.assertNull(messageErrorReceived);
	}

}
