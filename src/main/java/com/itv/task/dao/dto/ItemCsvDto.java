package com.itv.task.dao.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a row in the items CSV file
 *
 * @author enderdincer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCsvDto {

  @CsvBindByPosition(position = 0)
  private String sku;

  @CsvBindByPosition(position = 1)
  private double price;
}
