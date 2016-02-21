package com.bravofly.salestaxes.business;

import com.bravofly.salestaxes.SalesTaxesException;
import com.bravofly.salestaxes.model.concrete.Receipt;

public interface SalesTaxesBusinessService {

	public void compileReceipt(Receipt theReceipt) throws SalesTaxesException;

}