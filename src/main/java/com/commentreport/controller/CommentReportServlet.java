package com.commentreport.controller;

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
import com.commentreport.model.CommentReportService;
import com.commentreport.model.CommentReportVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class CommentReportServlet extends HttpServlet {
	/* prevent 405 method */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/* request from any jsp */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/* Get to updating article report */
		if("commentDealPage".equals(action))
		{
			String strComment_report_id = req.getParameter("comment_report_id");
			Integer comment_report_id = Integer.valueOf(strComment_report_id);
			CommentReportService commentReportSvc = new CommentReportService();
			CommentReportVO commentReportVO = new CommentReportVO();
			commentReportVO = commentReportSvc.transCommentReport(comment_report_id);
			req.setAttribute("commentReportVO", commentReportVO);
			/* start to transform jsp*/
		    String url = "/back_end/article/updateOneCommentReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		}
		/* Get to updating article report */
		if ("commentDeal".equals(action)) {
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String strComment_id = req.getParameter("comment_id" );
			String strComment_reportprocess_state = req.getParameter("comment_reportprocess_state");
			String strComment_reportprocess_time = req.getParameter("comment_reportprocess_time");
			if(strComment_id==null || strComment_id.trim().length() ==0) {
				errorMsgs.put("comment_id", "•請至總表選擇要處理的留言檢舉");
			}
			if (!errorMsgs.isEmpty()) {
				res.setCharacterEncoding("UTF-8");
				 String url = "/back_end/article/updateOneCommentReport.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			if(strComment_reportprocess_state==null || strComment_reportprocess_state.trim().length() ==0) {
				errorMsgs.put("comment_reportprocess_state", "•請選擇處理狀態");
			}
			if(strComment_reportprocess_time==null || strComment_reportprocess_time.trim().length() ==0) {
				errorMsgs.put("comment_reportprocess_time", "•請選擇處理時間");
			}
			if (!errorMsgs.isEmpty()) {
				String strComment_report_id = req.getParameter("comment_report_id");
				Integer comment_report_id = Integer.valueOf(strComment_report_id);
				CommentReportService commentReportSvc = new CommentReportService();
				CommentReportVO commentReportVO = new CommentReportVO();
				commentReportVO = commentReportSvc.transCommentReport(comment_report_id);
				req.setAttribute("commentReportVO", commentReportVO);
				res.setCharacterEncoding("UTF-8");
				 String url = "/back_end/article/updateOneCommentReport.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Byte comment_reportprocess_state = Byte.valueOf(strComment_reportprocess_state);
			String comment_reportprocess_content = req.getParameter("comment_reportprocess_content").trim();
			Timestamp comment_reportprocess_time = Timestamp.valueOf(strComment_reportprocess_time+" "+"00:00:00");
			String strEmp_id =  req.getParameter("emp_id");
			Integer emp_id = Integer.valueOf(strEmp_id);
			Integer comment_id = Integer.valueOf(strComment_id);
             CommentReportService commentReportSvc = new CommentReportService();
             commentReportSvc.updateCommentReport(comment_reportprocess_state, comment_reportprocess_content, comment_reportprocess_time, emp_id, comment_id);
             /* start to transform jsp*/
		    String url = "/back_end/article/allCommentReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		}
		/* Comment Report*/
		if("commentReport".equals(action)) {
			String strComment_idString = req.getParameter("comment_id");
		     Integer comment_id = Integer.valueOf(strComment_idString);
		     String strMember_id = req.getParameter("member_id");
		     Integer member_id = Integer.valueOf(strMember_id);
		     String strComment_report_reason = req.getParameter("reportReason");
		     Byte comment_report_reason = Byte.valueOf(strComment_report_reason);
		     Byte comment_repoertprocess_state = 0; // default 0
		     CommentReportService commentReportSvc = new CommentReportService();
		     commentReportSvc.addCommentReport(comment_id, member_id, comment_report_reason, comment_repoertprocess_state);
		 	Integer article_id=Integer.valueOf(req.getParameter("article_id"));
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
		
	}
}
