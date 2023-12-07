package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.service.ItemService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemMapper itemMapper;

	private CreateItemDto createItemDto;

	@BeforeEach
	void init() {
		createItemDto = new CreateItemDto("Bed", "king sized bed", 300, 3);
	}

	@Test
	void whenCreateItem_thenReturnCreatedAndPopulateRepository(){
		ItemDto result = given()
				.contentType(ContentType.JSON)
				.baseUri("http://localhost")
				.port(port)
				.when()
				.body(createItemDto)
				.post("/api/item")
				.then()
				.assertThat()
				.statusCode(201)
				.extract()
				.as(ItemDto.class);

		assertThat(itemRepository.getItems()
				.stream()
				.filter(item -> item.getItemId().equals(result.getItemId()))
				.collect(Collectors.toList()))
				.isNotEmpty();
	}
}