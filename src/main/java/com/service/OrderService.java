package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.common.Payment;
import com.common.TransactionRequest;
import com.common.TransactionResponse;
import com.dao.OrderRepository;
import com.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {

	Logger logger= LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public RestTemplate template;

	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
		String response="";
		Order order=request.getOrder();
	    Payment payment=request.getPayment();
	    payment.setOrderId(order.getId());
	    payment.setAmount(order.getPrice());
	    
	    //logging
	    logger.info("Order-Service Request :"+new ObjectMapper().writeValueAsString(request));
	    
	    //calling our rest template
	    Payment paymentResponse=template.postForObject("http://PAYMENT-SERVICE/payment/initiateTransaction", payment, Payment.class);
	    
	    //logging
	    logger.info("Payment Service response for Order-Service Rest Request :"+new ObjectMapper().writeValueAsString(paymentResponse));
	    response= paymentResponse.getPaymentStatus()
				.equals("success")?"payment processed sucessfully and your order has been placed"
						: "there is s failure in your payment please retry after sometime";

	    orderRepository.save(order);
	     return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	
	}
}
