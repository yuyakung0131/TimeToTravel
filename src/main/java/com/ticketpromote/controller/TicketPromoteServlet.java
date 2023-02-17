package com.ticketpromote.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketpromote.model.TicketPromote;
import com.ticketpromote.model.TicketPromoteService;

public class TicketPromoteServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String prom_name=req.getParameter("prom_name").trim();
			Byte prom_state=Byte.valueOf(req.getParameter("prom_state"));
			Double discount_amount=Double.valueOf(req.getParameter("discount_amount"));
			Integer prom_achieve_number=Integer.valueOf(req.getParameter("prom_achieve_number"));
			
			
			TicketPromoteService ticketpromoteSvc=new TicketPromoteService();
			ticketpromoteSvc.addTicketPromote(prom_name, prom_state, discount_amount, prom_achieve_number);
			
			String url = "/back_end/ticketpromote/listAllPromote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);		
		}
		
		
		
		if("getState_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Byte prom_state=Byte.valueOf(req.getParameter("prom_state"));
			req.setAttribute("prom_state", prom_state);
			String url="/back_end/ticketpromote/listAllPromoteState.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			
		}
		
		
		
		if("getAmount_For_Display".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer prom_achieve_number=Integer.valueOf(req.getParameter("prom_achieve_number"));
			req.setAttribute("prom_achieve_number", prom_achieve_number);
			String url="/back_end/ticketpromote/listAllPromoteAmount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			
		}
		
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer prom_id=Integer.valueOf(req.getParameter("prom_id"));
			TicketPromoteService ticketpromoteSvc=new TicketPromoteService();
			TicketPromote ticketpromote=ticketpromoteSvc.getOneTicketPromote(prom_id);
			req.setAttribute("ticketpromote", ticketpromote);
			String url = "/back_end/ticketpromote/updateOnePromote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			System.out.println("insert4");
			successView.forward(req, res);

		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("insert1");
			Integer prom_id=Integer.valueOf(req.getParameter("prom_id"));
			String prom_name=req.getParameter("prom_name");
			Byte prom_state=Byte.valueOf(req.getParameter("prom_state"));
			Double discount_amount =Double.valueOf(req.getParameter("discount_amount"));
			Integer prom_achieve_number=Integer.valueOf(req.getParameter("prom_achieve_number"));
			
			TicketPromoteService ticketpromoteSvc=new TicketPromoteService();
			TicketPromote ticketpromote=ticketpromoteSvc.updateTicketPromote(prom_id, prom_name, prom_state, discount_amount, prom_achieve_number);
			
//			System.out.println("insert2");
			req.setAttribute("ticketpromote", ticketpromote);
			String url = "/back_end/ticketpromote/listOnePromote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			System.out.println("insert3");
			successView.forward(req, res);
			

		}
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer prom_id=Integer.valueOf(req.getParameter("prom_id"));
			
			TicketPromoteService ticketpromoteSvc=new TicketPromoteService();
			TicketPromote ticketpromote=ticketpromoteSvc.getOneTicketPromote(prom_id);
			req.setAttribute("ticketpromote", ticketpromote);
			String url = "/back_end/ticketpromote/listOnePromote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		}
		
		
		if("delete".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer prom_id=Integer.valueOf(req.getParameter("prom_id"));
			TicketPromoteService ticketpromoteSvc=new TicketPromoteService();
			ticketpromoteSvc.delete(prom_id);
			String url = "/back_end/ticketpromote/listAllPromote.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			
		}
		
		
		
		
	}

}
