package com.bac.cxf.service.provider;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServiceProvider()
@ServiceMode(value = Service.Mode.MESSAGE)
public class OrderProcessDOMProvider implements Provider<DOMSource> {
	
	public OrderProcessDOMProvider() {
		
	}

	@Override
	public DOMSource invoke(DOMSource request) {
		DOMSource response = new DOMSource();
		try {
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage soapReq = factory.createMessage();
			soapReq.getSOAPPart().setContent(request);
			System.out.println("Incoming Client Request as a DOMSource data in MESSAGE Mode");
			soapReq.writeTo(System.out);
			System.out.println("\n");
			
			Node processOrderNode = soapReq.getSOAPBody().getFirstChild();
			Node orderNode = processOrderNode.getChildNodes().item(0);
			
			NodeList nodeList = orderNode.getChildNodes();
			for(int i=0; i<nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getNodeName() + " = "+
							nodeList.item(i).getTextContent());
			}
			
			SOAPMessage orderResponse = factory.createMessage();
			QName processOrderQName = new QName("http://provider.service.cxf.bac.com/", "processOrder");
			QName responseQName = new QName("http://provider.service.cxf.bac.com/", "processOrderResponse");
			
			SOAPElement processOrderResponse = orderResponse.getSOAPBody().addChildElement(processOrderQName);
			processOrderResponse.addChildElement(responseQName).addTextNode("ORD_DOM_PROV123");
			response.setNode(orderResponse.getSOAPPart());
			
		}catch(Exception ex) {
			
		}
		return response;
	}

}
