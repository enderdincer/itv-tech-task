package com.itv.task.repository;

import com.itv.task.model.Item;

import java.util.Optional;

public interface ItemRepository {

        Optional<Item> find(String sku);
}
