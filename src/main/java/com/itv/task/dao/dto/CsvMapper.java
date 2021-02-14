package com.itv.task.dao.dto;

import com.itv.task.model.Item;
import com.itv.task.model.Promotion;

/**
 * Simple DTO to model mapper
 *
 * @author enderdincer
 */
public final class CsvMapper {

  private CsvMapper() {}

  public static Item toModel(ItemCsvDto dto) {
    return Item.builder().price(dto.getPrice()).sku(dto.getSku()).build();
  }

  public static Promotion toModel(PromotionCsvDto dto) {
    return Promotion.builder()
        .numOfUnits(dto.getNumOfUnits())
        .sku(dto.getSku())
        .specialTotalPrice(dto.getSpecialTotalPrice())
        .build();
  }
}
