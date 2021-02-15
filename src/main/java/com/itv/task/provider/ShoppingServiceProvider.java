package com.itv.task.provider;

import com.itv.task.service.shopping.ConsoleShoppingService;
import com.itv.task.service.shopping.CsvShoppingService;
import com.itv.task.service.shopping.ShoppingService;

import java.util.Objects;
/**
 * Provides singleton implementations of ShoppingService depending on InputOutputType.
 *
 * @author enderdincer
 */
public final class ShoppingServiceProvider {

  private ShoppingServiceProvider() {}

  private static CsvShoppingService csvShoppingService;
  private static ConsoleShoppingService consoleShoppingService;

  public static ShoppingService get(InputOutputType type) {
    switch (type) {
      case CONSOLE:
        return getConsoleShoppingService();
      case CSV:
        return getCsvShopping();
      default:
        return getConsoleShoppingService();
    }
  }

  private static CsvShoppingService getCsvShopping() {
    if (Objects.isNull(csvShoppingService)) {
      csvShoppingService = new CsvShoppingService();
    }
    return csvShoppingService;
  }

  private static ConsoleShoppingService getConsoleShoppingService() {
    if (Objects.isNull(consoleShoppingService)) {
      consoleShoppingService = new ConsoleShoppingService();
    }
    return consoleShoppingService;
  }
}
