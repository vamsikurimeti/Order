package com.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="orderTable")
public class Order {
	
	@Id
	private int id;
	private String name;
	private String quantity;
	private int price;

}
