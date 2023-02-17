package com.emp.model;

import java.sql.Date;

public class EmpVO implements java.io.Serializable {

	/* 利用private宣告->防止其他人使用(資料竄改) */
	private Integer emp_id;
	private String emp_account;
	private String emp_pwd;
	private String emp_name;
	private String emp_nameeng;
	private byte[] emp_img;
	private Byte emp_state;
	private Date emp_date;

	/* 宣告get/set方法->利用這些方法去資料庫進行拿取或設定動作 */
	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_account() {
		return emp_account;
	}

	public void setEmp_account(String emp_account) {
		this.emp_account = emp_account;
	}

	public String getEmp_pwd() {
		return emp_pwd;
	}

	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_nameeng() {
		return emp_nameeng;
	}

	public void setEmp_nameeng(String emp_nameeng) {
		this.emp_nameeng = emp_nameeng;
	}

	public byte[] getEmp_img() {
		return emp_img;
	}

	public void setEmp_img(byte[] emp_img) {
		this.emp_img = emp_img;
	}

	public Byte getEmp_state() {
		return emp_state;
	}

	public void setEmp_state(Byte emp_state) {
		this.emp_state = emp_state;
	}

	public Date getEmp_date() {
		return emp_date;
	}

	public void setEmp_date(Date emp_date) {
		this.emp_date = emp_date;
	}

}
