package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
	public OrderDto mapOrderToOrderDto(Order order){
		return new OrderDto(order.getOrderId(), order.getTotalPrice(), order.getUser(), order.getItemGroups());
	}
}
