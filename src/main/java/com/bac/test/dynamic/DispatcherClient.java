package com.bac.test.dynamic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;

public class DispatcherClient {
	
	public static final String WSDL_LOCATION = "http://localhost:8787/OrderProcessDOMProvider?wsdl";
	
	public DispatcherClient() {
		
	}

	public static void main(String[] args) throws MalformedURLException, SOAPException, IOException {
		URL wsdlURL = new URL(WSDL_LOCATION);
		MessageFactory factory = MessageFactory.newInstance();
		
		QName domProvider = new QName("http://provider.service.cxf.bac.com/", "OrderProcessDOMProviderService");
		QName portName = new QName("http://provider.service.cxf.bac.com/", "OrderProcessDOMProviderPort");
		
		Service service = Service.create(wsdlURL, domProvider);
		SOAPMessage soapRequest = factory.createMessage();
		QName processOrderQName = new QName("http://provider.service.cxf.bac.com/", "processOrder");
		
		SOAPElement processOrderResponse = soapRequest.getSOAPBody().addChildElement(processOrderQName);
		SOAPElement order = processOrderResponse.addChildElement("processOrderResponse");
		order.addChildElement("customerID").addTextNode("CUST05");
		order.addChildElement("itemID").addTextNode("I001");
		order.addChildElement("price").addTextNode("200.00");
		order.addChildElement("quantity").addTextNode("10");
		
		DOMSource domRequestMessage = new DOMSource(soapRequest.getSOAPPart());
		Dispatch<DOMSource> domMsg = service.createDispatch(portName, DOMSource.class, Mode.MESSAGE);
		DOMSource domResponseMsg = domMsg.invoke(domRequestMessage);
		
		System.out.println("Client Request as DOMSource data:");
		soapRequest.writeTo(System.out);
		System.out.println("\n");
		
		System.out.println("Response from server:"+domResponseMsg.getNode().getLastChild().getTextContent());
		
	}
}
