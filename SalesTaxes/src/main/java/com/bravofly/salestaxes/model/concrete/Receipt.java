package com.bravofly.salestaxes.model.concrete;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.bravofly.salestaxes.Constants;
import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.SalesTaxesModel;

public class Receipt extends SalesTaxesModel {

	private static final long serialVersionUID = -2913006575226066653L;

	private Map<String, List<Good>> goodMap;

	private BigDecimal totalSalesTaxes;

	private BigDecimal totalAmount;

	public Receipt() {
		super();
		this.goodMap = new HashMap<>();
		this.totalSalesTaxes = new BigDecimal(Constants.ZERO);
		this.totalAmount = new BigDecimal(Constants.ZERO);
	}

	public Map<String, List<Good>> getGoodMap() {
		return goodMap;
	}

	public void setGoodMap(Map<String, List<Good>> goodMap) {
		this.goodMap = goodMap;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();

		if ((this.goodMap != null) && !this.goodMap.isEmpty()) {
			Set<Map.Entry<String, List<Good>>> goodMapEntrySet = this.goodMap.entrySet();
			Iterator<Map.Entry<String, List<Good>>> goodMapEntrySetIter = goodMapEntrySet.iterator();
			while (goodMapEntrySetIter.hasNext()) {
				Map.Entry<String, List<Good>> currEntry = goodMapEntrySetIter.next();
				String entryKey = currEntry.getKey();
				List<Good> entryValue = currEntry.getValue();
				if (entryValue != null) {
					sBuilder.append(StringUtils.LF);
					sBuilder.append(entryValue.size());
					sBuilder.append(StringUtils.SPACE);
					sBuilder.append(entryKey);

					double totalTaxedPriceForGood = 0.00;
					for (Good g : entryValue) {
						totalTaxedPriceForGood += g.getTaxedPrice().doubleValue();
					}

					sBuilder.append(" total taxed price: ");
					sBuilder.append(String.valueOf(totalTaxedPriceForGood));
				}
			}
		}

		sBuilder.append(StringUtils.LF);
		sBuilder.append("Sales taxes: ");
		sBuilder.append(this.totalSalesTaxes);
		sBuilder.append(StringUtils.LF);
		sBuilder.append("Total: ");
		sBuilder.append(this.totalAmount);

		return sBuilder.toString();
	}

	public BigDecimal getTotalSalesTaxes() {
		return totalSalesTaxes;
	}

	public void setTotalSalesTaxes(BigDecimal totalSalesTaxes) {
		this.totalSalesTaxes = totalSalesTaxes;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
