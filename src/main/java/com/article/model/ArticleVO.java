package com.article.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleVO implements java.io.Serializable{
	private Integer article_id;
	private Integer member_id;
	private String article_title;
	private String article_content;
	private Timestamp article_time;
	private Byte article_state;
	
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
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public Timestamp getArticle_time() {
		return article_time;
	}
	public void setArticle_time(Timestamp article_time) {
		this.article_time = article_time;
	}
	public Byte getArticle_state() {
		return article_state;
	}
	public void setArticle_state(Byte article_state) {
		this.article_state = article_state;
	}
	public com.member.model.MemberVO getMember_id_byMember(){
		com.member.model.MemberService memberSvc=new com.member.model.MemberService();
		com.member.model.MemberVO memberVO=memberSvc.getMemberID(member_id);
		return memberVO;
	}
	public com.articlereport.model.ArticleReportVO getArticleReportVO(){
		com.articlereport.model.ArticleReportService articleReportSvc = new com.articlereport.model.ArticleReportService();
		com.articlereport.model.ArticleReportVO articleReportVO = articleReportSvc.getArticleProcessState(article_id);
		return articleReportVO;
	}
}
