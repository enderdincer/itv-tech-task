package com.itv.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * After each shopping, chosen items are collected into the Basket with their SKUs.
 *
 * @author enderdincer
 */
@Data
@Builder
@AllArgsConstructor
public class Basket {
  private List<String> skus;
}
