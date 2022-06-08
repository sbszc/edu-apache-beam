package com.sbszc.edu.apache.beam.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Transaction implements Serializable {
    private final Long id;
    private final String customerType;
    private final String productType;
    private final BigDecimal price;
}
