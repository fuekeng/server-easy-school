package com.wechange.easyschool.esmodel.entity.user;

public enum EnumTokenType {

	EMAIL_VERIFICATION_TOKEN(0), PASSWORD_RESET_TOKEN(1), PHONE_VERIFICATION_TOKEN(2), REFRESH_TOKEN(3);

	private int status;

	EnumTokenType(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}
}
