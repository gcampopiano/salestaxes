package com.bravofly.salestaxes.business.concrete.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxesUtils implements Serializable {

	private static final long serialVersionUID = -1497329728846244969L;

	private final static BigDecimal NEAREST = new BigDecimal("0.05");

	private final static BigDecimal TAX_MULTIPLIER_BASIC = new BigDecimal("10");

	private final static BigDecimal TAX_MULTIPLIER_IMPORT_DUTY = new BigDecimal("5");

	private final static BigDecimal roundUpToNearest(BigDecimal value) {
		BigDecimal divided = value.divide(NEAREST, 0, RoundingMode.UP);
		BigDecimal result = divided.multiply(NEAREST);
		return result;
	}

	public static BigDecimal calculateBasicTaxAmount(BigDecimal rawPrice) {
		BigDecimal calculatedTaxAmount = rawPrice.multiply(TAX_MULTIPLIER_BASIC).divide(new BigDecimal("100"));
		return roundUpToNearest(calculatedTaxAmount);
	}

	public static BigDecimal calculateImportDutyTaxAmount(BigDecimal rawPrice) {
		BigDecimal calculatedTaxAmount = rawPrice.multiply(TAX_MULTIPLIER_IMPORT_DUTY).divide(new BigDecimal(100));
		return roundUpToNearest(calculatedTaxAmount);
	}

}
