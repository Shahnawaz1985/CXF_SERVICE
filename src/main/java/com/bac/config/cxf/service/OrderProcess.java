package com.bac.config.cxf.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.bac.config.cxf.pojo.Order;

@WebService
public interface OrderProcess {
	
	@WebMethod
	String processOrder(Order order);
}
