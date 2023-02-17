package com.promotionitem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotionitem.model.PromotionItemService;
import com.promotionitem.model.PromotionItemVO;

public class PromotionItemServlet extends HttpServlet {

	// 無敵的doGet 加上doPost
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	// 我們由doPost開始撰寫
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//
//		if (" listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				
//				/***************************1.將輸入資料轉為Map**********************************/ 
//				//採用Map<String,String[]> getParameterMap()的方法 
//				//注意:an immutable java.util.Map 
//				Map<String, String[]> map = req.getParameterMap();
//				
//				/***************************2.開始複合查詢***************************************/
//				PromotionItemService empSvc = new PromotionItemService();
//				List<PromotionItemVO> list  = empSvc.getAll(map);
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);
//		}		

		// 取一值更新
		if ("getOne_For_Update_PromotionItem".equals(action)) { // 來自listAllEmp.jsp的請求

			

			/*************************** 1.接收請求參數 ****************************************/
			Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id"));
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id"));
			
		
			/*************************** 2.開始查詢資料 ****************************************/
			PromotionItemService proSvc = new PromotionItemService();
			PromotionItemVO promotionItemVO = proSvc.getByTwo(promotion_id, room_type_id);
			System.out.println(promotionItemVO.toString());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("promotionItemVO", promotionItemVO);
			// 資料庫取出的promotionVO物件,存入req
			String url = "/back_end/roomPromotion/update_promotionItem_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_promotion_input.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Display_PromotionItem".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String str = req.getParameter("promotion_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomPromotion/select_pagePromotionItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer promotion_id = null;
			try {
				promotion_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomPromotion/select_pagePromotionItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			// ==============================
			String str1 = req.getParameter("room_type_id");
			if (str1 == null || (str1.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomPromotion/select_pagePromotionItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			Integer room_type_id = null;
			try {
				room_type_id = Integer.valueOf(str1);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomPromotion/select_pagePromotionItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PromotionItemService proISvc = new PromotionItemService();
			PromotionItemVO promotionItemVO = proISvc.getOnePromotionItem(promotion_id);
			if (promotionItemVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomPromotion/select_pagePromotionItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promotionItemVO", promotionItemVO); // 資料庫取出的empVO物件,存入req
			String url = "/promotionItem/listOnePromotionItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		// 修改
		// 來自update_news_input.jsp的請求
		if ("update_promotionItem".equals(action)) {

			System.out.println("aaaa");
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id").trim());
			// 錯誤時 秀出原本的值
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id").trim());
			// 錯誤時 秀出原本的值
			Double discount_number = null;
			try {
				discount_number = Double.valueOf(req.getParameter("discount_number").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("discount_number","請填數字 0.1~0.95");
			}
			if (discount_number>0.95||discount_number<0.1) {
				
				errorMsgs.put("discount_number","請填數字 0.1~0.95");
			}

			// 建立這個NewsVO物件是為了讓輸入錯誤的話，可以儲存其他正確的值

			PromotionItemVO promotionItemVO = new PromotionItemVO();
			promotionItemVO.setPromotion_id(promotion_id);
			promotionItemVO.setRoom_type_id(room_type_id);
			promotionItemVO.setDiscount_number(discount_number);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionItemVO", promotionItemVO); // 含有輸入格式錯誤的newsVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomPromotion/update_promotionItem_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			PromotionItemService proISvc = new PromotionItemService();
			promotionItemVO = proISvc.updatePromotionItem(promotion_id, room_type_id, discount_number);
			// 再交給DAO去新增

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promotionItemVO", promotionItemVO); // 資料庫update成功後,正確的的newsVO物件,存入req
			String url = "/back_end/roomPromotion/listAllPromotionItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			// 修改成功後,轉交listOnePromotion.jsp
			successView.forward(req, res);
		}

		// 新增
		if ("insert_promotionItem".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("新增成功一筆");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			//錯誤處理顯示在旁，用在Map 喔!
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer promotion_id = Integer.valueOf(req.getParameter("promotion_id").trim());
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id").trim());
//			Double discount_number = null;
//			try {
//				discount_number = Double.valueOf(req.getParameter("discount_number").trim());
//			} catch (NumberFormatException e) {
//				discount_number = 0.0;
//				errorMsgs.add("請填數字.");
//			}
			Double discount_number = null;
			try {
				discount_number = Double.valueOf(req.getParameter("discount_number").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("discount_number","請填數字 0.1~0.95");
			}
			if (discount_number>0.95||discount_number<0.1) {
				
				errorMsgs.put("discount_number","請填數字 0.1~0.95");
			}
			

//			PromotionItemVO promotionItemVO = new PromotionItemVO();
//			promotionItemVO.setPromotion_id(promotion_id);
//			promotionItemVO.setRoom_type_id(room_type_id);
//			promotionItemVO.setDiscount_number(discount_number);
//
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomPromotion/addPromotionItem.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PromotionItemService proISvc = new PromotionItemService();
			proISvc.addPromotionItem(promotion_id, room_type_id, discount_number);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/roomPromotion/listAllPromotionItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

	}
}
