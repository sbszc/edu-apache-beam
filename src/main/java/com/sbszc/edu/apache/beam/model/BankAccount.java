package com.sbszc.edu.apache.beam.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BankAccount implements Serializable {
    private final long number;
    private final String owner;
    private final BigDecimal balance;
}
