package com.empfunc.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.empfunc.model.EmpFuncService;
import com.empfunc.model.EmpFuncVO;

public class EmpFuncServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getEmpID_For_Display".equals(action)) {

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("emp_id"); 
			Integer emp_id = Integer.valueOf(str);
			/*************************** 2.開始查詢資料 *****************************************/
			EmpFuncService empFuncSvc = new EmpFuncService();
			List<EmpFuncVO> empFuncVO = empFuncSvc.getByEmpID(emp_id);
			
			if (empFuncVO.isEmpty()) {
				errorMsgs.put("emp_id","此員工無任何權限");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/empFunc/listAllEmpFunc.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empFuncVO", empFuncVO); 
			String url = "/back_end/empFunc/listOneEmpFunc.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		if ("getFuncID_For_Display".equals(action)) {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("func_id"); 
			Integer func_id = Integer.valueOf(str);
			/*************************** 2.開始查詢資料 *****************************************/
			EmpFuncService empFuncSvc = new EmpFuncService();
			List<EmpFuncVO> empFuncVO = empFuncSvc.getByFuncID(func_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empFuncVO", empFuncVO); 
			String url = "/back_end/empFunc/listOneEmpFunc.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)){
			String str = req.getParameter("emp_id"); 
			Integer emp_id = Integer.valueOf(str);
			String str2 = req.getParameter("func_id"); 
			Integer func_id = Integer.valueOf(str2);
			
			EmpFuncService empFuncSvc = new EmpFuncService();
			empFuncSvc.deleteEmpFunc(emp_id, func_id);
			
			String url = "/back_end/empFunc/listAllEmpFunc.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		if("insert".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String str1 = req.getParameter("emp_id"); 
			Integer emp_id = Integer.parseInt(str1);
			String str2 = req.getParameter("func_id"); 
			Integer func_id = Integer.valueOf(str2);
			
			// 要錯誤處理判斷要新增的權限，不是已經有了的
			EmpFuncService empFuncSvc = new EmpFuncService();
			List<EmpFuncVO> empFuncVO = empFuncSvc.getByEmpID(emp_id);
			for(EmpFuncVO empFunc : empFuncVO) {
				if(empFunc.getFunc_id() == func_id) {
					errorMsgs.add("此員工已有該權限"); 
				}
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/empFunc/addEmpFunc.jsp");
				failureView.forward(req, res);
				return;
			}
			
			// 若沒有錯誤，新增
			empFuncSvc.addEmpFunc(emp_id, func_id);
			// 再用員工編號查詢一次，並丟給另個頁面顯示
			List<EmpFuncVO> empFuncVO2 = empFuncSvc.getByEmpID(emp_id);
			req.setAttribute("empFuncVO", empFuncVO2);
			
			String url = "/back_end/empFunc/listOneEmpFunc.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		
		
	}
}
