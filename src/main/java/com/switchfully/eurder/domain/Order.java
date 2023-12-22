package com.switchfully.eurder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;
	@Column(name = "total_price")
	private double totalPrice;
	@OneToMany(mappedBy = "order")
	private List<ItemGroup> itemGroups;
	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	private User user;

	public Order() {
	}

	public Order(User user) {
		this.user = user;
		itemGroups = new ArrayList<>();
		this.totalPrice = 0;
	}

	public Long getOrderId() {
		return orderId;
	}

	public List<ItemGroup> getItemGroups() {
		return itemGroups;
	}

	public User getUser() {
		return user;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void increaseTotalPrice(double amount) {
		this.totalPrice += amount;
	}

	public void addItemGroup(ItemGroup itemGroup){
		itemGroups.add(itemGroup);
		totalPrice += itemGroup.getAmount() * itemGroup.getItem().getPrice();
	}
}
