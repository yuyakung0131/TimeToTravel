package com.articlereport.model;

import java.util.List;

public interface ArticleReportDAO_interface {
	
	public void insert(ArticleReportVO articleReportVO);
	
	public void update(ArticleReportVO articleReportVO);
	
	List<ArticleReportVO> getALL();
	
	public ArticleReportVO findByID(Integer article_report_id);
	
	public ArticleReportVO findStateByID(Integer article_id);
	
}
