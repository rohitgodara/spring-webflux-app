package com.catapi.configuration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

/**
 * @author rohit.godara
 *
 */
@Configuration
@ConfigurationProperties(prefix = "r2dbc")
public class AppConfiguration extends AbstractR2dbcConfiguration {

	private String driver;
	private String host;
	private int port;
	private String user;
	private String password;
	private String name;
	private int poolMaxSize;

	@Override
	protected List<Object> getCustomConverters() {
		List<Converter<?, ?>> converterList = new ArrayList<>();
		converterList.add(new UUIDToByteArrayConverter());
		converterList.add(new ByteArrayToUUIDConverter());
		return Collections.unmodifiableList(converterList);
	}

	@Profile("dev")
	@Bean("connectionFactory")
	public ConnectionFactory connectionFactory() {
		return ConnectionFactories
				.get(ConnectionFactoryOptions.builder().option(ConnectionFactoryOptions.DRIVER, driver)
						.option(ConnectionFactoryOptions.HOST, host).option(ConnectionFactoryOptions.PORT, port)
						.option(ConnectionFactoryOptions.USER, user).option(ConnectionFactoryOptions.PASSWORD, password)
						.option(ConnectionFactoryOptions.DATABASE, name).option(MAX_SIZE, poolMaxSize).build());
	}

	@Profile("prod")
	@Bean("connectionFactory")
	public ConnectionFactory prodConnectionFactory() {
		return ConnectionFactories
				.get(ConnectionFactoryOptions.builder().option(ConnectionFactoryOptions.DRIVER, driver)
						.option(ConnectionFactoryOptions.HOST, host).option(ConnectionFactoryOptions.PORT, port)
						.option(ConnectionFactoryOptions.USER, user).option(ConnectionFactoryOptions.PASSWORD, password)
						.option(ConnectionFactoryOptions.DATABASE, name).option(MAX_SIZE, poolMaxSize).build());
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}


}
