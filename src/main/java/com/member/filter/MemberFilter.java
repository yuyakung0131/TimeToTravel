package com.member.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

/**
 * Servlet Filter implementation class MemberFilter
 */
public class MemberFilter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public MemberFilter() {
		super();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		/* Article Report */
		if ("articleReport".equals(action)) {
			String member_id = req.getParameter("member_id");
			if (member_id == null || (member_id.trim()).length() == 0) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if ("commentReport".equals(action)) {
			String member_id = req.getParameter("member_id");
			if (member_id == null || (member_id.trim()).length() == 0) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if ("article_insert".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if ("comment_insert".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		/* Ticket Filter */
		if ("addCart".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
//			String member_id = req.getParameter("member_id");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if ("addListRedis".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
//			String member_id = req.getParameter("member_id");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if ("checklogin".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
//			String member_id = req.getParameter("member_id");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;

			}
		}
		if ("addList".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
//			String member_id = req.getParameter("member_id");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("reservation_data".equals(action)) {
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
			if (member == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
