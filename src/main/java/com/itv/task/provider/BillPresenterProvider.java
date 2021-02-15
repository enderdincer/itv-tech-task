package com.itv.task.provider;

import com.itv.task.service.presenter.BillPresenter;
import com.itv.task.service.presenter.ConsoleBillPresenter;
import com.itv.task.service.presenter.CsvBillPresenter;

import java.util.Objects;

/**
 * Provides singleton implementations of BillPresenter depending on the InputOutputType.
 *
 * @author enderdincer
 */
public final class BillPresenterProvider {

  private BillPresenterProvider() {}

  private static ConsoleBillPresenter consoleBillPresenter;
  private static CsvBillPresenter csvBillPresenter;

  public static BillPresenter get(InputOutputType type) {
    switch (type) {
      case CSV:
        return getCsvBillPresenter();
      case CONSOLE:
        return getConsoleBillPresenter();
      default:
        return getConsoleBillPresenter();
    }
  }

  private static CsvBillPresenter getCsvBillPresenter() {
    if (Objects.isNull(csvBillPresenter)) {
      csvBillPresenter = new CsvBillPresenter();
    }
    return csvBillPresenter;
  }

  private static ConsoleBillPresenter getConsoleBillPresenter() {
    if (Objects.isNull(consoleBillPresenter)) {
      consoleBillPresenter = new ConsoleBillPresenter();
    }
    return consoleBillPresenter;
  }
}
