package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;

import com.bravofly.salestaxes.model.BasicTaxApplicableGood;
import com.bravofly.salestaxes.model.Good;

public class MusicCD extends Good implements BasicTaxApplicableGood {

	private static final long serialVersionUID = -8182024781345848677L;

	public MusicCD(BigDecimal rawPrice) {
		super(rawPrice);
	}

}
