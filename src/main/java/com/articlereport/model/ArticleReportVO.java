package com.articlereport.model;

import java.sql.Timestamp;

public class ArticleReportVO implements java.io.Serializable{
	private Integer article_report_id;
	private Integer article_id;
	private Integer member_id;
	private Integer emp_id;
	private Byte article_report_reason;
	private Timestamp article_report_time;
	private Timestamp article_reportprocess_time;
	private Byte article_reportprocess_state;
	private String article_reportprocess_content;
	public Integer getArticle_report_id() {
		return article_report_id;
	}
	public void setArticle_report_id(Integer article_report_id) {
		this.article_report_id = article_report_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public byte getArticle_report_reason() {
		return article_report_reason;
	}
	public void setArticle_report_reason(Byte article_report_reason) {
		this.article_report_reason = article_report_reason;
	}
	public Timestamp getArticle_report_time() {
		return article_report_time;
	}
	public void setArticle_report_time(Timestamp article_report_time) {
		this.article_report_time = article_report_time;
	}
	public Timestamp getArticle_reportprocess_time() {
		return article_reportprocess_time;
	}
	public void setArticle_reportprocess_time(Timestamp article_reportprocess_time) {
		this.article_reportprocess_time = article_reportprocess_time;
	}
	public byte getArticle_reportprocess_state() {
		return article_reportprocess_state;
	}
	public void setArticle_reportprocess_state(Byte article_reportprocess_state) {
		this.article_reportprocess_state = article_reportprocess_state;
	}
	public String getArticle_reportprocess_content() {
		return article_reportprocess_content;
	}
	public void setArticle_reportprocess_content(String article_reportprocess_content) {
		this.article_reportprocess_content = article_reportprocess_content;
	}
	
	
}
