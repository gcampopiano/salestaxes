package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;

import com.bravofly.salestaxes.model.Good;

public class Book extends Good {

	private static final long serialVersionUID = 5325071812835369296L;

	public Book(BigDecimal rawPrice) {
		super(rawPrice);
	}

}
