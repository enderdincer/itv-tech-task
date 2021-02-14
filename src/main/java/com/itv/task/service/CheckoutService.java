package com.itv.task.service;

import com.itv.task.model.Basket;
import com.itv.task.model.Bill;

public interface CheckoutService {


    Bill checkout(Basket basket);
}
