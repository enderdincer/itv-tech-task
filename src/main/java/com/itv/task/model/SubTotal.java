package com.itv.task.model;

import lombok.Builder;
import lombok.Data;

/**
 * An intermediate object to group same sku items and their discount data.
 *
 * @author enderdincer
 */
@Data
@Builder
public class SubTotal {

    private String sku;
    private long numInBasket;
    private double beforeDiscount;
    private double afterDiscount;
}
