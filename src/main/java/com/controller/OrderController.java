package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.Payment;
import com.common.TransactionRequest;
import com.common.TransactionResponse;
import com.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	public OrderService orderService;
	
	@PostMapping("/saveorder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
		return orderService.saveOrder(transactionRequest);
	}
}
