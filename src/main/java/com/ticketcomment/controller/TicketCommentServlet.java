package com.ticketcomment.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.ticket.model.TicketService;
import com.ticketcomment.model.TicketComment;
import com.ticketcomment.model.TicketCommentService;

public class TicketCommentServlet extends HttpServlet  {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		


		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert_ticket_comment".equals(action)) {
			
			Integer tkt_id= Integer.valueOf(req.getParameter("tkt_id"));
			Integer member_id= Integer.valueOf(req.getParameter("member_id"));
			Double tkt_score= Double.valueOf(req.getParameter("tkt_score"));
			String instruction=req.getParameter("instruction");
			
			TicketCommentService tktcommentSvc=new TicketCommentService();
			TicketService tktSvc=new TicketService();

			
			
			DataSource ds = null;
			Context ctx;
			try {
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Connection con = null;

			try {

				con = ds.getConnection();
				
				
			con.setAutoCommit(false);
			TicketComment tktcomment=tktcommentSvc.addTicketComment(tkt_id, member_id, instruction, tkt_score);
			Double tkt_total_score= tktcommentSvc.getAllCommentScore(tkt_id);
			Integer tkt_total_count=tktcommentSvc.getAllCommentCount(tkt_id);
			tktSvc.updatebyComment(tkt_id, tkt_total_score, tkt_total_count);
			con.commit();
			con.setAutoCommit(true);
			con.close();
			}catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
			

			
			
			
			
			req.setAttribute("tkt_id", tkt_id);
			String url = "/front_end/ticket/browseTicketDetailed.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}

}
}
