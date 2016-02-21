package com.bravofly.salestaxes.business.concrete.drools;

import java.io.Serializable;

import com.bravofly.salestaxes.model.Good;

/**
 * Fact for {@link Good}
 * 
 * @author guido.campopiano
 *
 */

public class GoodControlFact implements Serializable {

	private static final long serialVersionUID = 9082986075252370279L;

	private Good theGood;

	private boolean basicSalesTaxApplied;

	private boolean importDutySalexTaxApplied;

	public GoodControlFact(Good theGood) {
		super();
		this.theGood = theGood;
	}

	public Good getTheGood() {
		return theGood;
	}

	public void setTheGood(Good theGood) {
		this.theGood = theGood;
	}

	public boolean isBasicSalesTaxApplied() {
		return basicSalesTaxApplied;
	}

	public void setBasicSalesTaxApplied(boolean basicSalesTaxApplied) {
		this.basicSalesTaxApplied = basicSalesTaxApplied;
	}

	public boolean isImportDutySalexTaxApplied() {
		return importDutySalexTaxApplied;
	}

	public void setImportDutySalexTaxApplied(boolean importDutySalexTaxApplied) {
		this.importDutySalexTaxApplied = importDutySalexTaxApplied;
	}

}
