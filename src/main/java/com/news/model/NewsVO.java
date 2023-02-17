package com.news.model;

import java.sql.Timestamp;

public class NewsVO implements java.io.Serializable {
	/* 利用private宣告->防止其他人使用(資料竄改) */
	private Integer news_no;
	private String news_title;
	private String news_content;
	private Timestamp news_time;
	private byte[] news_pic;

	/* 宣告get/set方法->利用這些方法去資料庫進行拿取或設定動作 */
	public Integer getNews_no() {
		return news_no;
	}

	public void setNews_no(Integer news_no) {
		this.news_no = news_no;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public Timestamp getNews_time() {
		return news_time;
	}

	public void setNews_time(Timestamp news_time) {
		this.news_time = news_time;
	}

	public byte[] getNews_pic() {
		return news_pic;
	}

	public void setNews_pic(byte[] news_pic) {
		this.news_pic = news_pic;
	}
}
