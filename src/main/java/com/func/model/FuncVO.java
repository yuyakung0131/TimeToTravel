package com.func.model;

public class FuncVO implements java.io.Serializable {

	/* 利用private宣告->防止其他人使用(資料竄改) */
	private Integer func_id;
	private String func_name;

	/* 宣告get/set方法->利用這些方法去資料庫進行拿取或設定動作 */
	public Integer getFunc_id() {
		return func_id;
	}

	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
}
