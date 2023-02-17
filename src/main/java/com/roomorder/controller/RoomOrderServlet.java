package com.roomorder.controller;

import java.io.IOException;
import java.util.ArrayList;
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

public class RoomOrderServlet extends HttpServlet {
	// 無敵的doGet 加上doPost
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	// 我們由doPost開始撰寫
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String test = req.getParameter("test");
		String action = req.getParameter("action");
		
		System.out.println(test);
		// 訂單 的訂單明細

		if ("getOneForOrderId".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			Integer room_order_id = Integer.valueOf(req.getParameter("room_order_id"));
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("room_order_id", room_order_id);
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/roomOrder/one_order_all_orderitem.jsp");
			successView.forward(req, res);

		}

		// 抓狀態

		if ("getOrder_State".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/

			Byte room_order_state = Byte.valueOf(req.getParameter("room_order_state"));
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("room_order_state", room_order_state);
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/roomOrder/StateRoomOrder_back.jsp");
			successView.forward(req, res);

		}

		// 修改狀態
		if ("Update_state".equals(action)) { // 來自select_pagePromotion.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer room_order_id = Integer.valueOf(req.getParameter("room_order_id"));

			/*************************** 2.開始查詢資料 *****************************************/
			RoomOrderService proSvc = new RoomOrderService();
			RoomOrderVO roomOrderVO = proSvc.updateState(room_order_id);
			
			
			//刪除預約
			   RoomOrderItemService roomOrderItemSvc=new RoomOrderItemService();
			   RoomOrderItemVO roomOrderItemVO=roomOrderItemSvc.getOneRoomOrderItem(room_order_id);
			   Integer room_type_id=roomOrderItemVO.getRoom_type_id();
			   String start_date=roomOrderItemVO.getRoom_order_checkin_date().toString();
			   System.out.println("start_date"+start_date);
			   String end_date=roomOrderItemVO.getRoom_order_checkout_date().toString();
			   System.out.println("end_date"+end_date);
			   java.sql.Timestamp start_time = java.sql.Timestamp
			     .valueOf(start_date + " " + "00:00:00");
			   java.sql.Timestamp start_time_forSave = java.sql.Timestamp
			     .valueOf(start_date + " " + "00:00:00");
			   java.sql.Timestamp start_time_forDelete = java.sql.Timestamp
			     .valueOf(start_date + " " + "00:00:00");
			   java.sql.Timestamp start_time_forInsert = java.sql.Timestamp
			     .valueOf(start_date + " " + "00:00:00");
			   java.sql.Timestamp end_time = java.sql.Timestamp.valueOf(end_date+ " " + "00:00:00");
			   ReservationService reservationSvc = new ReservationService();
			   ReservationVO reservationOg=null;
			   ReservationVO reservationDates = reservationSvc.getDates(end_time, start_time);
			   DateAdd dates = new DateAdd();
			   List<Integer> roomOrderVOListOg=new ArrayList<Integer>();
			   List<Integer> roomOrderVOListDelete=new ArrayList<Integer>();
			   List<Integer> roomOrderVOListOrder=new ArrayList<Integer>();
			   
			   //儲存區間內的原供應房數 與 需刪除的房數
			   for(int i= 0;i<reservationDates.getDates();i++) {
			    reservationOg=reservationSvc.getByRoomTypeByStartDate(room_type_id, start_time_forSave);
			    roomOrderVOListOg.add(reservationOg.getRoom_type_amount());
			    roomOrderVOListDelete.add(reservationOg.getReservation_amount());
			    roomOrderVOListOrder.add(roomOrderItemVO.getRoom_amount());
			    start_time_forSave=dates.addDays(start_time_forSave, 1);
			   }
			   System.out.println(roomOrderVOListOg);
			   System.out.println(roomOrderVOListDelete);
			   System.out.println(roomOrderVOListOrder);
			   //刪除區間內的全部房數
			   for (int i = 0; i < reservationDates.getDates(); i++) {
			    reservationSvc.deleteReservationDate(roomOrderItemVO.getRoom_type_id(), start_time_forDelete);
			    System.out.println("delete"+start_time_forDelete);
			    start_time_forDelete = dates.addDays(start_time_forDelete, 1);
			   }
			   //新增區間內刪除此訂單後加回來的房數
			   for (int i = 0; i < reservationDates.getDates(); i++) {
			    reservationSvc.insert(room_type_id, start_time_forInsert, roomOrderVOListOg.get(i), roomOrderVOListDelete.get(i)-roomOrderVOListOrder.get(i));
			    System.out.println("原供應數量"+roomOrderVOListOg.get(i)+"被訂數量"+roomOrderVOListDelete.get(i)+"刪除後加回數量"+roomOrderVOListOrder.get(i));
			    start_time_forInsert = dates.addDays(start_time_forInsert, 1);
			   }
			
			
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomOrderVO", roomOrderVO); // 資料庫取出的roomOrderVO物件,存入req
			String url = "/front_end/member/Orderlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomOrder.jsp
			successView.forward(req, res);
		}

		// 單一查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_pagePromotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("room_order_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrderItem/listAllRoomOrderItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer room_order_id = null;
			try {
				room_order_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrderItem/listAllRoomOrderItem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			RoomOrderService proSvc = new RoomOrderService();
			RoomOrderVO roomOrderVO = proSvc.getOneRoomOrder(room_order_id);
			if (roomOrderVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomOrder/select_pageRoomOrder.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("roomOrderVO", roomOrderVO); // 資料庫取出的roomOrderVO物件,存入req
			String url = "/back_end/roomOrder/listOnePromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomOrder.jsp
			successView.forward(req, res);
		}

		// 新增insert=======================================================================================
		if ("insertOrder".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id").trim());
			Integer room_order_ttl_price = Integer.valueOf(req.getParameter("room_order_ttl_price").trim());
			// 宣告變數 創立物件
			RoomOrderVO roomOrderVO = new RoomOrderVO();
			roomOrderVO.setMember_id(member_id);
			roomOrderVO.setRoom_order_ttl_price(room_order_ttl_price);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("roomOrderVO", roomOrderVO); // 含有輸入格式錯誤的roomOrderVO物件,也存入req 將使用者輸入錯的在顯示一次
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomOrder/addRoomOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			RoomOrderService proSvc = new RoomOrderService();
			roomOrderVO = proSvc.addRoomOrder(member_id, room_order_ttl_price);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back_end/roomOrder/listAllPromotion.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPromotion.jsp
//				successView.forward(req, res);
			return;
		}

	}

}
