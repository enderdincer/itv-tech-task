package com.itv.task;

import com.itv.task.model.Basket;
import com.itv.task.model.Bill;
import com.itv.task.provider.BillPresenterProvider;
import com.itv.task.provider.CheckoutServiceProvider;
import com.itv.task.provider.InputOutputType;
import com.itv.task.provider.ShoppingServiceProvider;
import com.itv.task.service.CheckoutService;
import com.itv.task.service.presenter.BillPresenter;
import com.itv.task.service.shopping.ShoppingService;

public class SuperMarket {

  public static void main(String[] args) {

    ShoppingService shoppingService = ShoppingServiceProvider.get(InputOutputType.CSV);

    Basket basket = shoppingService.doShopping();

    CheckoutService checkoutService = CheckoutServiceProvider.simpleCheckoutService();

    Bill bill = checkoutService.checkout(basket);

    BillPresenter billPresenter = BillPresenterProvider.get(InputOutputType.CSV);

    billPresenter.present(bill);
  }
}
