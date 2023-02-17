package com.qa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qa.model.QaService;
import com.qa.model.QaVO;

public class QaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	

public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	String action = req.getParameter("action");
	
	
	if("getOne_For_Display".equals(action)) {
		Integer qa_id = Integer.valueOf(req.getParameter("qa_id"));
		QaService qaSvc=new QaService();
		QaVO qa=qaSvc.getOneQaVO(qa_id);
		req.setAttribute("qa", qa);
		String url="/back_end/qa/listOneQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	
	}
	
	if("getType_For_Display".equals(action)) {
		Integer qa_class_id=Integer.valueOf(req.getParameter("qa_class_id"));
		QaService qaSvc=new QaService();
		List<QaVO>list=qaSvc.findByType(qa_class_id);
		req.setAttribute("list", list);
		String url="/back_end/qa/listOneTypeQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	
	}
	
	if("getState_For_Display".equals(action)) {
		Byte qa_state=Byte.valueOf(req.getParameter("qa_state"));
		QaService qaSvc=new QaService();
		List<QaVO>list=qaSvc.findByState(qa_state);
		req.setAttribute("list", list);
		String url="/back_end/qa/listOneTypeQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	
	}
	
	if("insert".equals(action)) {
		Integer qa_show_no = Integer.valueOf(req.getParameter("qa_show_no"));
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		Byte qa_state=Byte.valueOf(req.getParameter("qa_state"));
		Integer qa_class_id=Integer.valueOf(req.getParameter("qaclass_id"));
		QaService qaSvc=new QaService();
		QaVO qa=qaSvc.addQaVO(qa_show_no, question, answer, qa_state,qa_class_id);
		req.setAttribute("qa", qa);
		String url="/back_end/qa/listAllQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	

	}
	
	
	if("delete".equals(action)) {
		Integer qa_no=Integer.valueOf(req.getParameter("qa_id"));
		QaService qaSvc=new QaService();
		qaSvc.deleteQaVO(qa_no);
		String url="/back_end/qa/listAllQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	
			
	}
	
	if("getOne_For_Update".equals(action)) {
		Integer qa_no=Integer.valueOf(req.getParameter("qa_id"));
		System.out.println(qa_no);
		QaService qaSvc=new QaService();
		QaVO qa=qaSvc.getOneQaVO(qa_no);
		req.setAttribute("qa", qa);
		String url="/back_end/qa/updateOneQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	
		
		
	}
	
	
	if("update".equals(action)) {
		Integer qa_show_no = Integer.valueOf(req.getParameter("qa_show_no"));
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		Byte qa_state=Byte.valueOf(req.getParameter("qa_state"));
		Integer qa_class_id=Integer.valueOf(req.getParameter("qaclass_id"));
		Integer qa_id=Integer.valueOf(req.getParameter("qa_id"));
		QaService qaSvc=new QaService();
		QaVO qa=qaSvc.updateQaVO(qa_id, qa_show_no, question, answer, qa_state, qa_class_id);
		req.setAttribute("qa", qa);
		String url="/back_end/qa/listOneQa.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);	
		
	}
	
	
	
	
	
}

}
