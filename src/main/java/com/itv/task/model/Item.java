package com.itv.task.model;

import lombok.Builder;
import lombok.Data;

/**
 * Represents an item that is sold in the supermarket.
 *
 * @author enderdincer
 */
@Data
@Builder
public class Item {

  // unique identifier for the item sold
  private String sku;
  // regular unit price
  private double price;
}
