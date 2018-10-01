package com.bac.test.servie.model;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;

public class ServiceModelFrameworkTest {
	
	public ServiceModelFrameworkTest() {
		
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, 
								IntrospectionException, IllegalArgumentException, InvocationTargetException, Exception {
		JaxWsDynamicClientFactory dynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = dynamicClientFactory.createClient("http://localhost:8282/services/OrderProcess?wsdl");
		Endpoint endPoint = client.getEndpoint();
		ServiceInfo serviceInfo = endPoint.getService().getServiceInfos().get(0);
		QName bindingName = new QName("http://service.cxf.config.bac.com/", "OrderProcessImplServiceSoapBinding");
		BindingInfo bindingInfo = serviceInfo.getBinding(bindingName);
		QName opName = new QName("http://service.cxf.config.bac.com/", "processOrder");
		BindingOperationInfo bindingOperationInfo = bindingInfo.getOperation(opName);
		BindingMessageInfo inputMessageInfo = null;
		if(!bindingOperationInfo.isUnwrapped()) {
			inputMessageInfo = bindingOperationInfo.getWrappedOperation().getInput();
		}else {
			inputMessageInfo = bindingOperationInfo.getUnwrappedOperation().getInput();
		}
		List<MessagePartInfo> parts = null;
		if(inputMessageInfo != null) {
			parts = inputMessageInfo.getMessageParts();
		}
		MessagePartInfo partInfo = null;
		if(null != parts && parts.size()>0) {
			partInfo = parts.get(0);
		}
		Class<?> orderClass = null;
		if(null != partInfo) {
			orderClass = partInfo.getTypeClass();
		}
		Object orderObject = null != orderClass?orderClass.newInstance():null;
		
		PropertyDescriptor custProperty = new PropertyDescriptor("customerID", orderClass);
		custProperty.getWriteMethod().invoke(orderObject, "CUST-1");
		
		
		PropertyDescriptor itemProperty = new PropertyDescriptor("itemID", orderClass);
		itemProperty.getWriteMethod().invoke(orderObject, "I001");
		
		PropertyDescriptor priceProperty = new PropertyDescriptor("price", orderClass);
		priceProperty.getWriteMethod().invoke(orderObject, Double.valueOf(100.00));
		
		PropertyDescriptor quantityProperty = new PropertyDescriptor("qunatity", orderClass);
		quantityProperty.getWriteMethod().invoke(orderObject, Integer.valueOf(20));
		
		Object[] result = client.invoke(opName, orderObject);
		System.out.println("Order Id is :"+result[0]);
	}

}
