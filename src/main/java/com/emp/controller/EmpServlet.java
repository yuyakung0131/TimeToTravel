package com.emp.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@MultipartConfig
public class EmpServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 來自select_page.jsp的請求
		if ("getOne_For_Display".equals(action)) {

			// 先建立一個空的errorMsgs集合，若有例外訊息就往裡面放值
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 取得select_page輸入的值
			// 先用String型態，方便錯誤判斷
			// 注意JSP不論顯示文字是甚麼，都回傳name="emp_id"的資料，所以這邊getParameter("emp_id")
			String str = req.getParameter("emp_id"); 
			if (str == null || (str.trim()).length() == 0) {
				//錯誤條件成立，就往集合丟資料。
				errorMsgs.add("請輸入員工編號"); 
			}
			// 若錯誤集合有資料就forward回原網頁，並中斷程式。
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			// 判斷是否能轉型。
			Integer emp_id = null;
			try {
				emp_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(emp_id);
			
			// 判斷是否物件有值。
			if (empVO == null) {
				errorMsgs.add("查無資料");
			}
			// 若錯誤集合有資料就forward回原網頁，並中斷程式。
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 若成功找到物件，setAttribute並轉交 listOneEmp.jsp來顯示
			req.setAttribute("empVO", empVO); 
			String url = "/back_end/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		//////////////////////////////////////////////////////////////////////////////

		// 來自addEmp.jsp的請求
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String emp_account = req.getParameter("emp_account");
			String emp_accountReg = "^[a-zA-Z0-9]+@timetotravel.com$";
			
			EmpService empSvc2 = new EmpService();
			List<EmpVO> empVOList = empSvc2.getAll();
			for (EmpVO empVOx : empVOList)
				if (empVOx.getEmp_account().equals(emp_account)) {
					errorMsgs.add("帳號: 此信箱重複");
				}
				
			if (emp_account == null || emp_account.trim().length() == 0) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (!emp_account.trim().matches(emp_accountReg)) { 
				errorMsgs.add("帳號: 請填入正確e-mail格式");
			}
			
			String emp_pwd = req.getParameter("emp_pwd");
			String emp_pwdReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (emp_pwd == null || emp_pwd.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!emp_pwd.trim().matches(emp_pwdReg)) { 
				errorMsgs.add("密碼: 只能是英文字母、數字和_ ， 且長度必需在2到10之間");
			}
			
			String emp_name = req.getParameter("emp_name");
			String emp_nameReg = "^[(\u4e00-\u9fa5)]{1,4}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!emp_name.trim().matches(emp_nameReg)) { 
				errorMsgs.add("姓名: 只能是中文 , 且長度必需在1到4之間");
			}

			String emp_nameeng = req.getParameter("emp_nameeng");
			String emp_nameengReg = "^[a-zA-Z]+$";
			if (emp_nameeng == null || emp_nameeng.trim().length() == 0) {
				errorMsgs.add("Name: 請勿空白");
			} else if (!emp_nameeng.trim().matches(emp_nameengReg)) { 
				errorMsgs.add("Name: 請輸入英文");
			}
			
			// 將圖片用byte[]儲存
			Part part = req.getPart("emp_img"); 
			InputStream in = part.getInputStream();
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			
			Byte emp_state = 0;

			java.sql.Date emp_date = null;
			try {
				emp_date = java.sql.Date.valueOf(req.getParameter("emp_date").trim());
			} catch (IllegalArgumentException e) {
				emp_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期");
			}
			
			// 建立這個EmpVO物件是為了讓輸入錯誤的話，可以保留已輸入過的值
			EmpVO empVO = new EmpVO();
			empVO.setEmp_account(emp_account);
			empVO.setEmp_pwd(emp_pwd);
			empVO.setEmp_name(emp_name);
			empVO.setEmp_nameeng(emp_nameeng);
			empVO.setEmp_img(b);
			empVO.setEmp_state(emp_state);
			empVO.setEmp_date(emp_date);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EmpService empSvc = new EmpService();
			empVO = empSvc.addEmp(emp_account, emp_pwd, emp_name, emp_nameeng, b, emp_state, emp_date);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		/////////////////////////////////////////////////////////////////
		
		// 來自listAllEmp.jsp的請求
		if ("getOne_For_Update".equals(action)) { 
			
			// 這段應該沒有錯誤的可能，這集合應該不用
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		////////////////////////////////////////////////////////////////////
		
		// 來自update_emp_input.jsp的請求
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id").trim());
			
			String emp_account = req.getParameter("emp_account");
			String emp_accountReg = "^[a-zA-Z0-9]+@timetotravel.com$";
			
			EmpService empSvc2 = new EmpService();
			List<EmpVO> empVOList = empSvc2.getAll();
			for (EmpVO empVOx : empVOList)
				// 判斷帳號重複，不用檢查和自己有沒有相同
				if (empVOx.getEmp_id() != emp_id) {
					if (empVOx.getEmp_account().equals(emp_account)) {
						errorMsgs.add("員工帳號: 此帳號重複");
					}
				}
				
			if (emp_account == null || emp_account.trim().length() == 0) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (!emp_account.trim().matches(emp_accountReg)) { 
				errorMsgs.add("帳號: 請填入正確e-mail格式");
			}

			String emp_pwd = req.getParameter("emp_pwd");
			String emp_pwdReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (emp_pwd == null || emp_pwd.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!emp_pwd.trim().matches(emp_pwdReg)) { 
				errorMsgs.add("密碼: 只能是英文字母、數字和_ ， 且長度必需在2到10之間");
			}

			String emp_name = req.getParameter("emp_name");
			String emp_nameReg = "^[(\u4e00-\u9fa5)]{1,4}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!emp_name.trim().matches(emp_nameReg)) { 
				errorMsgs.add("姓名: 只能是中文 , 且長度必需在1到4之間");
			}

			String emp_nameeng = req.getParameter("emp_nameeng");
			String emp_nameengReg = "^[a-zA-Z]+$";
			if (emp_nameeng == null || emp_nameeng.trim().length() == 0) {
				errorMsgs.add("Name: 請勿空白");
			} else if (!emp_nameeng.trim().matches(emp_nameengReg)) { 
				errorMsgs.add("Name: 請輸入英文");
			}
			
			//圖片
			Part part = req.getPart("emp_img"); 
			InputStream in = part.getInputStream();
			byte[] b = new byte[in.available()];
			
			if (b.length == 0) {
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
				b = empVO.getEmp_img();
			}else {
				in.read(b);
				in.close();
			}
			
			//狀態
			String str = req.getParameter("emp_state");
			Byte emp_state = Byte.valueOf(str);
			
			//時間
			java.sql.Date emp_date = null;
			try {
				emp_date = java.sql.Date.valueOf(req.getParameter("emp_date").trim());
			} catch (IllegalArgumentException e) {
				emp_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			// 建立這個EmpVO物件是為了讓輸入錯誤的話，可以儲存其他正確的值
			EmpVO empVO = new EmpVO();
			empVO.setEmp_id(emp_id);
			empVO.setEmp_account(emp_account);
			empVO.setEmp_pwd(emp_pwd);
			empVO.setEmp_name(emp_name);
			empVO.setEmp_nameeng(emp_nameeng);
			empVO.setEmp_img(b);
			empVO.setEmp_state(emp_state);
			empVO.setEmp_date(emp_date);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EmpService empSvc = new EmpService();
			empVO = empSvc.updateEmp(emp_id, emp_account, emp_pwd, emp_name, emp_nameeng, b, emp_state, emp_date);
			
			// 可能改到登入者自己的資料，所以修改後重新設定一次session的empVO
			HttpSession session = req.getSession();
			EmpVO empVO2 = (EmpVO)session.getAttribute("empVO");
			empVO2 = empSvc.getOneEmp(empVO2.getEmp_id());
			session.setAttribute("empVO", empVO2);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("empVO", empVO);
			String url = "/back_end/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		//////////////////////////////////////////////////////
		
		if ("getImg".equals(action)) {
			res.setContentType("image/jpg");
			ServletOutputStream out = res.getOutputStream();
			String empIDString = req.getParameter("emp_id");
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(Integer.parseInt(empIDString));
//			res.setContentLength(empVO.getEmp_img().length);
//			out.write(empVO.getEmp_img());
//			out.close();
			byte[] b1 = empVO.getEmp_img();
			
			if (b1 == null) {
				InputStream in = getServletContext().getResourceAsStream("/back_end/emp/images/noimage.JPG");
				byte[] b2 = new byte[in.available()];
				in.read(b2);
				out.write(b2);
				out.close();
				in.close();
			}else if(b1.length == 0) {
				InputStream in = getServletContext().getResourceAsStream("/back_end/emp/images/noimage.JPG");
				byte[] b2 = new byte[in.available()];
				in.read(b2);
				out.write(b2);
				out.close();
				in.close();
			}else {
				out.write(b1);
				out.close();
			}
			
		}
		
		
		//////////////////////////////////////////////////
		
		if ("login".equals(action)) {
			String emp_account = req.getParameter("emp_account");
			String emp_pwd = req.getParameter("emp_pwd");

			EmpVO empVO = new EmpVO();
			EmpService empSvc = new EmpService();
			empVO = empSvc.getOneEmpByAccount(emp_account);
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			if (emp_account == null || (emp_account.trim()).length() == 0) {
				errorMsgs.put("empLogin_account", "•請輸入帳號");
			}
			if (emp_pwd == null || (emp_pwd.trim()).length() == 0) {
				errorMsgs.put("empLogin_pwd", "•請輸入密碼");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/emp/login.jsp");
				failureView.forward(req, res);
				return;
			}
			if (empVO == null) {
				errorMsgs.put("empVO", "•沒有此員工帳號");
			} else if (!(emp_pwd.equals(empVO.getEmp_pwd()))) { // client輸入的密碼和透過帳號找到的員工的密碼 相比對
				errorMsgs.put("empLogin_pwd", "•密碼有誤,請確認密碼!");
			} else if (empVO.getEmp_state() == 1) {
				errorMsgs.put("empVO", "•此員工已離職QQ");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/emp/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			if (emp_pwd.equals(empVO.getEmp_pwd())) {
				String url = "/back_end/index/index.jsp";
				HttpSession session = req.getSession();
				session.setAttribute("empVO", empVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			}
		}
		
		///////////////////////////////////////////////////////
		
		if("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("empVO");
			String url = "/front_end/member/home.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
	}
}
