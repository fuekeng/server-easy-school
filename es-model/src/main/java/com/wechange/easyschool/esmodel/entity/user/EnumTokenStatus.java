package com.wechange.easyschool.esmodel.entity.user;

public enum EnumTokenStatus {
	PENDING(0), VERIFIED(1), EXPIRED(2);

	private int status;

	EnumTokenStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}
}
