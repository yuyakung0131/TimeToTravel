package com.tktimg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.tktimg.model.TktImg;
import com.tktimg.model.TktImgService;

@MultipartConfig
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class TktImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			byte[] buf = req.getPart("upfile1").getInputStream().readAllBytes();

			if (buf.length == 0) {
				errorMsgs.add("請確認有無圖片上傳");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/tktimg/addTktImg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

//			String file_format=req.getPart("upfile1").getContentType();

			if (!(req.getPart("upfile1").getContentType().contains("image"))) {
				errorMsgs.add("請確認上傳圖片格式");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/tktimg/addTktImg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			if (req.getParameter("tkt_id") == null || (req.getParameter("tkt_id").trim()).length() == 0) {
				errorMsgs.add("請輸入票券編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/tktimg/addTktImg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer tkt_id = null;

			try {
				tkt_id = Integer.valueOf(req.getParameter("tkt_id"));
			} catch (Exception e) {
				errorMsgs.add("票券編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			TktImgService tktimgSvc = new TktImgService();
			TktImg tktimg = tktimgSvc.addTktImg(buf, tkt_id);
			req.setAttribute("tktimg", tktimg);
			String url = "/back_end/tktimg/listOneTktimg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getOnImageId_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer tkt_id = Integer.valueOf(req.getParameter("tkt_id"));
//			System.out.println("111");
//			TktImgService tktimgSvc=new TktImgService();
//			List<TktImg> list=tktimgSvc.findByTktId(tkt_id);
			req.setAttribute("tkt_id", tkt_id);
			String url = "/back_end/tktimg/listTktIdTktimg.jsp";
//			System.out.println("222");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer tkt_img_id = Integer.valueOf(req.getParameter("tkt_img_id"));
			TktImgService tktimgSvc = new TktImgService();
			TktImg tktimg = tktimgSvc.getOneTktImg(tkt_img_id);
			req.setAttribute("tktimg", tktimg);
			String url = "/back_end/tktimg/updateOneTktimg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			byte[] buf = req.getPart("upfile1").getInputStream().readAllBytes();
			Integer tkt_img_id = Integer.valueOf(req.getParameter("tkt_img_id"));
			TktImgService tktimgSvc = new TktImgService();

			if (!(buf.length == 0) && !(req.getPart("upfile1").getContentType().contains("image"))) {
				errorMsgs.add("請確認上傳圖片格式");
			}

			Integer tkt_id = Integer.valueOf(req.getParameter("tkt_id"));



			if (buf.length == 0) {
				buf = tktimgSvc.getOneTktImg(tkt_img_id).getTkt_img();
			}

			TktImg tktimg = new TktImg();
			tktimg.setTkt_img_id(tkt_img_id);
			tktimg.setTkt_img(buf);
			tktimg.setTkt_id(tkt_id);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("tktimg", tktimg);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/tktimg/updateOneTktimg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			tktimg = tktimgSvc.updateTktImg(tkt_img_id, buf, tkt_id);
			req.setAttribute("tktimg", tktimg);
			String url = "/back_end/tktimg/listOneTktimg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer tkt_img_id = Integer.valueOf(req.getParameter("tkt_img_id"));
			TktImgService tktimgSvc = new TktImgService();
			tktimgSvc.deleteTktImg(tkt_img_id);
			String url = "/back_end/tktimg/listAllTktimg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}

}