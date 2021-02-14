package com.itv.task.repository;

import com.itv.task.dao.Dao;
import com.itv.task.model.Item;

import java.util.Optional;

/**
 * Uses the in-memory database can be replaced with real repository that retrieves data from a real
 * database.
 *
 * @author enderdincer
 */
public class LocalItemRepository implements ItemRepository {

  @Override
  public Optional<Item> find(String sku) {
    return Dao.findItem(sku);
  }
}
