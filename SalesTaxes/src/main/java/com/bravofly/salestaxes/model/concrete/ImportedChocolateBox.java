package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;

import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.ImportDutyTaxApplicableGood;

public class ImportedChocolateBox extends Good implements ImportDutyTaxApplicableGood {

	private static final long serialVersionUID = 8773165592840786662L;

	public ImportedChocolateBox(BigDecimal rawPrice) {
		super(rawPrice);
	}

}
