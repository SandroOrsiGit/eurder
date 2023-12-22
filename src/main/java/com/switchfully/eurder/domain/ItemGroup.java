package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.dto.ItemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "item_groups")
public class ItemGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemGroupId;
	private int amount;

	@Column(name = "shipping_date")
	private LocalDate shippingDate;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "fk_order_id")
	private Order order;

	public ItemGroup() {
	}

	public ItemGroup(int amount, boolean isInStock, Item item, Order order) {
		this.amount = amount;
		this.shippingDate = LocalDate.now().plusDays(isInStock ? 1 : 7);
		this.item = item;
		this.order = order;
	}

	public Long getItemGroupId() {
		return itemGroupId;
	}
	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

}
