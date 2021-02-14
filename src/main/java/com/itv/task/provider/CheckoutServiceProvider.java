package com.itv.task.provider;

import com.itv.task.service.CheckoutService;
import com.itv.task.service.SimpleCheckoutService;

import java.util.Objects;

/**
 * Provides singleton implementation of CheckoutService.
 *
 * @author enderdincer
 */
public final class CheckoutServiceProvider {

  private CheckoutServiceProvider() {}

  private static SimpleCheckoutService simpleCheckoutService;

  public static CheckoutService simpleCheckoutService() {

    if (Objects.isNull(simpleCheckoutService)) {
      simpleCheckoutService =
          new SimpleCheckoutService(
              RepositoryProvider.localItemRepository(),
              RepositoryProvider.localPromotionRepository());
    }

    return simpleCheckoutService;
  }
}
