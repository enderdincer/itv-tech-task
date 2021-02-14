package com.itv.task;

import com.itv.task.model.Basket;
import com.itv.task.model.Bill;
import com.itv.task.repository.LocalItemRepository;
import com.itv.task.repository.LocalPromotionRepository;
import com.itv.task.service.CheckoutService;
import com.itv.task.service.SimpleCheckoutService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

public class SimpleCheckoutServiceTest {

  private CheckoutService checkoutService;

  @BeforeEach
  public void setup() {
    checkoutService =
        new SimpleCheckoutService(new LocalItemRepository(), new LocalPromotionRepository());
  }

  public static Stream<? extends Arguments> checkoutDataSet() {
    return Stream.of(
        Arguments.of(List.of("A", "A", "D", "A", "A", "B", "A", "C", "D", "A"), 340L, 40L),
        Arguments.of(List.of("C", "A", "D", "A"), 135L, 0L),
        Arguments.of(List.of("B", "A", "D", "A", "B", "B", "A", "B"), 255L, 30L),
        Arguments.of(List.of(), 0L, 0L)
    );
  }

  @ParameterizedTest(name = "{index} => items={0}, total={1}, discountApplied={2}")
  @MethodSource("checkoutDataSet")
  public void checkoutTest(List<String> items, long total, long discountApplied) {
    Basket testBasket = new Basket(items);

    Bill testBill = checkoutService.checkout(testBasket);

    Assertions.assertNotNull(testBill);

    Assertions.assertEquals(total, testBill.getTotal());

    Assertions.assertEquals(discountApplied, testBill.getDiscountApplied());

    Assertions.assertEquals(new HashSet<String>(items).size(), testBill.getSubTotals().size());
  }
}
