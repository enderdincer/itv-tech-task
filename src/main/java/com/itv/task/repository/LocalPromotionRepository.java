package com.itv.task.repository;

import com.itv.task.dao.Dao;
import com.itv.task.model.Promotion;

import java.util.*;
/**
 * Uses the in-memory database, can be replaced with real repository that retrieves data from a real
 * database.
 *
 * @author enderdincer
 */
public class LocalPromotionRepository implements PromotionRepository {

  @Override
  public Optional<Promotion> find(String sku) {

    return Dao.findPromotion(sku);
  }
}
