package com.itv.task.model;

import lombok.Builder;
import lombok.Data;

/**
 * Describes a promotion.
 *
 * <p>For example: An item, say sku = "B" has a promotion of "buy 3 pay 150" where the
 * individual price is 60. This would be Promotion(sku=B, numOfUnits=3, specialTotalPrice=150)
 *
 * @author enderdincer
 */
@Data
@Builder
public class Promotion {

  private String sku;
  private long numOfUnits;
  private double specialTotalPrice;
}
