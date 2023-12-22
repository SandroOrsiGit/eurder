package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import com.switchfully.eurder.exceptions.StockTooLowException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findAll();

	Optional<Item> findItemByItemId(Long itemId);

}
