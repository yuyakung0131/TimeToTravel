package com.qaclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qa.model.QaService;
import com.qa.model.QaVO;
import com.qaclass.model.QaclassService;
import com.qaclass.model.QaclassVO;

public class QaclassServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("showDetail".equals(action)) {			

			Integer qa_class_id = Integer.valueOf(req.getParameter("qa_class_id"));
			
			QaclassService qaClassSvc = new QaclassService();
			QaclassVO qaClassVO = qaClassSvc.getOneQaclassVO(qa_class_id);
			
			req.setAttribute("qaClassVO", qaClassVO);

			QaService qaSvc = new QaService();
			List<QaVO> qaList = qaSvc.findByType(qa_class_id);
			req.setAttribute("qaList", qaList);
			

			

			String url = "/front_end/qa/listQa.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

}
	}

}
