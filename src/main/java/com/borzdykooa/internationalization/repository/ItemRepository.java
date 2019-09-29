package com.borzdykooa.internationalization.repository;

import com.borzdykooa.internationalization.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByCode(Integer code);
}
