package com.bravofly.salestaxes.model.concrete;

import java.util.ArrayList;
import java.util.List;

import com.bravofly.salestaxes.model.Good;
import com.bravofly.salestaxes.model.SalesTaxesModel;

public class ShoppingBasket extends SalesTaxesModel {

	private static final long serialVersionUID = -135558368985165890L;

	private List<Good> goodList;

	public ShoppingBasket() {
		super();
		this.goodList = new ArrayList<>();
	}

	public List<Good> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}

}
