package com.itv.task.provider;

import com.itv.task.repository.LocalItemRepository;
import com.itv.task.repository.LocalPromotionRepository;

import java.util.Objects;

/**
 * Provides singleton implementation of repositories.
 *
 * @author enderdincer
 */
public final class RepositoryProvider {

  private static LocalItemRepository localItemRepository;
  private static LocalPromotionRepository localPromotionRepository;

  public static LocalItemRepository localItemRepository() {

    if (Objects.isNull(localItemRepository)) {
      localItemRepository = new LocalItemRepository();
    }
    return localItemRepository;
  }

  public static LocalPromotionRepository localPromotionRepository() {

    if (Objects.isNull(localPromotionRepository)) {
      localPromotionRepository = new LocalPromotionRepository();
    }

    return localPromotionRepository;
  }
}
