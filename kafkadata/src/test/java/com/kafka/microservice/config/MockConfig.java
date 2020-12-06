package com.kafka.microservice.config;

import javax.sql.DataSource;
import org.mockito.Mockito;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class MockConfig {


	@Bean
	public DataSource db2DataSource() {
		return Mockito.mock(DataSource.class);
	}

	@Bean
	public DataSource mySqlDataSource() { return Mockito.mock(DataSource.class); }

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("kafkaConsumerCache");
	}

}
