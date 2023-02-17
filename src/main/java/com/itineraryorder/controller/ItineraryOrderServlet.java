package com.itineraryorder.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itineraryorder.model.ItineraryOrderDAO;
import com.itineraryorder.model.ItineraryOrderService;
import com.itineraryorder.model.ItineraryOrderVO;
import com.itinerarytype.model.ItineraryTypeService;
import com.itinerarytype.model.ItineraryTypeVO;
import com.mysql.cj.Session;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

public class ItineraryOrderServlet extends HttpServlet {
	public static AllInOne domain;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String abc = req.getParameter("abc");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("itinerary_order_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryorder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer itinerary_order_id = null;
			try {
				itinerary_order_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("種類編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryorder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ItineraryOrderService itineraryorderSvc = new ItineraryOrderService();
			ItineraryOrderVO itineraryOrderVO = itineraryorderSvc.getOneItineraryOrder(itinerary_order_id);
			if (itineraryOrderVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryorder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryOrderVO", itineraryOrderVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/itineraryorder/listOneItineraryOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getMember_For_Display".equals(action)) {

			String str = req.getParameter("member_id");

			Integer member_id = Integer.valueOf(str);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 2.開始查詢資料 *****************************************/
			ItineraryOrderService itineraryOrderSvc = new ItineraryOrderService();
			List<ItineraryOrderVO> itineraryOrderVO = itineraryOrderSvc.getMember(member_id);
			if (itineraryOrderVO.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryorder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryOrderVO", itineraryOrderVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/itineraryorder/listMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer member_id = null;
			try {
				member_id = Integer.valueOf(req.getParameter("member_id").trim());
			} catch (NumberFormatException e) {
				member_id = 0;
				errorMsgs.add("請填寫會員編號");
			}

			Integer itinerary_id = null;
			try {
				itinerary_id = Integer.valueOf(req.getParameter("itinerary_id").trim());
			} catch (NumberFormatException e) {
				itinerary_id = 0;
				errorMsgs.add("行程編號請填數字.");
			}

			Integer itinerary_people_num = null;
			try {
				itinerary_people_num = Integer.valueOf(req.getParameter("itinerary_people_num").trim());
			} catch (NumberFormatException e) {
				itinerary_people_num = 0;
				errorMsgs.add("報名人數請填數字.");
			}

			Integer itinerary_ttl_price = null;
			try {
				itinerary_ttl_price = Integer.valueOf(req.getParameter("itinerary_ttl_price").trim());
			} catch (NumberFormatException e) {
				itinerary_ttl_price = 0;
				errorMsgs.add("訂單總金額請填數字.");
			}

			Byte itinerary_ordre_state = 0;

			Byte itinerary_refund_state = 0;

			String itinerary_order_memo = req.getParameter("itinerary_order_memo");

			ItineraryOrderVO itineraryOrderVO = new ItineraryOrderVO();
			itineraryOrderVO.setMember_id(member_id);
			itineraryOrderVO.setItinerary_id(itinerary_id);
			itineraryOrderVO.setItinerary_people_num(itinerary_people_num);
			itineraryOrderVO.setItinerary_ttl_price(itinerary_ttl_price);
			itineraryOrderVO.setItinerary_order_state(itinerary_ordre_state);
			itineraryOrderVO.setItinerary_refund_state(itinerary_refund_state);
			itineraryOrderVO.setItinerary_order_memo(itinerary_order_memo);

			// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("itineraryOrderVO", itineraryOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/front_end/itineraryorder/select_page.jsp");
//						failureView.forward(req, res);
//						return;
//					}

			/**** 2.新增資料 ****/
			ItineraryOrderService itineraryorderSvc = new ItineraryOrderService();

			/*************************** 2.開始新增資料 ***************************************/

			itineraryOrderVO = itineraryorderSvc.addItineraryOrder(member_id, itinerary_id, itinerary_people_num,
					itinerary_ttl_price, itinerary_ordre_state, itinerary_refund_state, itinerary_order_memo);
			ItineraryOrderDAO dao = new ItineraryOrderDAO();
			ItineraryOrderVO itineraryOrderVO1 = dao.getLatestOrder(member_id, itinerary_id);
//					req.setAttribute("itineraryOrderVO1", itineraryOrderVO1);
			req.getSession().setAttribute("itineraryOrderVO1", itineraryOrderVO1);
			System.out.println("新增商品OK");
			// 在新增訂單的同時去修改 itinerary裡的人數資料
			ItineraryService itineraryService = new ItineraryService();
			ItineraryVO itineraryVO = itineraryService.getOneItinerary(itinerary_id);
			int newNum = itineraryVO.getItinerary_now() + itinerary_people_num;
			itineraryVO.setItinerary_now(newNum);
			itineraryService.updateItinerary(itineraryVO.getItinerary_id(), itineraryVO.getItinerary_type_id(),
					itineraryVO.getItinerary_start(), itineraryVO.getItinerary_end(), itineraryVO.getItinerary_now(),
					itineraryVO.getItinerary_price(), itineraryVO.getItinerary_min(), itineraryVO.getItinerary_max(),
					itineraryVO.getEntered_start(), itineraryVO.getEntered_end(), itineraryVO.getItinerary_state());

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//					String url = "/front_end/itinerarytype/orderFinsh.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//					successView.forward(req, res);		

			Integer test = 100;
//					epay
			// 根據表單建立收款連結 (中文編碼有問題)
			// 使用者跳轉至綠界的交易流程網站
			// 按照流程輸入卡號..... (中文編碼!)
			// 測試卡號: 一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
			// 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，
			// 例如在 2016/04/20 當天作測試，請設定 05/2016(含)之後的有效月年，否則回應刷卡失敗。
			// 手機請輸入正確，因為會傳驗證碼
			// 檢查後台: 信用卡收單 - 交易明細 - 查詢
			domain = new AllInOne("");
			AioCheckOutOneTime obj = new AioCheckOutOneTime();
			// 從 view 獲得資料，依照 https://developers.ecpay.com.tw/?p=2866 獲得必要的參數
			// MerchantTradeNo : 必填 特店訂單編號 (不可重複，因此需要動態產生)
			obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
			// MerchantTradeDate : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
			obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
			// TotalAmount : 必填 交易金額
			obj.setTotalAmount(itineraryOrderVO1.getItinerary_ttl_price().toString());
			// TradeDesc : 必填 交易描述
			obj.setTradeDesc("StoreID:" + (String) req.getSession().getAttribute("foodorder_storeId"));
			// ItemName : 必填 商品名稱
			obj.setItemName("aaa");
			// ReturnURL : 必填 我用不到所以是隨便填一個英文字
			obj.setReturnURL("a");
			// OrderResultURL : 選填 消費者完成付費後。重新導向的位置
//			        obj.setOrderResultURL("http://localhost:8081/CGA105G3_hsun0207/front_end/itinerarytype/select_page.jsp");
			obj.setClientBackURL("http://localhost:8081/CGA105G3_timetotravel/front_end/member/home.jsp");
			obj.setNeedExtraPaidInfo("N");

			// 回傳form訂單 並自動將使用者導到 綠界
			String form = domain.aioCheckOut(obj, null);
			System.out.println(form);
			res.setCharacterEncoding("UTF-8");
			res.getWriter().print("<html><body>" + form + "</body></html>");

		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs2 = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs2);

			/*************************** 1.接收請求參數 ****************************************/
			Integer itinerary_order_id = Integer.valueOf(req.getParameter("itinerary_order_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			ItineraryOrderService itineraryorderSvc = new ItineraryOrderService();
			ItineraryOrderVO itineraryOrderVO = itineraryorderSvc.getOneItineraryOrder(itinerary_order_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("itineraryOrderVO", itineraryOrderVO);
			String url = "/back_end/itineraryorder/updateItineraryOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer itinerary_order_id = Integer.valueOf(req.getParameter("itinerary_order_id").trim());

			Integer member_id = null;
			try {
				member_id = Integer.valueOf(req.getParameter("member_id").trim());
			} catch (NumberFormatException e) {
				member_id = 0;
				errorMsgs.add("請填寫會員編號");
			}

			Integer itinerary_id = null;
			try {
				itinerary_id = Integer.valueOf(req.getParameter("itinerary_id").trim());
			} catch (NumberFormatException e) {
				itinerary_id = 0;
				errorMsgs.add("行程編號請填數字.");
			}

			Integer itinerary_people_num = null;
			try {
				itinerary_people_num = Integer.valueOf(req.getParameter("itinerary_people_num").trim());
			} catch (NumberFormatException e) {
				itinerary_people_num = 0;
				errorMsgs.add("報名人數請填數字.");
			}

			Integer itinerary_ttl_price = null;
			try {
				itinerary_ttl_price = Integer.valueOf(req.getParameter("itinerary_ttl_price").trim());
			} catch (NumberFormatException e) {
				itinerary_ttl_price = 0;
				errorMsgs.add("訂單總金額請填數字.");
			}

			Byte itinerary_order_state = null;
			try {
				itinerary_order_state = Byte.valueOf(req.getParameter("itinerary_order_state").trim());
			} catch (NumberFormatException e) {
				itinerary_order_state = 0;
				errorMsgs.add("行程狀態請填數字.");
			}
			;

			Byte itinerary_refund_state = null;
			try {
				itinerary_refund_state = Byte.valueOf(req.getParameter("itinerary_refund_state").trim());
			} catch (NumberFormatException e) {
				itinerary_refund_state = 0;
				errorMsgs.add("行程狀態請填數字.");
			}
			;

			String itinerary_order_memo = req.getParameter("itinerary_order_memo");

			ItineraryOrderVO itineraryOrderVO = new ItineraryOrderVO();
			itineraryOrderVO.setMember_id(member_id);
			itineraryOrderVO.setItinerary_id(itinerary_id);
			itineraryOrderVO.setItinerary_people_num(itinerary_people_num);
			itineraryOrderVO.setItinerary_ttl_price(itinerary_ttl_price);
			itineraryOrderVO.setItinerary_order_state(itinerary_order_state);
			itineraryOrderVO.setItinerary_refund_state(itinerary_refund_state);
			itineraryOrderVO.setItinerary_order_memo(itinerary_order_memo);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("itineraryOrderVO", itineraryOrderVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryorder/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			ItineraryOrderService itineraryOrderSvc = new ItineraryOrderService();
			itineraryOrderVO = itineraryOrderSvc.updateItineraryOrder(itinerary_order_id, member_id, itinerary_id,
					itinerary_people_num, itinerary_ttl_price, itinerary_order_state, itinerary_refund_state,
					itinerary_order_memo);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryOrderVO", itineraryOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/itineraryorder/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("orderDetail".equals(action)) {
			/**** 1.取得參數 ****/
			Integer itinerary_id = new Integer(req.getParameter("itinerary_id"));

			/**** 2.查詢行程種類資料 ****/
			ItineraryService itinerarySvc = new ItineraryService();
			ItineraryVO itineraryVO = itinerarySvc.getOneItinerary(itinerary_id);

			req.setAttribute("itineraryVO", itineraryVO);

			/**** 3.轉交 ****/
			String url = "/front_end/itinerarytype/orderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

//		 if ("showOrder".equals(action)) {
//				/**** 1.取得參數 ****/
//				Integer member_id = new Integer(req.getParameter("member_id"));
//				
//				/**** 3.查詢行程資料list ****/
//				ItineraryOrderService itineraryOrderSvc = new ItineraryOrderService();
//				List<ItineraryOrderVO> itineraryOrderList = itineraryOrderSvc.getMember(member_id);
//				req.setAttribute("itineraryOrderList", itineraryOrderList);
//				
//				/**** 5.轉交 ****/
//				String url = "/front_end/member/mcItrOrder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//			}
//		 
		if ("updateItrOrder".equals(action)) { // 來自update_emp_input.jsp的請求
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			int itinerary_id = Integer.valueOf(req.getParameter("itinerary_id").trim());
			int people = Integer.valueOf(req.getParameter("itinerary_people_num").trim());

			String str = req.getParameter("itinerary_order_id");
			Integer itinerary_order_id = Integer.valueOf(str);

			Byte itinerary_order_state = 1;
			Byte itinerary_refund_state = 1;

			ItineraryOrderService itineraryOrderSvc = new ItineraryOrderService();
			ItineraryOrderVO itineraryOrderVO = itineraryOrderSvc.updateItrOrder(itinerary_order_id,
					itinerary_order_state, itinerary_refund_state);

			ItineraryService itineraryService = new ItineraryService();
			ItineraryVO itineraryVO = itineraryService.getOneItinerary(itinerary_id);

			int newNum = itineraryVO.getItinerary_now() - people;
			itineraryVO.setItinerary_now(newNum);
			itineraryService.updateItinerary(itineraryVO.getItinerary_id(), itineraryVO.getItinerary_type_id(),
					itineraryVO.getItinerary_start(), itineraryVO.getItinerary_end(), itineraryVO.getItinerary_now(),
					itineraryVO.getItinerary_price(), itineraryVO.getItinerary_min(), itineraryVO.getItinerary_max(),
					itineraryVO.getEntered_start(), itineraryVO.getEntered_end(), itineraryVO.getItinerary_state());

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryOrderVO", itineraryOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req

//				int pageControl=1;
//				req.setAttribute("pageControl", pageControl); 

			String url = "/back_end/itineraryorder/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("updateItrOrderMember".equals(action)) { // 來自update_emp_input.jsp的請求
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			int itinerary_id = Integer.valueOf(req.getParameter("itinerary_id").trim());
			int people = Integer.valueOf(req.getParameter("itinerary_people_num").trim());

			String str = req.getParameter("itinerary_order_id");
			Integer itinerary_order_id = Integer.valueOf(str);

			Byte itinerary_order_state = 1;
			Byte itinerary_refund_state = 1;

			ItineraryOrderService itineraryOrderSvc = new ItineraryOrderService();
			ItineraryOrderVO itineraryOrderVO = itineraryOrderSvc.updateItrOrder(itinerary_order_id,
					itinerary_order_state, itinerary_refund_state);

			ItineraryService itineraryService = new ItineraryService();
			ItineraryVO itineraryVO = itineraryService.getOneItinerary(itinerary_id);

			int newNum = itineraryVO.getItinerary_now() - people;
			itineraryVO.setItinerary_now(newNum);
			itineraryService.updateItinerary(itineraryVO.getItinerary_id(), itineraryVO.getItinerary_type_id(),
					itineraryVO.getItinerary_start(), itineraryVO.getItinerary_end(), itineraryVO.getItinerary_now(),
					itineraryVO.getItinerary_price(), itineraryVO.getItinerary_min(), itineraryVO.getItinerary_max(),
					itineraryVO.getEntered_start(), itineraryVO.getEntered_end(), itineraryVO.getItinerary_state());

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryOrderVO", itineraryOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req

//				int pageControl=1;
//				req.setAttribute("pageControl", pageControl); 

			String url = "/front_end/member/Orderlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

	}
}