package com.bravofly.salestaxes.model.concrete.decorator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.SalesTaxesModel;
import com.bravofly.salestaxes.model.concrete.Receipt;

public class ReceiptDecorator extends SalesTaxesModel {

	private static final long serialVersionUID = -3268188647922568480L;

	private Receipt decoratedReceipt;

	public ReceiptDecorator(Receipt decoratedReceipt) {
		super();
		this.decoratedReceipt = decoratedReceipt;
	}

	public Receipt getDecoratedReceipt() {
		return decoratedReceipt;
	}

	public void setDecoratedReceipt(Receipt decoratedReceipt) {
		this.decoratedReceipt = decoratedReceipt;
	}

	public List<Good> retrieveFlatGoodList() {
		List<Good> flatGoodList = new ArrayList<>();
		Map<String, List<Good>> goodMap = this.decoratedReceipt.getGoodMap();
		Set<Map.Entry<String, List<Good>>> goodMapEntrySet = goodMap.entrySet();
		Iterator<Map.Entry<String, List<Good>>> goodMapEntrySetIter = goodMapEntrySet.iterator();
		while (goodMapEntrySetIter.hasNext()) {
			Map.Entry<String, List<Good>> currEntry = goodMapEntrySetIter.next();
			List<Good> entryValue = currEntry.getValue();
			if (entryValue != null) {
				flatGoodList.addAll(entryValue);
			}
		}

		return flatGoodList;
	}

}
