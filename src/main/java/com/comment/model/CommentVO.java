package com.comment.model;

import java.sql.Timestamp;

import com.commentreport.model.CommentReportService;

public class CommentVO implements java.io.Serializable{
	private Integer comment_id;
	private Integer article_id;
	private Integer member_id;
	private String comment_content;
	private Timestamp comment_time;
	private Byte comment_state;
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
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
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Timestamp getComment_time() {
		return comment_time;
	}
	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
	}
	public Byte getComment_state() {
		return comment_state;
	}
	public void setComment_state(Byte comment_state) {
		this.comment_state = comment_state;
	}
	public com.member.model.MemberVO getMember_id_byMember(){
		com.member.model.MemberService memberSvc=new com.member.model.MemberService();
		com.member.model.MemberVO memberVO=memberSvc.getMemberID(member_id);
		return memberVO;
	}
	
	public com.commentreport.model.CommentReportVO getCommentReportVO() {
		com.commentreport.model.CommentReportService commentReportSvc = new com.commentreport.model.CommentReportService();
		com.commentreport.model.CommentReportVO commentReportVO = commentReportSvc.getCommentProcessState(comment_id);
		return commentReportVO;
	}
	
}


