package com.kafka.microservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
@EnableKafka
public class KafkaDataConfig
{

	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;

	@Value("${kafka.groupId}")
	private String groupId;

	@Value("${java.security.auth.login.config:null}")
	private String JAVA_SECURITY_AUTH_LOGIN_CONFIG;

	@Value("${java.security.krb5.realm:null}")
	private String JAVA_SECURITY_KRB5_REALM;

	@Value("${java.security.krb5.kdc:null}")
	private String JAVA_SECURITY_KRB5_KDC;

	@Value("${kafka.security.protocol}")
	private String securityProtocol;

	@Value("${kafka.earliest}")
	private String earliest;

	// Producer

	@Value("${kafka.acks:all}")
	private String acks;

	@Value("${kafka.batch.size:16384}")
	private String batchSize;

	@Value("${kafka.linger.ms:10}")
	private String lingerMs;

	@Value("${kafka.max.block.ms:1000}")
	private String maxBlockMs;

	@Value("${kafka.retries:0}")
	private String retries;

	@Value("${kafka.buffer.memory:33554432}")
	private String bufferMemory;

	@Value("${kafka.metadata.max.age.ms:300000}")
	private String metadataMaxAgeMs;

	@Value("${kafka.max.request.size:1048576}")
	private String maxRequestSize;

	@Value("${kafka.transaction.timeout.ms:60000}")
	private String transactionTimeoutMs;
	
	@Value("${kafka.producer.security.protocol}")
	private String producerSecurityProtocol;
	

	@Bean
	public Map<String, Object> consumerConfigs()
	{
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, earliest);
		props.put("security.protocol", securityProtocol);

		System.setProperty("java.security.auth.login.config", JAVA_SECURITY_AUTH_LOGIN_CONFIG);
		System.setProperty("java.security.krb5.realm", JAVA_SECURITY_KRB5_REALM);
		System.setProperty("java.security.krb5.kdc", JAVA_SECURITY_KRB5_KDC);

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory()
	{
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, String> kafkaFactory = new ConcurrentKafkaListenerContainerFactory<>();
		kafkaFactory.setConsumerFactory(consumerFactory());

		return kafkaFactory;
	}

	@Bean
	public Map<String, Object> producerConfigs()
	{
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "console-producer");
		props.put("security.protocol", producerSecurityProtocol);
		props.put(ProducerConfig.ACKS_CONFIG, acks); // make sure no data lost - Leader + replicas acknowledgement
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
		props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs); // default is 60000
		props.put(ProducerConfig.RETRIES_CONFIG, retries);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
		props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, metadataMaxAgeMs);
		props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, maxRequestSize); // max.request.size = 1048576 default
		props.put("transaction.timeout.ms", transactionTimeoutMs); // default is 60000

		System.setProperty("java.security.auth.login.config", JAVA_SECURITY_AUTH_LOGIN_CONFIG);
		System.setProperty("java.security.krb5.realm", JAVA_SECURITY_KRB5_REALM);
		System.setProperty("java.security.krb5.kdc", JAVA_SECURITY_KRB5_KDC);

		return props;
	}

	@Bean
	public ProducerFactory<String, String> producerFactory()
	{
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate()
	{
		return new KafkaTemplate<>(producerFactory());
	}

}
