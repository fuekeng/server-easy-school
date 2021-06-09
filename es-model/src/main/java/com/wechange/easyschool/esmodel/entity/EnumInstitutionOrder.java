package com.wechange.easyschool.esmodel.entity;

public enum EnumInstitutionOrder {
	PUBLIC(0), PRIVATE(1), NIGHT_CLASSES(2);
	private int order;
	EnumInstitutionOrder(int order) {
		this.order = order;
	}
	public int getOrder() {
		return this.order;
	}
}
