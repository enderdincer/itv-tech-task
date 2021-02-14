package com.itv.task.service.presenter;

import com.itv.task.model.Bill;

/**
 *
 * @author enderdincer
 */
public class ConsoleBillPresenter implements BillPresenter {

  private static final String BILL_COL_FORMAT = "%s%13s%13s%13s\n";
  private static final String SUB_TOTAL_PRINT_FORMAT = "%s%14d%14.1f%14.1f\n";
  private static final String BILL_HEADER = "\n\n================== BILL ===================";

  /**
   * Formats and prints the bill to the console.
   *
   * @param bill result of the checkout
   */
  @Override
  public void present(Bill bill) {

    header();

    colNames();

    rows(bill);

    finalSum(bill);

  }

  private void header() {
    System.out.println(BILL_HEADER);
  }

  private void colNames(){
    System.out.printf(BILL_COL_FORMAT, "Item", "Quantity", "Total", "Discounted");
    System.out.println("-------------------------------------------");
  }

  private void rows(Bill bill) {
    bill.getSubTotals()
        .forEach(
            subTotal ->
                System.out.printf(
                    SUB_TOTAL_PRINT_FORMAT,
                    subTotal.getSku(),
                    subTotal.getNumInBasket(),
                    subTotal.getBeforeDiscount(),
                    subTotal.getAfterDiscount()));
  }

  private void finalSum(Bill bill) {
    System.out.println("-------------------------------------------");
    System.out.printf("Total: %s  (Total discount applied: %s)\n\n", bill.getTotal(), bill.getDiscountApplied());
  }
}
