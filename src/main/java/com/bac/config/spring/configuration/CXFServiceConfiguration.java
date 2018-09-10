package com.bac.config.spring.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bac.config.cxf.service.OrderProcessImpl;

@Configuration
public class CXFServiceConfiguration {
	
	@Bean
	public SpringBus springBus() {
	    return new SpringBus();
	}
	
	
	@Bean
	public Endpoint endpoint() {
	    EndpointImpl endpoint = new EndpointImpl(springBus(), new OrderProcessImpl());
	    endpoint.publish("http://localhost:8282/services/OrderProcess");
	    return endpoint;
	}

}
