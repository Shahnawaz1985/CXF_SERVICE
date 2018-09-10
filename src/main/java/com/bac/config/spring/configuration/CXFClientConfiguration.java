package com.bac.config.spring.configuration;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bac.config.cxf.service.OrderProcess;

@Configuration()
public class CXFClientConfiguration {
	
	@Bean
	public JaxWsProxyFactoryBean proxyFactoryBean() {
	    JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
	    proxyFactory.setServiceClass(OrderProcess.class);
	    proxyFactory.setAddress("http://localhost:8282/services/OrderProcess");
	    return proxyFactory;
	}
	
	@Bean(name = "client")
	public Object generateProxy() {
	    return proxyFactoryBean().create();
	}

}
