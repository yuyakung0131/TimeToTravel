package com.promotion.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.promotion.model.PromotionService;
import com.promotion.model.PromotionVO;

//繼承 HttpServlet
public class PromotionServlet extends HttpServlet {

	// 無敵的doGet 加上doPost
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	// 我們由doPost開始撰寫
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 單一查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_pagePromotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("promotion_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入促銷編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomPromotion/select_pagePromotion.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer promotion_id = null;
			try {
				promotion_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("促銷編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomPromotion/select_pagePromotion.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PromotionService proSvc = new PromotionService();
			PromotionVO promotionVO = proSvc.getOnePromotion(promotion_id);
			if (promotionVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomPromotion/select_pagePromotion.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promotionVO", promotionVO); // 資料庫取出的promotionVO物件,存入req
			String url = "/back_end/roomPromotion/listOnePromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePronotion.jsp
			successView.forward(req, res);
		}
		// 取一值更新
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			PromotionService proSvc = new PromotionService();
			PromotionVO promotionVO = proSvc.getOnePromotion(promotion_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("promotionVO", promotionVO);
			// 資料庫取出的promotionVO物件,存入req
			String url = "/back_end/roomPromotion/update_promotion_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_promotion_input.jsp
			successView.forward(req, res);
		}

		// 修改
		if ("update".equals(action)) { // 來自update_promotion_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id").trim());

			String promotion_name = req.getParameter("promotion_name");
			String promotion_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)([^%&',;=?$x22]+)]{1,100}$";
			if (promotion_name == null || promotion_name.trim().length() == 0) {
				errorMsgs.add("促銷專案名稱填寫");
			} else if (!promotion_name.trim().matches(promotion_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("促銷標題: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
			}

			java.sql.Date promotion_startdate = null;
			try {
				promotion_startdate = java.sql.Date.valueOf(req.getParameter("promotion_startdate").trim());
			} catch (IllegalArgumentException e) {
				promotion_startdate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請填寫開始日期!");
			}

			java.sql.Date promotion_enddate = null;
			try {
				promotion_enddate = java.sql.Date.valueOf(req.getParameter("promotion_enddate").trim());
			} catch (IllegalArgumentException e) {
				promotion_enddate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請填寫結束日期!");
			}

			Byte promotion_state = Byte.valueOf(req.getParameter("promotion_state").trim());

			PromotionVO promotionVO = new PromotionVO();
			promotionVO.setPromotion_id(promotion_id);
			promotionVO.setPromotion_name(promotion_name);
			promotionVO.setPromotion_startdate(promotion_startdate);
			promotionVO.setPromotion_enddate(promotion_enddate);
			promotionVO.setPromotion_state(promotion_state);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的promotionVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomPromotion/update_promotion_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			PromotionService proSvc = new PromotionService();
			promotionVO = proSvc.updatePromotion(promotion_id, promotion_name, promotion_startdate, promotion_enddate,
					promotion_state);
			// 再交給DAO去新增

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promotionVO", promotionVO); // 資料庫update成功後,正確的的promotionVO物件,存入req
			String url = "/back_end/roomPromotion/listAllPromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			// 修改成功後,轉交listOnePromotion.jsp
			successView.forward(req, res);
		}
		
		
		// 新增insert=======================================================================================
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String promotion_name = req.getParameter("promotion_name");
			String promotion_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,)([^%&',;=?$x22]+)]{1,100}$";
			if (promotion_name == null || promotion_name.trim().length() == 0) {
				errorMsgs.add("促銷標題: 請勿空白");
			} else if (!promotion_name.trim().matches(promotion_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("促銷標題: 只能是中、英文字母、數字和少數標點符號 且長度必需在1到100之間");
			}

			// 開始時間
			java.sql.Date promotion_startdate = null;
			try {
				promotion_startdate = java.sql.Date.valueOf(req.getParameter("promotion_startdate").trim());// 字串入sql.Date.valueOf出
			} catch (IllegalArgumentException e) {
				promotion_startdate = new java.sql.Date(System.currentTimeMillis() + 24 * 24 * 60 * 60 * 1000L); // currentTimeMillis當下毫秒數，超過24天要轉型成L
				errorMsgs.add("請輸入開始日期!");
			}
			// 結束時間
			java.sql.Date promotion_enddate = null;
			try {
				promotion_enddate = java.sql.Date.valueOf(req.getParameter("promotion_enddate").trim());// 字串入sql.Date.valueOf出
			} catch (IllegalArgumentException e) {
				promotion_enddate = new java.sql.Date(System.currentTimeMillis() + 24 * 24 * 60 * 60 * 1000L); // currentTimeMillis當下毫秒數，超過24天要轉型成L
				errorMsgs.add("請輸入結束日期!");
			}

			// 房型種類
			Byte promotion_state = Byte.valueOf(req.getParameter("promotion_state").trim());

			// 宣告變數 創立物件
			PromotionVO promotionVO = new PromotionVO();
			promotionVO.setPromotion_name(promotion_name);
			promotionVO.setPromotion_startdate(promotion_startdate);
			promotionVO.setPromotion_enddate(promotion_enddate);
			promotionVO.setPromotion_state(promotion_state);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的promotionVO物件,也存入req 將使用者輸入錯的在顯示一次
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomPromotion/listAllPromotion.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PromotionService proSvc = new PromotionService();
			promotionVO = proSvc.addPromotion(promotion_name, promotion_startdate, promotion_enddate, promotion_state);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/roomPromotion/listAllPromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPromotion.jsp
			successView.forward(req, res);
		}

		// 刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			PromotionService proSvc = new PromotionService();
			proSvc.deletePromotion(promotion_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/roomPromotion/listAllPromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		// 複合查詢
		if ("listPromotion_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			// Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

			// 以下的 if 區塊只對第一次執行時有效
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}

			/*************************** 2.開始複合查詢 ***************************************/
			PromotionService proSvc = new PromotionService();
			List<PromotionVO> list = proSvc.getAll(map);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listPromotion_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/roomPromotion/listOnePromotion.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
		}
	
	
    // 上下架的請求
	if ("listPromotion_ByState_01".equals(action)) {

			/*************************** 1.接收請求參數 ****************************************/
			Byte promotion_state = Byte.valueOf(req.getParameter("promotion_state"));

			/*************************** 2.開始查詢資料 ****************************************/
//			PromotionService promotionService = new PromotionService();
//			Set<PromotionVO> set = promotionService.getPromotionByState(promotion_state);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("promotion_state", promotion_state);    // 資料庫取出的set物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/roomPromotion/setAboutPromotion_state.jsp");
			successView.forward(req, res);
	}

}
	}
