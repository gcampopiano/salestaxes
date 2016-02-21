package com.bravofly.salestaxes.model.concrete.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.concrete.Receipt;

public class ReceiptBuilder {

	private Map<String, List<Good>> goodMap;

	public ReceiptBuilder() {
		super();
		this.goodMap = new HashMap<>();
	}

	public Receipt build() {
		Receipt receipt = new Receipt();
		receipt.setGoodMap(this.goodMap);
		return receipt;
	}

	public ReceiptBuilder good(Good toAdd) {
		Class<? extends Good> clazz = toAdd.getClass();
		String clazzCanonicalName = clazz.getCanonicalName();
		if (!this.goodMap.containsKey(clazzCanonicalName)) {
			this.goodMap.put(clazzCanonicalName, new ArrayList<>());
		}
		this.goodMap.get(clazzCanonicalName).add(toAdd);
		return this;
	}

}
