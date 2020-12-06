package com.kafka.microservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.kafka.microservice.TestApplication;
import com.kafka.microservice.businessobjects.KafkaDataFixture;
import com.kafka.microservice.businessobjects.KafkaDataRequest;
import com.kafka.microservice.businessobjects.KafkaDataResponse;
import com.kafka.microservice.kafka.KafkaTestBase;
import com.kafka.microservice.service.KafkaDataService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = TestApplication.class)
@DirtiesContext
public class KafkaDataServiceTest extends KafkaTestBase
{

	@Autowired
	private KafkaDataService alternateDatesService;

	@Autowired
	private KafkaDataFixture fixture;

	private KafkaDataRequest alternateDatesRequest, alternateDatesRequest_Error, alternateDatesRequest_Empty,
			alternateDatesRequest_JsonParse;

	private KafkaDataResponse alternateDatesResponse;

	@Before
	public void setup()
	{
		alternateDatesRequest = fixture.createJsonSuccessRequest();
		alternateDatesRequest_Error = fixture.createJsonErrorRequest();
		alternateDatesRequest_Empty = fixture.createJsonEmptyRequest();
		alternateDatesRequest_JsonParse = fixture.createJsonParseErrorRequest();
	}

	@Test
	public void tesRetrieveAlternateDatesSuccess()
	{
		alternateDatesResponse = alternateDatesService.retrieveAlternateDates(alternateDatesRequest);
		org.junit.Assert.assertNotNull(alternateDatesResponse);
		Assert.assertEquals("Success", alternateDatesResponse.getStatus());
		org.springframework.util.Assert.isInstanceOf(KafkaDataResponse.class, alternateDatesResponse);
	}

	@Test
	public void testRetrieveAlternateDatesJsonParseError()
	{
		alternateDatesResponse = alternateDatesService.retrieveAlternateDates(alternateDatesRequest_JsonParse);
		org.junit.Assert.assertNotNull(alternateDatesResponse);
		Assert.assertEquals("Error", alternateDatesResponse.getStatus());		
		org.springframework.util.Assert.isInstanceOf(KafkaDataResponse.class, alternateDatesResponse);
	}

	@Test
	public void tesRetrieveAlternateDatesError()
	{
		alternateDatesResponse = alternateDatesService.retrieveAlternateDates(alternateDatesRequest_Error);
		org.junit.Assert.assertNotNull(alternateDatesResponse);
		Assert.assertEquals("Error", alternateDatesResponse.getStatus());		
		org.springframework.util.Assert.isInstanceOf(KafkaDataResponse.class, alternateDatesResponse);
	}

	@Test
	public void tesRetrieveAlternateDatesEmptyJSON()
	{
		alternateDatesResponse = alternateDatesService.retrieveAlternateDates(alternateDatesRequest_Empty);
		org.junit.Assert.assertNotNull(alternateDatesResponse);
		Assert.assertEquals("Success", alternateDatesResponse.getStatus());
		Assert.assertNull(alternateDatesResponse.getKafkaDataList());
		org.springframework.util.Assert.isInstanceOf(KafkaDataResponse.class, alternateDatesResponse);
	}
}
