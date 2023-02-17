package com.articlereport.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleReportService {
	/* initial implement */
	private ArticleReportDAO_interface dao;

	/* initial constructor */
	public ArticleReportService() {
		dao = new ArticleReportDAO();
	}

	/* initial method */
	/* insert article report into database */
	public ArticleReportVO addArticleReport(Integer article_id, Integer member_id, Byte article_report_reason,
			Byte article_reportprocess_state) {
		ArticleReportVO articleReportVO = new ArticleReportVO();
		articleReportVO.setArticle_id(article_id);
		articleReportVO.setMember_id(member_id);
		articleReportVO.setArticle_report_reason(article_report_reason);
		articleReportVO.setArticle_reportprocess_state(article_reportprocess_state);
		dao.insert(articleReportVO);
		return articleReportVO;
	}

	/* get all service */
	public List<ArticleReportVO> getAll() {
		return dao.getALL();
	}
	/* get one to update*/
	public ArticleReportVO transAritcleReport(Integer article_report_id) {
		return dao.findByID(article_report_id);
	}
	/*update report data*/
	public ArticleReportVO updateArticleReport(Byte article_reportprocess_state,String article_reportprocess_content,Timestamp article_reportprocess_time,Integer emp_id,Integer article_id) {
		ArticleReportVO articleReportVO = new ArticleReportVO();
		articleReportVO.setArticle_reportprocess_state(article_reportprocess_state);
		articleReportVO.setArticle_reportprocess_content(article_reportprocess_content);
		articleReportVO.setArticle_reportprocess_time(article_reportprocess_time);
		articleReportVO.setEmp_id(emp_id);
		articleReportVO.setArticle_id(article_id);
		dao.update(articleReportVO);
		return articleReportVO;
		}
	/*get report_process_data  data*/
   public ArticleReportVO getArticleProcessState(Integer article_id) {
	   return dao.findStateByID(article_id);
   }
}
