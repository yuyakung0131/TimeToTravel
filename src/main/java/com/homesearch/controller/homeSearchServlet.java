package com.homesearch.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.homesearch.model.homeSearchService;
import com.homesearch.model.homeSearchVO;


public class homeSearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getSearch_For_Display".equals(action)) {
			
//			String title = req.getParameter("title");
//			Integer firm_type = Integer.valueOf(req.getParameter("firm_type"));
			System.out.println("aaaa");
			// controller function
			homeSearchService homeSearchSvc = new homeSearchService();
			List<homeSearchVO> list = homeSearchSvc.selectByTitle();
			System.out.println("list");
			Gson gson = new Gson();
			res.setContentType("text/html;charset=utf-8");
//			if(firm_type == 1) {
//				String url = "/front_end/ticket/ticket.do?action=getTicketDetailed_For_Display&tkt_id=${tkt.tkt_id}"; // path of jsp
////				RequestDispatcher successView = req.getRequestDispatcher(url);
////				successView.forward(req, res);
//			}else if(firm_type == 2) {
//					String url = "/front_end/member/testFirm2.jsp"; // path of jsp
////					RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
////					successView.forward(req, res);
//				}else if(firm_type == 3) {
//					String url = "/front_end/member/testFirm3.jsp"; // path of jsp
////					RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
////					successView.forward(req, res);
//			}
		    String jsonStr = "";
			jsonStr = gson.toJson(list);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
			System.out.println(jsonStr);
			
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// request to another value
//			req.setAttribute("homeSearchVO", homeSearchVO); // 資料庫取出的tktorder物件,存入req
//			String url = "/back_end/ticketorder/listOneTktOrder.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTktOrder.jsp
//			successView.forward(req, res);
		}

	}

}