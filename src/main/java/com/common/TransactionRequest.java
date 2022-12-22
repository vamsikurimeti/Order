package com.common;

import com.entity.Order;

import lombok.Data;

@Data
public class TransactionRequest {
	private Order order;
	private Payment payment;
}
