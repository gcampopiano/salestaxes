package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;

import com.bravofly.salestaxes.model.BasicTaxApplicableGood;
import com.bravofly.salestaxes.model.Good;

public class Perfume extends Good implements BasicTaxApplicableGood {

	private static final long serialVersionUID = 6946723152526480407L;

	public Perfume(BigDecimal rawPrice) {
		super(rawPrice);
	}

}
