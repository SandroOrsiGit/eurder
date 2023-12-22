package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/order")
public class OrderController {
	OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public List<OrderDto> getOrders(){
		return orderService.getOrders();
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDto createOrder(@RequestParam Long userId){
		return orderService.createOrder(userId);
	}

	@PutMapping(path = "{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto addItemToOrder(@PathVariable Long orderId, @RequestParam Long itemId, @RequestParam int amount){
		return orderService.addItemToOrder(orderId, itemId, amount);
	}
}
