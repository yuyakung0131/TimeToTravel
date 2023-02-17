package com.ticket.controller;

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

import com.ticket.model.Ticket;
import com.ticket.model.TicketService;

public class TicketServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);


			if (req.getParameter("tkt_id") == null || (req.getParameter("tkt_id").trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
	
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
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

			TicketService ticketSvc = new TicketService();

			if (ticketSvc.getOneTicket(tkt_id) == null) {

				errorMsgs.add("沒有此票券編號");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Ticket ticket = ticketSvc.getOneTicket(tkt_id);
			req.setAttribute("ticket", ticket);
			String url = "/back_end/ticket/listOneTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer tkt_date = null;
			
			try {
				tkt_date = Integer.valueOf(req.getParameter("tkt_date"));
			} catch (Exception e) {
				errorMsgs.add("票券有效日期格式不正確");
			}
			
			Byte tkt_type_id=Byte.valueOf(req.getParameter("tkt_type_id"));			
			String tkt_name = req.getParameter("tkt_name").trim();
			

			Integer tkt_price=null;
			try {
				tkt_price = Integer.valueOf(req.getParameter("tkt_price"));
			} catch (Exception e) {
				errorMsgs.add("票券價錢格式不正確");
			}
			
			Integer firm_id = Integer.valueOf(req.getParameter("firm_id"));
			
			Integer tkt_amount = null;
			try {
				tkt_amount = Integer.valueOf(req.getParameter("tkt_amount"));
			} catch (Exception e) {
				errorMsgs.add("票券庫存量格式不正確");
			}
			
			
			
			String instruction = req.getParameter("instruction").trim();
			
			
			Double tkt_total_score = null;
			try {
				tkt_total_score = Double.valueOf(req.getParameter("tkt_total_score"));
			} catch (Exception e) {
				errorMsgs.add("票券評論分數格式不正確");
			}
			
			
			
			Integer tkt_total_people = null;
			try {
				tkt_total_people = Integer.valueOf(req.getParameter("tkt_total_people"));
			} catch (Exception e) {
				errorMsgs.add("票券評論人數格式不正確");
			}
			
			
			

				Ticket ticket=new Ticket();
				ticket.setTkt_date(tkt_date);
				ticket.setTkt_type_id(tkt_type_id);
				ticket.setTkt_name(tkt_name);
				ticket.setTkt_price(tkt_price);
				ticket.setFirm_id(firm_id);
				ticket.setTkt_amount(tkt_amount);
				ticket.setInstruction(instruction);
				ticket.setTkt_total_score(tkt_total_score);
				ticket.setTkt_total_people(tkt_total_people);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("ticket", ticket);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/addTicket.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				
//				

			TicketService ticketSvc = new TicketService();
			 ticket = ticketSvc.addTicket(tkt_date, tkt_type_id, tkt_name, tkt_price, firm_id, tkt_amount,
					instruction, tkt_total_score, tkt_total_people);

			String url = "/back_end/ticket/listAllTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	
		if ("getOne_For_Update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

	
			Integer tkt_id = Integer.valueOf(req.getParameter("tkt_id"));
//					Integer tkt_date=Integer.valueOf(req.getParameter("tkt_date"));
//					Byte tkt_type_id=Byte.valueOf(req.getParameter("tkt_type_id"));
//					String tkt_name=req.getParameter("tkt_name").trim();
//					Integer tkt_price=Integer.valueOf(req.getParameter("tkt_price"));
//					Integer firm_id=Integer.valueOf(req.getParameter("firm_id"));
//					Integer tkt_amount=Integer.valueOf(req.getParameter("tkt_amount"));
//					String instruction=req.getParameter("instruction").trim();
//					Integer tkt_total_score=Integer.valueOf(req.getParameter("tkt_total_score"));
//					Integer tkt_total_people=Integer.valueOf(req.getParameter("tkt_total_people"));

			TicketService ticketSvc = new TicketService();
			Ticket ticket = ticketSvc.getOneTicket(tkt_id);
			req.setAttribute("ticket", ticket);
			String url = "/back_end/ticket/updateOneTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer tkt_id = Integer.valueOf(req.getParameter("tkt_id"));
			Integer tkt_date = Integer.valueOf(req.getParameter("tkt_date"));
			Byte tkt_type_id = Byte.valueOf(req.getParameter("tkt_type_id"));
			String tkt_name = req.getParameter("tkt_name").trim();
			Integer tkt_price = Integer.valueOf(req.getParameter("tkt_price"));
			Integer firm_id = Integer.valueOf(req.getParameter("firm_id"));
			Integer tkt_amount = Integer.valueOf(req.getParameter("tkt_amount"));
			String instruction = req.getParameter("instruction").trim();
			Double tkt_total_score = Double.valueOf(req.getParameter("tkt_total_score"));
			Integer tkt_total_people = Integer.valueOf(req.getParameter("tkt_total_people"));

			TicketService ticketSvc = new TicketService();
			Ticket ticket = ticketSvc.updateTicket(tkt_id, tkt_date, tkt_type_id, tkt_name, tkt_price, firm_id,
					tkt_amount, instruction, tkt_total_score, tkt_total_people);
			req.setAttribute("ticket", ticket);
			String url = "/back_end/ticket/listOneTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer tkt_id = Integer.valueOf(req.getParameter("tkt_id"));
			TicketService ticketSvc = new TicketService();
			ticketSvc.deleteTicket(tkt_id);

			String url = "/back_end/ticket/listAllTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);

		}

		if ("getType_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer tkt_type_id = Integer.valueOf(req.getParameter("tkt_type_id"));
			TicketService ticketSvc = new TicketService();
//				List<Ticket>list=ticketSvc.findByType(tkt_type_id);
			req.setAttribute("tkt_type_id", tkt_type_id);
//				req.setAttribute("list", list);
			String url = "/back_end/ticket/listTypeTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getPrice_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			

			
			if (req.getParameter("min") == null || (req.getParameter("min").trim()).length() == 0) {
				errorMsgs.add("請輸入最小價錢");
			}
			
			if (req.getParameter("max") == null || (req.getParameter("max").trim()).length() == 0) {
				errorMsgs.add("請輸入最大價錢");
			}			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer min = null;
			Integer max = null;
			
			try {
//				min = Integer.valueOf(req.getParameter("min"));
				max = Integer.valueOf(req.getParameter("max"));
				
			} catch (Exception e) {
				errorMsgs.add("最大值價錢格式不對");
			}
			
			
			try {
				min = Integer.valueOf(req.getParameter("min"));
//				max = Integer.valueOf(req.getParameter("max"));
				
			} catch (Exception e) {
				errorMsgs.add("最小值價錢格式不對");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			
			
			if(min>=max) {
				errorMsgs.add("請確認輸入最大值最小值");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/ticket/listTypeTicket.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/ticket/listTypeTicket.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			
			

			req.setAttribute("min", min);
			req.setAttribute("max", max);
			String url = "/back_end/ticket/listPriceTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		
		if ("getAmount_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			if (req.getParameter("number") == null || (req.getParameter("number").trim()).length() == 0) {
				errorMsgs.add("請輸入最小庫存量");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer amount = null;

			try {
				amount = Integer.valueOf(req.getParameter("number"));
			} catch (Exception e) {
				errorMsgs.add("最少庫存量格式不對");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
		
			req.setAttribute("amount", amount);
			String url = "/back_end/ticket/listAmountTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} else if ("getTktDate_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			if (req.getParameter("number") == null || (req.getParameter("number").trim()).length() == 0) {
				errorMsgs.add("請輸入有效日期");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer tkt_date = null;
			
			try {
				tkt_date = Integer.valueOf(req.getParameter("number"));
				
			} catch (Exception e) {
				errorMsgs.add("有效日期格式不對");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ticket/select_ticket.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
	
			req.setAttribute("tkt_date", tkt_date);
			String url = "/back_end/ticket/listTktDateTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getComment_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer tkt_comment_score = Integer.valueOf(req.getParameter("tkt_comment_score"));
			req.setAttribute("tkt_comment_score", tkt_comment_score);
			String url = "/back_end/ticket/listCommentTicket.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if("getTicketDetailed_For_Display".equals(action)) {
			
			Integer tkt_id=Integer.valueOf(req.getParameter("tkt_id"));
			req.setAttribute("tkt_id", tkt_id);
			String url = "/front_end/ticket/browseTicketDetailed.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if("getTicketType_For_Display".equals(action)) {
			
			Integer tkt_type_id=Integer.valueOf(req.getParameter("tkt_type_id"));
			req.setAttribute("tkt_type_id", tkt_type_id);
			String url = "/front_end/ticket/browseTicketByType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
			
		}
		
		
		
		
		

	}
}
