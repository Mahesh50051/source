package com.kafka.microservice.businessobjects;

import org.springframework.stereotype.Service;
import com.kafka.microservice.businessobjects.KafkaDataRequest;
import com.kafka.microservice.businessobjects.KafkaDataResponse;

@Service
public class KafkaDataFixture
{

	private TestDataLoader jsonTestDataLoader = new TestDataLoader();

	public KafkaDataRequest createJsonSuccessRequest()
	{
		return (KafkaDataRequest) jsonTestDataLoader.createObjectFromResource(
                "com/kafka/microservice/businessobjects/request/kafkaDataRequest-success.json",
				KafkaDataRequest.class);
	}

	public KafkaDataRequest createJsonErrorRequest()
	{
		return (KafkaDataRequest) jsonTestDataLoader.createObjectFromResource(
                "com/kafka/microservice/businessobjects/request/kafkaDataRequest-error.json",
				KafkaDataRequest.class);
	}

	public KafkaDataRequest createJsonParseErrorRequest()
	{
		return (KafkaDataRequest) jsonTestDataLoader.createObjectFromResource(
                "com/kafka/microservice/businessobjects/request/kafkaDataRequest-jsonerror.json",
				KafkaDataRequest.class);
	}

	public KafkaDataRequest createJsonEmptyRequest()
	{
		return (KafkaDataRequest) jsonTestDataLoader.createObjectFromResource(
                "com/kafka/microservice/businessobjects/request/kafkaDataRequest-empty.json",
				KafkaDataRequest.class);
	}

	public String createJsonSuccessResponse()
	{
		KafkaDataResponse object = (KafkaDataResponse) jsonTestDataLoader.createObjectFromResource(
                "com/kafka/microservice/businessobjects/response/kafkaDataResponse-success.json",
				KafkaDataResponse.class);
		return (String) jsonTestDataLoader.exportObjectTo(object, true);
	}

	public String createJsonEmptyResponse()
	{
		KafkaDataResponse object = (KafkaDataResponse) jsonTestDataLoader.createObjectFromResource(
                "com/kafka/microservice/businessobjects/response/kafkaDataResponse-empty.json",
				KafkaDataResponse.class);
		return (String) jsonTestDataLoader.exportObjectTo(object, true);
	}
}
