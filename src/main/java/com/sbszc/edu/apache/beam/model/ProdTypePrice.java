package com.sbszc.edu.apache.beam.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProdTypePrice implements Serializable {
    private final String productType;
    private final BigDecimal price;
}
