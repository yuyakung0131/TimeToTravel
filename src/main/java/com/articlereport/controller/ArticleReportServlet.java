package com.articlereport.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.articlereport.model.ArticleReportService;
import com.articlereport.model.ArticleReportVO;
import com.comment.model.CommentService;
import com.comment.model.CommentVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class ArticleReportServlet extends HttpServlet {

	/* prevent 405 method */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/* request from any jsp */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/*這邊只剩傳送資訊*/
		/* Article Report */
		if ("articleReport".equals(action)) {
			String strArticle_id = req.getParameter("article_id");
			Integer article_id = Integer.valueOf(strArticle_id);
			String strMember_id = req.getParameter("member_id");
			Integer member_id = Integer.valueOf(strMember_id);
			String strArticle_report_reason = req.getParameter("reportReason");
			Byte article_report_reason = Byte.valueOf(strArticle_report_reason);
			Byte article_reportprocess_state = 0;// default is 0
			/* start to insert data in db */
			ArticleReportService articleReportSvc = new ArticleReportService();
			articleReportSvc.addArticleReport(article_id, member_id, article_report_reason, article_reportprocess_state);
			/* start to transform jsp*/
			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));
			
			ArticleService articleSvc=new ArticleService();
			ArticleVO articleVO=articleSvc.getArticleId(article_id);
			
			
			MemberService memberSvc=new MemberService();
			MemberVO memberVO_post=memberSvc.getMemberID(post_member_id);
			
			CommentService commentSvc=new CommentService();
			List<CommentVO> commentVOList=commentSvc.getByArticleId(article_id);
			
			req.setAttribute("articleVO", articleVO);
			req.setAttribute("memberVO_post", memberVO_post);
			req.setAttribute("commentVOList", commentVOList);
			String url="/front_end/article/ArticleDetails.jsp";
			RequestDispatcher sucessView =req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}
		/* Get to updating article report */
		if ("articleDealPage".equals(action)) {
			String strArticle_report_id = req.getParameter("article_report_id");
			Integer article_report_id = Integer.valueOf(strArticle_report_id);
		    ArticleReportService articleReportSvc = new ArticleReportService();
		    ArticleReportVO articleReportVO = new ArticleReportVO();
		    articleReportVO= articleReportSvc.transAritcleReport(article_report_id);
		    req.setAttribute("articleReportVO", articleReportVO);
			/* start to transform jsp*/
		    String url = "/back_end/article/updateOneArticleReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		}
		/* updating article report */
		if ("articleDeal".equals(action)) {
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String strArticle_id = req.getParameter("article_id" );
			String strArticle_reportprocess_state = req.getParameter("article_reportprocess_state");
			String strArticle_reportprocess_time = req.getParameter("article_reportprocess_time");
			if(strArticle_id==null || strArticle_id.trim().length() ==0) {
				errorMsgs.put("article_id", "•請至總表選擇要處理的文章檢舉");
			}
			if (!errorMsgs.isEmpty()) {
				res.setCharacterEncoding("UTF-8");
				 String url = "/back_end/article/updateOneArticleReport.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			if(strArticle_reportprocess_state==null || strArticle_reportprocess_state.trim().length() ==0) {
				errorMsgs.put("article_reportprocess_state", "•請選擇處理狀態");
			}
			if(strArticle_reportprocess_time==null || strArticle_reportprocess_time.trim().length() ==0) {
				errorMsgs.put("article_reportprocess_time", "•請選擇處理時間");
			}
			if (!errorMsgs.isEmpty()) {
				String strArticle_report_id = req.getParameter("article_report_id");
				Integer article_report_id = Integer.valueOf(strArticle_report_id);
			    ArticleReportService articleReportSvc = new ArticleReportService();
			    ArticleReportVO articleReportVO = new ArticleReportVO();
			    articleReportVO= articleReportSvc.transAritcleReport(article_report_id);
			    req.setAttribute("articleReportVO", articleReportVO);
				res.setCharacterEncoding("UTF-8");
				 String url = "/back_end/article/updateOneArticleReport.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			Byte article_reportprocess_state = Byte.valueOf(strArticle_reportprocess_state);
			String article_reportprocess_content = req.getParameter("article_reportprocess_content").trim();
			Timestamp article_reportprocess_time = Timestamp.valueOf(strArticle_reportprocess_time+" "+"00:00:00");
			String strEmp_id =  req.getParameter("emp_id");
			Integer emp_id = Integer.valueOf(strEmp_id);
			Integer article_id = Integer.valueOf(strArticle_id);
			
			
			/*Do Service*/
			ArticleReportService articleReportSvc = new ArticleReportService();
			articleReportSvc.updateArticleReport(article_reportprocess_state, article_reportprocess_content, article_reportprocess_time, emp_id, article_id);
			/* start to transform jsp*/
		    String url = "/back_end/article/allArticleReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		}
	}
}
