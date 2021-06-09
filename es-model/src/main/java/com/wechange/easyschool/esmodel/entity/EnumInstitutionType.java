package com.wechange.easyschool.esmodel.entity;

public enum EnumInstitutionType {
	GHS(0), GBHS(1), THS(2), ENIEG(3), ENIET(3);
	private int type;
	EnumInstitutionType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return this.type;
	}
}
