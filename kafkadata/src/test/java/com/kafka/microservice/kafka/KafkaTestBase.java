package com.kafka.microservice.kafka;

import org.junit.Before;
import org.junit.ClassRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;


public class KafkaTestBase {

    public static Logger LOGGER = LoggerFactory.getLogger("OPERATIONINFO");

    public static final String KAFKA_TOPIC = "junit_topic";

    public static EmbeddedKafkaBroker KAFKA_EMBEDDED;

    @ClassRule
    public static EmbeddedKafkaRule createKafkaEmbedded() {
        EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(1, true, KAFKA_TOPIC){
            @Override
            public void after() {
                // TODO might need to do something with the kafka clean up here
                super.after();
            }
        };
        KAFKA_EMBEDDED = embeddedKafkaRule.getEmbeddedKafka();
        Runtime.getRuntime().addShutdownHook(new Thread(embeddedKafkaRule::after));
        return embeddedKafkaRule;
    }

    @Autowired
    public KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public KafkaTemplate<String, String> template;

    @Before
    public void setUp()
    {
        // set up the Kafka producer properties
        Map<String, Object> senderProperties = KafkaTestUtils.senderProps(KAFKA_EMBEDDED.getBrokersAsString());
        // create a Kafka producer factory
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<String, String>(
                senderProperties);

        // create a Kafka template
        template = new KafkaTemplate<>(producerFactory);
        // set the default topic to send to
        template.setDefaultTopic(KAFKA_TOPIC);

        // wait until the partitions are assigned
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers())
        {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, KAFKA_EMBEDDED.getPartitionsPerTopic());
        }

    }

}
