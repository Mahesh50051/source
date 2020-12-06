package com.kafka.microservice.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.kafka.microservice.TestApplication;
import com.kafka.microservice.config.KafkaDataConfig;
import com.kafka.microservice.dataaccess.ehcache.dao.KafkaDataCacheDAOImplMock;
import com.kafka.microservice.service.KafkaDataListenerService;
import com.kafka.microservice.service.KafkaDataProducerService;
import com.kafka.microservice.service.KafkaDataService;
import com.kafka.microservice.web.KafkaDataController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApplication.class, KafkaDataController.class })
public class KafkaDataControllerTest
{

	@Autowired
	protected WebApplicationContext wac;

	private MockMvc mockMvc;

	@Mock
	KafkaDataCacheDAOImplMock alternateDatesCacheDAODefaultMock;

	@MockBean
	private KafkaDataService service;

	@MockBean
	private KafkaDataListenerService alternateDatesKafkaListenerService;
	
	@MockBean
	KafkaDataProducerService alternateDatesKafkaProducerService;

	@Autowired
	private KafkaDataController alternateDatesController;

	@MockBean
	private KafkaDataConfig config;

	@Before
	public void before()
	{
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	@Test
	public void testRetriveAlternateDatesJson() throws Exception
	{
		this.mockMvc.perform(get("/ae/alternatedates/{searchId}", "4274349")).andReturn();
	}

	@Test
	public void testRetriveAlternateDatesEmptyInputTest() throws Exception
	{
		this.mockMvc.perform(get("/ae/alternatedates/{searchId}", "")).andExpect(status().is(404));
	}

}
