package com.roomorderitem.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dateadd.model.DateAdd;
import com.reservation.model.ReservationService;
import com.reservation.model.ReservationVO;
import com.roomorder.model.RoomOrderService;
import com.roomorder.model.RoomOrderVO;
import com.roomorderitem.model.RoomOrderItemService;
import com.roomorderitem.model.RoomOrderItemVO;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class RoomOrderItemServlet extends HttpServlet {

	// 無敵的doGet 加上doPost
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	// 我們由doPost開始撰寫
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		//訂單  的訂單明細
//		
//		if("getOneForOrderId".equals(action)) {
//			/*************************** 1.接收請求參數 ****************************************/
//           Integer room_order_id =Integer.valueOf(req.getParameter("room_order_id"));
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//
//			req.setAttribute("room_order_id", room_order_id);
//			RequestDispatcher successView = req.getRequestDispatcher("/back_end/roomOrder/one_order_all_orderitem.jsp");
//			successView.forward(req, res);
//		
//		}

		// 單一查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_pagePromotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("room_order_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrderItem/listOneRoomOrderItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer room_type_id = null;

			// room_type_id 沒除錯

			Integer room_order_id = null;

			try {
				room_order_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("促銷編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrderItem/listOneRoomOrderItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			RoomOrderItemService proSvc = new RoomOrderItemService();
			RoomOrderItemVO roomOrderItemVO = proSvc.getOneRoomOrderItem(room_order_id);
			if (roomOrderItemVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrderItem/listOneRoomOrderItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomOrderItemVO", roomOrderItemVO); // 資料庫取出的promotionVO物件,存入req
			String url = "/back_end/roomOrderItem/listOneRoomOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePronotion.jsp
			successView.forward(req, res);
		}

		// 取單一給前台
		if ("getOne_Front_For_Display".equals(action)) { // 來自select_pagePromotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer room_order_id = Integer.valueOf(req.getParameter("room_order_id"));

			/*************************** 2.開始查詢資料 *****************************************/
			RoomOrderItemService proSvc = new RoomOrderItemService();
			RoomOrderItemVO roomOrderItemVO = proSvc.getOneRoomOrderItem(room_order_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomOrderItemVO", roomOrderItemVO); // 資料庫取出的promotionVO物件,存入req

			String url = "/front_end/member/roomOrderDetail_front.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePronotion.jsp
			successView.forward(req, res);
		}

		// 給評論

		if ("getOne_Front_For_Comment".equals(action)) { // 來自select_pagePromotion.jsp的請求
			System.out.println("aaaaa");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer room_order_id = Integer.valueOf(req.getParameter("room_order_id"));

			/*************************** 2.開始查詢資料 *****************************************/
			RoomOrderItemService proSvc = new RoomOrderItemService();
			RoomOrderItemVO roomOrderItemVO = proSvc.getOneRoomOrderItem(room_order_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomOrderItemVO", roomOrderItemVO); // 資料庫取出的promotionVO物件,存入req

			String url = "/front_end/member/roomOrder_Comment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePronotion.jsp
			successView.forward(req, res);
		}

		// 新增insert=======================================================================================
		if ("insertOrderItem".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			// 訂單明細
			Integer room_order_id = Integer.valueOf(req.getParameter("room_order_id").trim());
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id").trim());
			Integer room_amount = Integer.valueOf(req.getParameter("reservation_amount").trim());
			Integer room_type_price = Integer.valueOf(req.getParameter("room_type_price").trim());
			Integer room_sale_price = Integer.valueOf(req.getParameter("room_sale_price").trim());
			String special_req = String.valueOf(req.getParameter("special_req").trim());
			Integer checkin_amount = Integer.valueOf(req.getParameter("checkin_amount").trim());
			Date room_order_checkin_date = Date.valueOf(req.getParameter("room_order_checkin_date").trim());
			Date room_order_checkout_date = Date.valueOf(req.getParameter("room_order_checkout_date").trim());
			String room_guest_name = String.valueOf(req.getParameter("room_guest_name").trim());
			RoomTypeService roomTypeService=new RoomTypeService();
			RoomTypeVO roomTypeVO=roomTypeService.getOneRoomType(room_type_id);
			
			//新增預約
			java.sql.Timestamp start_date = java.sql.Timestamp
					.valueOf(req.getParameter("room_order_checkin_date") + " " + "00:00:00");
			java.sql.Timestamp end_date = java.sql.Timestamp.valueOf(req.getParameter("room_order_checkout_date") + " " + "00:00:00");
			Integer room_type_amount = Integer.valueOf(req.getParameter("room_type_amount"));
			ReservationService reservationSvc = new ReservationService();
			ReservationVO reservationDates = reservationSvc.getDates(end_date, start_date);
			DateAdd dates = new DateAdd();

			for (int i = 0; i < reservationDates.getDates(); i++) {
				reservationSvc.insert(room_type_id, start_date, room_type_amount, room_amount);
				start_date = dates.addDays(start_date, 1);
			}
			
			
			
			// 宣告變數 創立物件
			RoomOrderItemVO roomOrderItemVO = new RoomOrderItemVO();
			roomOrderItemVO.setRoom_order_id(room_order_id);
			roomOrderItemVO.setRoom_type_id(room_type_id);
			roomOrderItemVO.setRoom_amount(room_amount);
			roomOrderItemVO.setRoom_type_price(room_type_price);
			roomOrderItemVO.setRoom_sale_price(room_sale_price);
			roomOrderItemVO.setSpecial_req(special_req);
			roomOrderItemVO.setCheckin_amount(checkin_amount);
			roomOrderItemVO.setRoom_order_checkin_date(room_order_checkin_date);
			roomOrderItemVO.setRoom_order_checkout_date(room_order_checkout_date);
			roomOrderItemVO.setRoom_guest_name(room_guest_name);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("roomOrderItemVO", roomOrderItemVO); // 含有輸入格式錯誤的promotionVO物件,也存入req 將使用者輸入錯的在顯示一次
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrderItem/addRoomOrderItem.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/

//新增訂單明細
			RoomOrderItemService proSvc = new RoomOrderItemService();
			roomOrderItemVO = proSvc.addRoomOrderItem(room_order_id, room_type_id, room_amount, room_type_price,
					room_sale_price, special_req, checkin_amount, room_order_checkin_date, room_order_checkout_date,
					room_guest_name);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/roomtype/listAllRoomFirm.jsp";
			   RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPromotion.jsp
			   try {
			       Thread.sleep(2000);
			   } catch (InterruptedException e) {
			       e.printStackTrace();
			   }
			   req.getRequestDispatcher(url).forward(req, res);
			   
			   
			  }

			 }

//	if ("getOne_For_Comment".equals(action)) { // 來自select_page.jsp的請求
//
//		
//
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String str = req.getParameter("empno");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入員工編號");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//			
//			Integer empno = null;
//			try {
//				empno = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("員工編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//			
//			/***************************2.開始查詢資料*****************************************/
//			EmpService empSvc = new EmpService();
//			EmpVO empVO = empSvc.getOneEmp(empno);
//			if (empVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//			
//			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
//			String url = "/emp/listOneEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//	}
}
