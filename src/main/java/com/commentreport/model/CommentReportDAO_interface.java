package com.commentreport.model;

import java.util.List;

public interface CommentReportDAO_interface {
	
	List<CommentReportVO> getALL();
	
	public CommentReportVO findBYID(Integer comment_report_id);
	
	public void update (CommentReportVO commentReportVO);
	
	public CommentReportVO findStateByID(Integer comment_id);
	
	public void insert(CommentReportVO commentReportVO);
}
