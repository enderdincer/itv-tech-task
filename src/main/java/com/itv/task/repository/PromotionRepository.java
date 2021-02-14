package com.itv.task.repository;

import com.itv.task.model.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository {

  Optional<Promotion> find(String sku);

}
