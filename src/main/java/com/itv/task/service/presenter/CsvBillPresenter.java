package com.itv.task.service.presenter;

import com.itv.task.common.CsvUtils;
import com.itv.task.model.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author enderdincer
 */
public class CsvBillPresenter implements BillPresenter {

  private static final String[] COL_NAMES =
      new String[] {"Item", "Quantity", "Total", "Discounted"};

  /**
   * Saves the bill into a CSV file.
   *
   * @param bill result of the checkout.
   */
  @Override
  public void present(Bill bill) {
    List<String[]> lines = new ArrayList<>();

    lines.add(COL_NAMES);

    lines.addAll(
        bill.getSubTotals().stream()
            .map(
                subTotal ->
                    new String[] {
                      subTotal.getSku(),
                      String.valueOf(subTotal.getNumInBasket()),
                      String.valueOf(subTotal.getBeforeDiscount()),
                      String.valueOf(subTotal.getAfterDiscount())
                    })
            .collect(Collectors.toList()));

    lines.add(
        new String[] {
          "Total:",
          String.valueOf(bill.getTotal()),
          "Total Discount:",
          String.valueOf(bill.getDiscountApplied())
        });

    CsvUtils.write(lines, "bill.csv");
  }
}
