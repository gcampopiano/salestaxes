package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;

import com.bravofly.salestaxes.model.BasicTaxApplicableGood;
import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.ImportDutyTaxApplicableGood;

public class ImportedPerfume extends Good implements BasicTaxApplicableGood, ImportDutyTaxApplicableGood {

	private static final long serialVersionUID = 6366853898972700698L;

	public ImportedPerfume(BigDecimal rawPrice) {
		super(rawPrice);
	}

}
