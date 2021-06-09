package com.wechange.easyschool.esmodel.entity.user;

public enum EnumAuthority {

	ANONYMOUS(0), USER(1), ADMIN(2);

	private int status;

	EnumAuthority(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}

}
