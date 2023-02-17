package com.commentreport.model;

import java.sql.Timestamp;

public class CommentReportVO implements java.io.Serializable{
	private Integer comment_report_id;
	private Integer comment_id;
	private Integer member_id;
	private Integer emp_id;
	private Byte comment_report_reason;
	private Timestamp comment_report_time;
	private Timestamp comment_reportprocess_time;
	private Byte comment_reportprocess_state;
	private String comment_reportprocess_content;
	
	public Integer getComment_report_id() {
		return comment_report_id;
	}
	public void setComment_report_id(Integer comment_report_id) {
		this.comment_report_id = comment_report_id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
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
	public byte getComment_report_reason() {
		return comment_report_reason;
	}
	public void setComment_report_reason(Byte comment_report_reason) {
		this.comment_report_reason = comment_report_reason;
	}
	public Timestamp getComment_report_time() {
		return comment_report_time;
	}
	public void setComment_report_time(Timestamp comment_report_time) {
		this.comment_report_time = comment_report_time;
	}
	public Timestamp getComment_reportprocess_time() {
		return comment_reportprocess_time;
	}
	public void setComment_reportprocess_time(Timestamp comment_reportprocess_time) {
		this.comment_reportprocess_time = comment_reportprocess_time;
	}
	public byte getComment_reportprocess_state() {
		return comment_reportprocess_state;
	}
	public void setComment_reportprocess_state(Byte comment_reportprocess_state) {
		this.comment_reportprocess_state = comment_reportprocess_state;
	}
	public String getComment_reportprocess_content() {
		return comment_reportprocess_content;
	}
	public void setComment_reportprocess_content(String comment_reportprocess_content) {
		this.comment_reportprocess_content = comment_reportprocess_content;
	}
	
	public  com.comment.model.CommentVO getCommentVO() {
		com.comment.model.CommentService commentSvc = new com.comment.model.CommentService();
		com.comment.model.CommentVO commentVO = commentSvc.getCommentByID(comment_id);
		return commentVO;
	}
	
}

