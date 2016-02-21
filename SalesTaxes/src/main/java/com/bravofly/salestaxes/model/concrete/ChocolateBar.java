package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;

import com.bravofly.salestaxes.model.Good;

public class ChocolateBar extends Good {

	private static final long serialVersionUID = 22061044640231331L;

	public ChocolateBar(BigDecimal rawPrice) {
		super(rawPrice);
	}

}
