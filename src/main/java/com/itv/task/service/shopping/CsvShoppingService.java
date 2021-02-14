package com.itv.task.service.shopping;

import com.itv.task.common.CsvUtils;
import com.itv.task.model.Basket;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author enderdincer
 */
public class CsvShoppingService implements ShoppingService {

  /**
   * Reads a csv file to populate the basket
   */
  @Override
  public Basket doShopping() {
    return Basket.builder()
        .skus(
            CsvUtils.readLines("/basket.csv").stream()
                .filter(Objects::nonNull)
                .filter(arr -> arr.length > 0)
                .map(arr -> arr[0].toUpperCase())
                .collect(Collectors.toList()))
        .build();
  }
}
