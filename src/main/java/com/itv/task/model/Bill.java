package com.itv.task.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Bill holds the final amount that must be paid as well as other statistical data.
 *
 * @author enderdincer
 */
@Data
@Builder
public class Bill {

  private List<SubTotal> subTotals;
  private double discountApplied;
  private double total;
}
