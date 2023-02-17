package com.firm.model;

import java.sql.Timestamp;

public class FirmVO implements java.io.Serializable {

	private Integer firm_id;
	private Integer emp_id;
	private Integer firmtype_id;
	private String firm_prim;
	private String firm_name;
	private String firm_regist_add;
	private String firm_operate_add;
	private String firm_poc;
	private String firm_phone;
	private String firm_email;
	private Timestamp firm_apply_date;
	private Byte firm_state;
	private Byte firm_review_state;
	private byte[] firm_review_petition;

	public Integer getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(Integer firm_id) {
		this.firm_id = firm_id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public Integer getFirmtype_id() {
		return firmtype_id;
	}

	public void setFirmtype_id(Integer firmtype_id) {
		this.firmtype_id = firmtype_id;
	}

	public String getFirm_prim() {
		return firm_prim;
	}

	public void setFirm_prim(String firm_prim) {
		this.firm_prim = firm_prim;
	}

	public String getFirm_name() {
		return firm_name;
	}

	public void setFirm_name(String firm_name) {
		this.firm_name = firm_name;
	}

	public String getFirm_regist_add() {
		return firm_regist_add;
	}

	public void setFirm_regist_add(String firm_regist_add) {
		this.firm_regist_add = firm_regist_add;
	}

	public String getFirm_operate_add() {
		return firm_operate_add;
	}

	public void setFirm_operate_add(String firm_operate_add) {
		this.firm_operate_add = firm_operate_add;
	}

	public String getFirm_poc() {
		return firm_poc;
	}

	public void setFirm_poc(String firm_poc) {
		this.firm_poc = firm_poc;
	}

	public String getFirm_phone() {
		return firm_phone;
	}

	public void setFirm_phone(String firm_phone) {
		this.firm_phone = firm_phone;
	}

	public String getFirm_email() {
		return firm_email;
	}

	public void setFirm_email(String firm_email) {
		this.firm_email = firm_email;
	}

	public Timestamp getFirm_apply_date() {
		return firm_apply_date;
	}

	public void setFirm_apply_date(Timestamp firm_apply_date) {
		this.firm_apply_date = firm_apply_date;
	}

	public Byte getFirm_state() {
		return firm_state;
	}

	public void setFirm_state(Byte firm_state) {
		this.firm_state = firm_state;
	}

	public Byte getFirm_review_state() {
		return firm_review_state;
	}

	public void setFirm_review_state(Byte firm_review_state) {
		this.firm_review_state = firm_review_state;
	}

	public byte[] getFirm_review_petition() {
		return firm_review_petition;
	}

	public void setFirm_review_petition(byte[] firm_review_petition) {
		this.firm_review_petition = firm_review_petition;
	}
	
	public com.firmtype.model.FirmTypeVO getFirmTypeVO(){
		com.firmtype.model.FirmTypeService firmTypeSvc = new com.firmtype.model.FirmTypeService();
		return firmTypeSvc.getOneFirmTypeVO(firmtype_id);
	}
	public com.emp.model.EmpVO getEmpVO(){
		com.emp.model.EmpService empSvc = new com.emp.model.EmpService();
		return empSvc.getOneEmp(emp_id);
	}
}
