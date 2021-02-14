package com.itv.task.service;

import com.itv.task.model.*;
import com.itv.task.repository.ItemRepository;
import com.itv.task.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Checkout service handles the basket and builds the bill.
 *
 * @author enderdincer
 */
@RequiredArgsConstructor
public class SimpleCheckoutService implements CheckoutService {

  private final ItemRepository itemRepository;
  private final PromotionRepository promotionRepository;


  /**
   * Groups each item to calculate promotions if there's any.
   * Then builds the bill.
   *
   * @param basket
   * @return
   */
  @Override
  public Bill checkout(Basket basket) {

    List<SubTotal> subTotals =
        basket.getSkus().stream()
            .collect(Collectors.groupingBy(sku -> sku, Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> getSubTotal(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

    double total = subTotals.stream().mapToDouble(SubTotal::getAfterDiscount).sum();
    double discountApplied =
        subTotals.stream().mapToDouble(SubTotal::getBeforeDiscount).sum() - total;

    return Bill.builder()
        .subTotals(subTotals)
        .discountApplied(discountApplied)
        .total(total)
        .build();
  }

  private SubTotal getSubTotal(String sku, long numInBasket) {

    Optional<Item> optionalItem = itemRepository.find(sku);

    if (optionalItem.isEmpty()) {
      throw new RuntimeException();
    }

    Item item = optionalItem.get();

    Optional<Promotion> optionalPromotion = promotionRepository.find(sku);

    SubTotal.SubTotalBuilder builder =
        SubTotal.builder()
            .beforeDiscount(numInBasket * item.getPrice())
            .sku(sku)
            .numInBasket(numInBasket);

    long itemsNotEligibleForPromotion = numInBasket;
    double afterDiscount = 0;

    if (optionalPromotion.isPresent() && optionalPromotion.get().getNumOfUnits() < numInBasket) {

      Promotion promotion = optionalPromotion.get();

      long numOfPromotionApplied = numInBasket / promotion.getNumOfUnits();

      itemsNotEligibleForPromotion -= numOfPromotionApplied * promotion.getNumOfUnits();

      afterDiscount += numOfPromotionApplied * promotion.getSpecialTotalPrice();
    }

    afterDiscount += itemsNotEligibleForPromotion * item.getPrice();

    return builder.afterDiscount(afterDiscount).build();
  }
}
