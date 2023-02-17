package com.firm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.firm.model.FirmService;
import com.firm.model.FirmVO;

	
@MultipartConfig
public class FirmServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
	String action = req.getParameter("action");
	
	if("insert".equals(action)){
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String name = req.getParameter("firm_name"); 
		if (name == null || (name.trim()).length() == 0) {
			errorMsgs.put("firm_name","請輸入公司名稱"); 
		}
		
		String prim = req.getParameter("firm_prim");
		String primReg = "^[(\u4e00-\u9fa5)]{2,4}$";
		if (prim == null || (prim.trim()).length() == 0) {
			errorMsgs.put("firm_prim","請輸入負責人姓名"); 
		}else if (!prim.trim().matches(primReg)) { 
			errorMsgs.put("firm_prim","限中文且字數2至4");
		}
		
		String city = req.getParameter("selectedLabel_city");
		String area = req.getParameter("selectedLabel_area");
		String firm_regist_add = req.getParameter("firm_regist_add");
		if (firm_regist_add == null || (firm_regist_add.trim()).length() == 0) {
			errorMsgs.put("firm_regist_add","請輸入詳細登記地址"); 
		}
		String regist_add = (city + area + firm_regist_add).trim();
		
		
		String city2 = req.getParameter("selectedLabel_city2");
		String area2 = req.getParameter("selectedLabel_area2");
		String firm_operate_add = req.getParameter("firm_operate_add");
		if (firm_operate_add == null || (firm_operate_add.trim()).length() == 0) {
			errorMsgs.put("firm_operate_add","請輸入詳細營業地址"); 
		}
		String operate_add = (city2 + area2 + firm_operate_add).trim();
		
		
		String poc = req.getParameter("firm_poc");
		String pocReg = "^[(\u4e00-\u9fa5)]{2,4}$";
		if (poc == null || (poc.trim()).length() == 0) {
			errorMsgs.put("firm_poc","請輸入聯絡人姓名"); 
		}else if (!poc.trim().matches(pocReg)) { 
			errorMsgs.put("firm_poc","限中文且字數2至4");
		}
		
		
		String phone = req.getParameter("firm_phone");
		String phoneReg = "^09[0-9]{8}$";
		if (phone == null || (phone.trim()).length() == 0) {
			errorMsgs.put("firm_phone","請輸入聯絡人電話"); 
		}else if (!phone.trim().matches(phoneReg)) { 
			errorMsgs.put("firm_phone","請輸入正確電話格式");
		}
		
		
		String email = req.getParameter("firm_email");
		String emailReg = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$";
		if (email == null || (email.trim()).length() == 0) {
			errorMsgs.put("firm_email","請輸入聯絡人e-mail"); 
		}else if (!email.trim().matches(emailReg)) { 
			errorMsgs.put("firm_email","請輸入正確e-mail格式");
		}
		
		
		Part part = req.getPart("firm_review_petition"); 
		InputStream in = part.getInputStream();
		byte[] b = new byte[in.available()];
		in.read(b);
		in.close();
		
		String str = req.getParameter("firmtype_id");
		Integer type_id = Integer.valueOf(str);
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/firm/addFirm.jsp");
			failureView.forward(req, res);
			return;
		}

		FirmService firmSvc = new FirmService();
		firmSvc.addFirm(type_id, prim, name, regist_add, operate_add, poc, phone, email, b);
		
		// 若沒有錯誤訊息，傳個成功訊息給而畫面
		List<String> successViewMsgs = new LinkedList<String>();
		req.setAttribute("successViewMsgs", successViewMsgs);
		successViewMsgs.add("已收到廠商申請，請靜待審查結果");
		
		String url = "/front_end/firm/success.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	
	if ("getOne_For_Display".equals(action)) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String firm_name = req.getParameter("firm_name");
		if (firm_name == null || (firm_name.trim()).length() == 0) {
			errorMsgs.add("請輸入廠商名稱"); 
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/firm/select_page.jsp");
			failureView.forward(req, res);
			return;
		}
		
		FirmService firmSvc = new FirmService();
		FirmVO firmVO = firmSvc.getByName(firm_name);
		if (firmVO == null) {
			errorMsgs.add("查無資料，請確認廠商名稱");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/firm/select_page.jsp");
			failureView.forward(req, res);
			return;
		}
		
		req.setAttribute("firmVO", firmVO); 
		String url = "/back_end/firm/listOneFirm.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	
	if ("get_by_type".equals(action)) {
		
		String str = req.getParameter("firmtype_id");
		Integer firm_type = Integer.valueOf(str);
		
		FirmService firmSvc = new FirmService();
		List<FirmVO> firmList = firmSvc.getByType(firm_type);
	
		
		req.setAttribute("firmList", firmList); 
		String url = "/back_end/firm/listMoreFirm.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	
	if ("get_by_state".equals(action)) {
		
		String str = req.getParameter("firm_state");
		Byte firm_state = Byte.valueOf(str);
		
		FirmService firmSvc = new FirmService();
		List<FirmVO> firmList = firmSvc.getByState(firm_state);
	
		
		req.setAttribute("firmList", firmList); 
		String url = "/back_end/firm/listMoreFirm.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	
	if ("get_one_update".equals(action)) { 
		
			/***************************1.接收請求參數****************************************/
		Integer firm_id = Integer.valueOf(req.getParameter("firm_id"));
			
			/***************************2.開始查詢資料****************************************/
		FirmService firmSvc = new FirmService();
		FirmVO firmVO = firmSvc.getByID(firm_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
		req.setAttribute("firmVO", firmVO);         // 資料庫取出的empVO物件,存入req
		String url = "/back_end/firm/update_firm_input.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
		successView.forward(req, res);
	}
	
	if ("update".equals(action)) { 
		
		Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));
		Byte firm_state = Byte.valueOf(req.getParameter("firm_state"));
		Byte firm_review_state = Byte.valueOf(req.getParameter("firm_review_state"));
		Integer firm_id = Integer.valueOf(req.getParameter("firm_id"));
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if (firm_review_state == 0 && firm_state == 0) {
			errorMsgs.put("firm_review_state","未審核無法啟用"); 
		}
		if (!errorMsgs.isEmpty()) {
			FirmService firmSvc = new FirmService();
			FirmVO firmVO = firmSvc.getByID(firm_id);
			req.setAttribute("firmVO", firmVO); 
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/firm/update_firm_input.jsp");
			failureView.forward(req, res);
			return;
		}
		
		if (firm_review_state == 2 && firm_state == 0) {
			errorMsgs.put("firm_review_state","審核不通過無法啟用"); 
		}
		if (!errorMsgs.isEmpty()) {
			FirmService firmSvc = new FirmService();
			FirmVO firmVO = firmSvc.getByID(firm_id);
			req.setAttribute("firmVO", firmVO); 
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/firm/update_firm_input.jsp");
			failureView.forward(req, res);
			return;
		}
		
		//修改
		FirmService firmSvc = new FirmService();
		firmSvc.update(emp_id, firm_state, firm_review_state, firm_id);
		
		//修改後再次搜尋來顯示
		
		FirmVO firmVO = firmSvc.getByID(firm_id);
		req.setAttribute("firmVO", firmVO);        
		String url = "/back_end/firm/listOneFirm.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		
	}
	
	if ("show_img".equals(action)) { 
		
		Integer firm_id = Integer.valueOf(req.getParameter("firm_id"));
		req.setAttribute("firm_id", firm_id);        
		String url = "/back_end/firm/firm_review_petition.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}
	
	if ("getImg".equals(action)) {
		
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();
		String str = req.getParameter("firm_id");
		FirmService firmSvc = new FirmService();
		FirmVO firmVO = firmSvc.getByID(Integer.parseInt(str));
//		res.setContentLength(firmVO.getFirm_review_petition().length);
		
		byte[] b1 = firmVO.getFirm_review_petition();
		
		if (b1 == null) {
			InputStream in = getServletContext().getResourceAsStream("/back_end/firm/images/noimage2.png");
			byte[] b2 = new byte[in.available()];
			in.read(b2);
			out.write(b2);
			out.close();
			in.close();
		}else if(b1.length == 0) {
			InputStream in = getServletContext().getResourceAsStream("/back_end/firm/images/noimage2.png");
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
	
	
	
	
	
	}
}
