package com.bac.config.cxf.service;

import java.util.UUID;

import javax.jws.WebService;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.bac.config.cxf.pojo.Order;

@WebService(endpointInterface="com.bac.config.cxf.service.OrderProcess")
public class OrderProcessImpl implements OrderProcess {

	/* (non-Javadoc)
	 * @see com.bac.config.cxf.service.OrderProcess#processOrder(com.bac.config.cxf.pojo.Order)
	 */
	@Override
	public String processOrder(Order order) {
		String orderID = validateAndProcess(order);
		return orderID;
	}

	/**
	 * @param order
	 * @return
	 */
	private String validateAndProcess(Order order) {
		String guid = null;
		if(ObjectUtils.allNotNull(order)) {
			if(StringUtils.isNotEmpty(order.getCustomerID()) && StringUtils.isNotEmpty(order.getItemID())
				&& order.getPrice() > 0.0 && order.getQunatity() > 0){
				UUID uuid1 = UUID.randomUUID();
				guid = uuid1.toString();
			}
		}
		return guid;
	}

}
