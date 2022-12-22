package com.common;
import com.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {

	
	private Order order;
	private double amount;
	private String transactionId;
	private String message;
}
