package com.articlephoto.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.articlephoto.model.ArticlePhotoService;
import com.articlephoto.model.ArticlePhotoVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ArticlePhotoServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		
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
