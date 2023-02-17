package com.article.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.articlephoto.model.ArticlePhotoService;
import com.articlephoto.model.ArticlePhotoVO;
import com.comment.model.CommentService;
import com.comment.model.CommentVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig
public class ArticleServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOneArticleId".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String idStr = req.getParameter("article_id");
			if (idStr == null || idStr.trim().length() == 0) {
				errorMsgs.put("article_id", "請輸入文章編號");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back_end/article/searchArticle");
				failView.forward(req, res);
				return;
			}
			
			Integer article_id = null;
			try {
				article_id = Integer.valueOf(idStr);
			} catch (Exception e) {
				errorMsgs.put("article_id", "格式輸入錯誤");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back_end/article/searchArticle");
				failView.forward(req, res);
				return;
			}
			
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getArticleId(article_id);
			if (articleVO == null) {
				errorMsgs.put("article_id", "查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back_end/article/searchArticle");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("articleVO", articleVO);
			String url = "/back_end/article/searchArticle";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		
		
		if ("getMemberId".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			String memberStr =req.getParameter("post_member_id");
			if(memberStr==null||memberStr.trim().length()==0) {
				errorMsgs.put("member_id", "請輸入會員編號");
			}
			
			Integer post_member_id=null;
			try {
				post_member_id=Integer.valueOf(memberStr);
			}catch (Exception e) {
				errorMsgs.put("member_id", "輸入格式錯誤");
			}
			
			ArticleService articleSvc=new ArticleService();
			List<ArticleVO> list=articleSvc.getMember(post_member_id);
			
			if(list==null) {
				errorMsgs.put("member_id", "查無資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/article/listArticleMember.jsp");
				failView.forward(req, res);
				return;
			}
			req.setAttribute("list", list);
			String url="/front_end/article/listArticleMember.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		if ("getMemberId_comment".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			String memberStr =req.getParameter("comment_member_id");
			if(memberStr==null||memberStr.trim().length()==0) {
				errorMsgs.put("member_id", "請輸入會員編號");
			}
			
			Integer post_member_id=null;
			try {
				post_member_id=Integer.valueOf(memberStr);
			}catch (Exception e) {
				errorMsgs.put("member_id", "輸入格式錯誤");
			}
			
			ArticleService articleSvc=new ArticleService();
			List<ArticleVO> list=articleSvc.getMember(post_member_id);
			
			if(list==null) {
				errorMsgs.put("member_id", "查無資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/article/listArticleMember.jsp");
				failView.forward(req, res);
				return;
			}
			req.setAttribute("list", list);
			String url="/front_end/article/listArticleMember.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		

		if ("getArticleTitle".equals(action)) {
			
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String article_title =req.getParameter("choose");
			if(article_title==null||article_title.trim().length()==0) {
				errorMsgs.put("article_title", "請輸入標題");
			}
			
			ArticleService articleSvc=new ArticleService();
			List<ArticleVO> list=articleSvc.getAticleTitle(article_title);
			
			if(list==null||list.size()==0) {
				errorMsgs.put("article_title", "查無資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/article/listAllArticle.jsp");
				failView.forward(req, res);
				return;
			}
			
			
			req.setAttribute("list", list);
			String url="/front_end/article/listArticleTitle.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if ("getArticleContent".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String article_content =req.getParameter("choose");
			if(article_content==null||article_content.trim().length()==0) {
				errorMsgs.put("article_content", "請輸入標題");
			}
			
			ArticleService articleSvc=new ArticleService();
			List<ArticleVO> list=articleSvc.getArticleContent(article_content);
			
			if(list==null||list.size()==0) {
				errorMsgs.put("article_content", "查無資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/article/listAllArticle.jsp");
				failView.forward(req, res);
				return;
			}
			req.setAttribute("list", list);
			String url="/front_end/article/listArticleContent.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("article_insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String memberStr =req.getParameter("login_member_id");
			if(memberStr==null||memberStr.trim().length()==0) {
				errorMsgs.put("member_id", "請輸入會員編號");
			}
			
			Integer login_member_id=null;
			try {
				login_member_id=Integer.valueOf(memberStr);
			} catch (Exception e) {
				errorMsgs.put("member_id", "請登入會員");
			}
			
			String article_title=req.getParameter("article_title");
			if(article_title==null||article_title.trim().length()==0) {
				errorMsgs.put("article_title", "請輸入標題");
			}else if(article_title.trim().length()>100) {
				errorMsgs.put("article_title","標題長度請介於1~100字");
			}
			
			String article_content=req.getParameter("article_content");
			if(article_content==null||article_content.trim().length()==0) {
				errorMsgs.put("article_content", "請輸入內容");
			}else if(article_content.trim().length()>8000) {
				errorMsgs.put("article_content","內容長度請介於1~8000字");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/article/addArticle.jsp");
				failView.forward(req, res);
				return;
			}
			
			
			ArticleService articleSvc=new ArticleService();
			ArticleVO articleVO=null;
			
			articleVO= articleSvc.insert(login_member_id, article_title, article_content);
			
			req.setAttribute("articleVO", articleVO);
			
			
			String url="/front_end/article/listAllArticle.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		
		if("article_data".equals(action)) {
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
		
		if ("update_data".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer article_id = null;
			article_id = Integer.valueOf(req.getParameter("article_id"));

			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));
			ArticleService articleSvc=new ArticleService();
			ArticleVO articleVO=articleSvc.getArticleId(article_id);
			
			MemberService memberSvc=new MemberService();
			MemberVO memberVO_post=memberSvc.getMemberID(post_member_id);

			String param = "?article_id="+articleVO.getArticle_id()+
						   "&article_title=" + articleVO.getArticle_title() + 
						   "&article_content=" + articleVO.getArticle_content();
						 
			req.setAttribute("articleVO", articleVO);
			req.setAttribute("memberVO_post", memberVO_post);
			String url = "/front_end/article/updateArticle.jsp"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer article_id=Integer.valueOf(req.getParameter("article_id"));
			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));
			
			String article_title=req.getParameter("article_title");
			if(article_title==null||article_title.trim().length()==0) {
				errorMsgs.put("article_title", "請輸入標題");
			}else if(article_title.trim().length()>10) {
				errorMsgs.put("article_title","標題長度請介於1~10字");
			}
			
			String article_content=req.getParameter("article_content");
			if(article_content==null||article_content.trim().length()==0) {
				errorMsgs.put("article_content", "請輸入內容");
			}else if(article_title.trim().length()>8000) {
				errorMsgs.put("article_content","內容長度請介於1~8000字");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/article/updateArticle.jsp");
				failView.forward(req, res);
				return;
			}
			
			ArticleService articleSvc=new ArticleService();
			articleSvc.update(article_title, article_content, article_id);
			String param="&post_member_id="+post_member_id+"&article_id="+article_id;
			String url="/front_end/article/article.do?action=article_data"+param;
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
		}
		
		if ("comment_insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer article_id = Integer.valueOf(req.getParameter("article_id"));

			Integer login_member_id = Integer.valueOf(req.getParameter("login_member_id"));
			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));

			String comment_content = req.getParameter("comment_content");
			if (comment_content == null || comment_content.trim().length() == 0) {
				errorMsgs.put("comment_content", "請輸入留言");
			}
			String param = "&post_member_id=" +post_member_id+ "&article_id=" + article_id;
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/front_end/article/article.do?action=article_data"+param);
				failView.forward(req, res);
				return;
			}

			CommentService commentSvc = new CommentService();
			CommentVO commentVO = commentSvc.insert(article_id, login_member_id, comment_content);

			req.setAttribute("commentVO", commentVO);
			
			String url = "/front_end/article/article.do?action=article_data"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);

		}

		if ("comment_update_data".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer comment_id=Integer.valueOf(req.getParameter("comment_id"));
			
			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));
			
			Integer login_member_id=Integer.valueOf(req.getParameter("login_member_id"));
			
			Integer article_id=Integer.valueOf(req.getParameter("article_id"));
			
			CommentService commentSvc=new CommentService();
			CommentVO commentVO=commentSvc.getByCommentId(comment_id);
			
			req.setAttribute("commentVO_data", commentVO);
			String param = "&post_member_id=" +post_member_id+ "&article_id=" + article_id+"&login_member_id"+login_member_id;
			String url = "/front_end/article/article.do?action=article_data"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);
			
			

		}

		if ("comment_update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer article_id = Integer.valueOf(req.getParameter("article_id"));
			
			Integer comment_id = Integer.valueOf(req.getParameter("comment_id"));

			
			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));

			String comment_content = req.getParameter("comment_content");
			if (comment_content == null || comment_content.trim().length() == 0) {
				errorMsgs.put("comment_content", "請輸入留言");
			}
			String param = "&post_member_id=" +post_member_id+ "&article_id=" + article_id;
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/front_end/article/article.do?action=article_data"+param);
				failView.forward(req, res);
				return;
			}

			CommentService commentSvc = new CommentService();
			CommentVO commentVO = commentSvc.update(comment_content, comment_id);

			req.setAttribute("commentVO", commentVO);
			
			String url = "/front_end/article/article.do?action=article_data"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);

		}
		
		
		if("insert_pic".equals(action)) {
			Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
			req.setAttribute("errorMSgs", errorMsgs);
			Integer post_member_id=Integer.valueOf(req.getParameter("post_member_id"));
			Integer article_id=Integer.valueOf(req.getParameter("article_id"));
			
			byte[] article_pic=req.getPart("article_pic_upload").getInputStream().readAllBytes();
			
			
			ArticlePhotoService articlePhotoSvc=new ArticlePhotoService();
			ArticlePhotoVO articlePhotoVO=articlePhotoSvc.insert(article_id, article_pic);
			
			req.setAttribute("articlePhotoVO", articlePhotoVO);
			String param="&article_id="+article_id+"&post_member_id="+post_member_id;
			String url="/front_end/article/article.do?action=article_data"+param;
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		

	}
}
