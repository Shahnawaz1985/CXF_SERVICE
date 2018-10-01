package com.bac.cxf.service.provider;

import javax.xml.ws.Endpoint;

public class TestServer {
	
	protected TestServer() {
		System.out.println("Starting Server...");
		Object implementor = new OrderProcessDOMProvider();
		String address = "http://localhost:8787/OrderProcessDOMProvider";
		Endpoint.publish(address, implementor);
	}
	
	public static void main(String[] args) {
		new TestServer();
		System.out.println("Server Ready");
	}

}
