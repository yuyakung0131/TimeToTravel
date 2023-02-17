package com.tktorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tktitem.model.TktItem;
import com.tktitem.model.TktItemService;
import com.tktorder.model.TktOrder;
import com.tktorder.model.TktOrderService;

public class TktOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getTktOrderId_For_Display".equals(action)) { // 來自select_page.jsp的請求

			// get value

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer tkt_order_id = Integer.valueOf(req.getParameter("number"));
			String str = req.getParameter("action");

			/*************************** 2.開始查詢資料 *****************************************/

			// controller function
			TktOrderService tktorderSvc = new TktOrderService();
			TktOrder tktorder = tktorderSvc.getOneTktOrder(tkt_order_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// request to another value
			req.setAttribute("tktorder", tktorder); // 資料庫取出的tktorder物件,存入req
			String url = "/back_end/ticketorder/listOneTktOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTktOrder.jsp
			successView.forward(req, res);
		}
		/******************************************************************************************/
		if ("getMemberId_For_Display".equals(action)) { // 來自select_page.jsp的請求
			// get value

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer member_id = Integer.valueOf(req.getParameter("number"));

			/*************************** 2.開始查詢資料 *****************************************/
			// controller function
//			TktOrderService tktorderSvc = new TktOrderService(); 
//			List<TktOrder> tktorder = tktorderSvc.findByMemberId(member_id); 

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// request to another value
			req.setAttribute("member_id", member_id);// 資料庫取出的tktorder物件,存入req ???

			String url = "/back_end/ticketorder/listOneByMemberId.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneByMemberId.jsp

			successView.forward(req, res);
		}

		// Select 複合下拉式標籤
		if ("getTktOrderId_For_Search".equals(action)) {

			Integer tkt_order_id = Integer.valueOf(req.getParameter("number"));
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String tktorderidstr = req.getParameter("tkt_order_id");

			/*************************** 2.開始查詢資料 *****************************************/

			TktOrderService tktorderSvc = new TktOrderService();
			TktOrder tktorder = tktorderSvc.getOneTktOrder(tkt_order_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("tktorder", tktorder); // 資料庫取出的tktorder物件,存入req

			String url = "/back_end/ticketorder/listOneTktOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneTktOrder.jsp
			successView.forward(req, res);

		} else if ("getMemberId_For_Search".equals(action)) {

			Integer member_id = Integer.valueOf(req.getParameter("number"));

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("action");
//				String memberidstr = req.getParameter("member_id");

			/*************************** 2.開始查詢資料 *****************************************/

//				TktOrderService tktorderSvc = new TktOrderService();
//				List<TktOrder> tktorder = tktorderSvc.findByMemberId(member_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("member_id", member_id);

			String url = "/back_end/ticketorder/listOneByMemberId.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} 
//			else if ("getTktOrderState_For_Search".equals(action)) {

//			System.out.println("111");

//			Byte tkt_order_state = Byte.valueOf(req.getParameter("number"));
//				System.out.println("222");
//			System.out.println(tkt_order_state);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

//				String tktorderstatestr = req.getParameter("tkt_order_state");

			/*************************** 2.開始查詢資料 *****************************************/

//			TktOrderService tktorderSvc = new TktOrderService();
//			List<TktOrder> tktorder = tktorderSvc.findByTktOrderState(tkt_order_state);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("tkt_order_state", tkt_order_state);
//
//			String url = "/back_end/ticketorder/listTktStateSelect.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//				System.out.println("333");
//			successView.forward(req, res);

//		}

		// 狀態Select 標籤
		if ("getTktOrderState_For_Search1".equals(action)) {

			Byte tkt_order_state = Byte.valueOf(req.getParameter("tktstate_action"));
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String tktorderstatestr = req.getParameter("tkt_order_state");
			req.setAttribute("tkt_order_state", tkt_order_state);

			String url = "/back_end/ticketorder/listTktStateSelect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		// listTktOrderItem

		if ("getOneOrderID_For_Update".equals(action)) {

			Integer tkt_order_id = Integer.valueOf(req.getParameter("tkt_order_id"));
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String tktorderstatestr = req.getParameter("tkt_order_state");

			TktItemService tktItemSvc = new TktItemService();
			List<TktItem> list = tktItemSvc.getAllOrder(tkt_order_id);

			req.setAttribute("list", list);

			String url = "/back_end/ticketorder/listOneTktOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("insert".equals(action)) {

			Integer member_id = Integer.valueOf(req.getParameter("MemberId"));

			Integer promo_id = Integer.valueOf(req.getParameter("PromId"));

			Integer total = Integer.valueOf(req.getParameter("Total"));

			Integer total_discount = Integer.valueOf(req.getParameter("TotalDiscount"));

			TktOrder tktorder = new TktOrder();
			tktorder.setMember_id(member_id);
			tktorder.setPromo_id(promo_id);
			tktorder.setTotal(total);
//			   tktorder.setTkt_order_state(tkt_order_state);
			tktorder.setTotal_discount(total_discount);

			/*************************** 2.開始新增資料 ***************************************/
			TktOrderService tktorderSvc = new TktOrderService();
			tktorder = tktorderSvc.addTktOrder(member_id, promo_id, total, total_discount);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/ticketorder/listAllTktOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTktOrder.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllTktOrder.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer tkt_order_id = Integer.valueOf(req.getParameter("tkt_order_id"));

			/*************************** 2.開始修改資料 ****************************************/
			TktOrderService tktorderSvc = new TktOrderService();
			TktOrder tktorder = tktorderSvc.getOneTktOrder(tkt_order_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			String param = "?tkt_order_id=" + tktorder.getTkt_order_id() + "&member_id=" + tktorder.getMember_id()
					+ "&promo_id=" + tktorder.getPromo_id() + "&total=" + tktorder.getTotal() + "&total_discount="
					+ tktorder.getTotal_discount() + "&tkt_order_state=" + tktorder.getTkt_order_state();
			req.setAttribute("tktorder", tktorder);
			String url = "/back_end/ticketorder/update_tktorder_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_tktorder_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_tktorder_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer tkt_order_id = Integer.valueOf(req.getParameter("tkt_order_id").trim());

			Integer member_id = Integer.valueOf(req.getParameter("MemberId").trim());

			Integer promo_id = Integer.valueOf(req.getParameter("PromId").trim());

			Integer total = Integer.valueOf(req.getParameter("Total"));

			Integer total_discount = Integer.valueOf(req.getParameter("TotalDiscount"));

			Byte tkt_order_state = Byte.valueOf(req.getParameter("TktOrderState"));
			/*************************** 2.開始修改資料 *****************************************/
			TktOrderService tktorderSvc = new TktOrderService();
			Timestamp order_date = tktorderSvc.getOneTktOrder(tkt_order_id).getOrder_date();

			TktOrder tktorder = tktorderSvc.updateTktOrder(tkt_order_id, member_id, promo_id, total, tkt_order_state,
					total_discount);

			tktorder.setOrder_date(order_date);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("tktorder", tktorder); // 資料庫update成功後,正確的的tktorder物件,存入req
			String url = "/back_end/ticketorder/listOneTktOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTktOrder.jsp

			successView.forward(req, res);
		}

		// TktOrderItem on member_center

		if ("getOneOrderID_For_MemberCenter".equals(action)) {

			Integer tkt_order_id = Integer.valueOf(req.getParameter("tkt_order_id"));
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			TktItemService tktItemSvc = new TktItemService();
			List<TktItem> list = tktItemSvc.getAllOrder(tkt_order_id);

			req.setAttribute("tktOrderListItem", list);

			String url = "/front_end/member/TktOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);

			successView.forward(req, res);

		}
	}
}
