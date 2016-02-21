package com.bravofly.salestaxes.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.bravofly.salestaxes.Constants;

/**
 * The basic pojo for all goods
 * 
 * @author guido.campopiano
 *
 */
public abstract class Good extends SalesTaxesModel {

	private static final long serialVersionUID = 6899808115954438852L;

	protected BigDecimal rawPrice;

	protected BigDecimal taxedPrice;

	protected BigDecimal appliedTaxesAmount;

	public Good(BigDecimal rawPrice) {
		super();
		this.rawPrice = rawPrice;
		this.taxedPrice = this.rawPrice;
		this.appliedTaxesAmount = new BigDecimal(Constants.ZERO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return StringUtils.join("{", this.getClass().getCanonicalName(), StringUtils.SPACE, this.rawPrice,
				StringUtils.SPACE, this.taxedPrice, "}");
	}

	public BigDecimal getRawPrice() {
		return rawPrice;
	}

	public void setRawPrice(BigDecimal rawPrice) {
		this.rawPrice = rawPrice;
	}

	public BigDecimal getTaxedPrice() {
		return taxedPrice;
	}

	public void setTaxedPrice(BigDecimal taxedPrice) {
		this.taxedPrice = taxedPrice;
	}

	public BigDecimal getAppliedTaxesAmount() {
		return appliedTaxesAmount;
	}

	public void setAppliedTaxesAmount(BigDecimal appliedTaxesAmount) {
		this.appliedTaxesAmount = appliedTaxesAmount;
	}

}
