package com.itv.task.dao;

import com.itv.task.common.CsvUtils;
import com.itv.task.dao.dto.CsvMapper;
import com.itv.task.dao.dto.ItemCsvDto;
import com.itv.task.dao.dto.PromotionCsvDto;
import com.itv.task.model.Item;
import com.itv.task.model.Promotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A simple in memory database access object
 *
 * @author enderdincer
 */
public class Dao {

  private static final Map<String, Item> LOCAL_ITEMS = new HashMap<>();
  private static final Map<String, Promotion> LOCAL_PROMOTIONS = new HashMap<>();

  public static Optional<Item> findItem(String sku) {

    if (LOCAL_ITEMS.isEmpty()) {
      populateItems();
    }

    if (LOCAL_ITEMS.containsKey(sku)) {
      return Optional.of(LOCAL_ITEMS.get(sku));
    }

    return Optional.empty();
  }

  public static Optional<Promotion> findPromotion(String sku) {

    if (LOCAL_PROMOTIONS.isEmpty()) {
      populatePromotions();
    }

    if (LOCAL_PROMOTIONS.containsKey(sku)) {
      return Optional.of(LOCAL_PROMOTIONS.get(sku));
    }

    return Optional.empty();
  }

  /**
   * Caches the csv file to the static map (LOCAL_ITEMS)
   */
  private static void populateItems() {
    CsvUtils.read(ItemCsvDto.class, "/items.csv")
        .forEach(itemDto -> LOCAL_ITEMS.put(itemDto.getSku(), CsvMapper.toModel(itemDto)));
  }

  /**
   * Caches the csv file to the static map (LOCAL_PROMOTIONS)
   */
  private static void populatePromotions() {
    CsvUtils.read(PromotionCsvDto.class, "/promotions.csv")
        .forEach(promDto -> LOCAL_PROMOTIONS.put(promDto.getSku(), CsvMapper.toModel(promDto)));
  }
}
