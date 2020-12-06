package com.kafka.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.kafka.microservice.businessobjects.BaseResponseObject;

/**
 * 
 * @author mahesh
 *
 */
@Service
public class KafkaDataProducerService
{
	private static Logger alternateDateslogger = LoggerFactory.getLogger("ALTDATES");

	private final static String errorInfo = "Service=AlternateDatesKafkaProducerService, Status=Error, sendAlternateDatesResults=";
	
	private final static String info = "Service=AlternateDatesKafkaProducerService, Status=Success, sendAlternateDatesResults=";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.producer.topic:null}")
	private String topic;

	/**
	 * Service method produce the message from the configured topic. *
	 * 
	 * @param request
	 * @throws Exception
	 */
	public BaseResponseObject sendAlternateDatesResults(String request)
	{
		alternateDateslogger.info(info+request);
		BaseResponseObject response = new BaseResponseObject();
		// the KafkaTemplate provides asynchronous send methods returning a
		// Future
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, request);
		response.setStatus("Success");
		// register a callback with the listener to receive the result of the send asynchronously
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
		{
			@Override
			public void onSuccess(SendResult<String, String> result)
			{
				response.setStatus("Success");
			}

			@Override
			public void onFailure(Throwable ex)
			{
				alternateDateslogger.error(errorInfo + ex.getLocalizedMessage());
				response.setStatus("Error");
			}
		});
		return response;
	}

}
