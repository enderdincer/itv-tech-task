package com.itv.task.service.shopping;

import com.itv.task.model.Basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** @author enderdincer */
public class ConsoleShoppingService implements ShoppingService {

  /**
   * Starts a loop that accepts user inputs from the console until "done" is entered.
   *
   * @return items put into the basket during shopping.
   */
  @Override
  public Basket doShopping() {
    List<String> items = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();

    while (!input.equalsIgnoreCase("done") && scanner.hasNext()) {
      items.add(input);
      input = scanner.nextLine().toUpperCase();
    }

    return Basket.builder().skus(items).build();
  }
}
