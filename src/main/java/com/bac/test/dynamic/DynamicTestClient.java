package com.bac.test.dynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class DynamicTestClient {
	
	public DynamicTestClient() {
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, 
							NoSuchMethodException, SecurityException, 
							IllegalArgumentException, InvocationTargetException, Exception {
		
		JaxWsDynamicClientFactory dynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = dynamicClientFactory.createClient("http://localhost:8282/services/OrderProcess?wsdl");
		
		Object order = Thread.currentThread().getContextClassLoader().loadClass("com.bac.config.cxf.service.Order").newInstance();
		
		Method[] methodRefs = order.getClass().getDeclaredMethods();
		for(Method m : methodRefs) {
			System.out.println(m.getName());
		}
		Method m1 = order.getClass().getMethod("setCustomerID", String.class);
		Method m2 = order.getClass().getMethod("setItemID", String.class);
		Method m4 = order.getClass().getMethod("setPrice", double.class);
		Method m3 = order.getClass().getMethod("setQunatity", int.class);
		
		m1.invoke(order, "C002");
		m2.invoke(order, "I002");
		m3.invoke(order, 1000);
		m4.invoke(order, 50.0);
		
		Object[] response = client.invoke("processOrder", order);
		System.out.println("Response is :"+response[0]);
		
	}

}
