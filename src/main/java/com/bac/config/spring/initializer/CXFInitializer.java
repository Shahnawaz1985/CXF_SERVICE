package com.bac.config.spring.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.bac.config.spring.configuration.CXFServiceConfiguration;

public class CXFInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(CXFServiceConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(webContext));
		ServletRegistration.Dynamic dispatcherServlet
		  = servletContext.addServlet("dispatcher", new CXFServlet());
		dispatcherServlet.addMapping("/services/*");

	}

}
