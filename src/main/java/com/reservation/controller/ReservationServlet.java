package com.reservation.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dateadd.model.DateAdd;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.promotionitem.model.PromotionItemService;
import com.promotionitem.model.PromotionItemVO;
import com.reservation.model.ReservationService;
import com.reservation.model.ReservationVO;
import com.roomsearch.model.RoomSearchService;
import com.roomsearch.model.RoomSearchVO;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class ReservationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 轉送到新增空預約表頁面
		if ("insert_empty_reservation_data".equals(action)) {
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id"));

			RoomTypeService roomTypeSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(room_type_id);

			req.setAttribute("roomTypeVO", roomTypeVO);
			String url = "/back_end/roomtype/addEmptyReservation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 由後台新增空預約表
		if ("insert_empty_reservation".equals(action)) {
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id"));
			java.sql.Timestamp start_time = java.sql.Timestamp
					.valueOf(req.getParameter("start_time") + " " + "00:00:00");
			java.sql.Timestamp end_time = java.sql.Timestamp.valueOf(req.getParameter("end_time") + " " + "00:00:00");
			Integer room_type_amount = Integer.valueOf(req.getParameter("room_type_amount"));

			ReservationService reservationSvc = new ReservationService();
			ReservationVO reservationDates = reservationSvc.getDates(end_time, start_time);
			DateAdd dates = new DateAdd();

			for (int i = 0; i < reservationDates.getDates(); i++) {
				reservationSvc.insertEmptyReservation(room_type_id, start_time, room_type_amount);
				start_time = dates.addDays(start_time, 1);
			}

			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);

		}
		
		if("reservation_data".equals(action)) {
			Integer member_id=Integer.valueOf(req.getParameter("login_member_id"));
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id"));
			java.sql.Timestamp start_time = java.sql.Timestamp
					.valueOf(req.getParameter("start_date") + " " + "00:00:00");
			java.sql.Timestamp end_time = java.sql.Timestamp.valueOf(req.getParameter("end_date") + " " + "00:00:00");
			String start_date=req.getParameter("start_date");
			String end_date=req.getParameter("end_date");
			Integer room_type_amount = Integer.valueOf(req.getParameter("room_type_amount"));
			Integer reservation_amount = Integer.valueOf(req.getParameter("reservation_amount"));
			Integer checkin_amount = Integer.valueOf(req.getParameter("checkin_amount"));
			RoomTypeService roomTypeSvc=new RoomTypeService();
			//儲存房型名稱/單價
			RoomTypeVO roomTypeVO =roomTypeSvc.getOneRoomType(room_type_id);
			//儲存會員資料
			MemberService memberService=new MemberService();
			MemberVO memberVO=memberService.getMemberIDLin(member_id);
			//計算天數
			ReservationService reservationSvc = new ReservationService();
			ReservationVO reservationDates = reservationSvc.getDates(end_time, start_time);
			Integer dates=reservationDates.getDates();
			//計算總金額(房數*天數)
			Integer total_price=reservation_amount*dates*roomTypeVO.getRoom_type_price();
			//計算優惠價格
			PromotionItemService promotionItemSvc=new PromotionItemService();
			PromotionItemVO promotionItemVO=promotionItemSvc.getProByRoomType(room_type_id);
			Double discount=1.0;
			Integer room_sale_price=total_price;
			if(promotionItemVO.getDiscount_number()!=null) {
				
				discount=(promotionItemVO.getDiscount_number());
				 room_sale_price=Integer.valueOf((int)(total_price*promotionItemVO.getDiscount_number()));
			}
			req.setAttribute("roomTypeVO", roomTypeVO);
			req.setAttribute("memberVO", memberVO);
			req.setAttribute("dates", dates);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			req.setAttribute("reservation_amount", reservation_amount);
			req.setAttribute("checkin_amount", checkin_amount);
			req.setAttribute("total_price", total_price);
			req.setAttribute("room_sale_price", room_sale_price);
			req.setAttribute("discount", discount);
			req.setAttribute("room_type_amount", room_type_amount);
			String url="/front_end/roomtype/OrderRoomDetail_front.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
		// 由訂房時新增預約表
		if ("insert_reservation".equals(action)) {
			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id"));
			java.sql.Timestamp start_date = java.sql.Timestamp
					.valueOf(req.getParameter("start_date") + " " + "00:00:00");
			java.sql.Timestamp end_date = java.sql.Timestamp.valueOf(req.getParameter("end_date") + " " + "00:00:00");
			Integer room_type_amount = Integer.valueOf(req.getParameter("room_type_amount"));
			Integer reservation_amount = Integer.valueOf(req.getParameter("reservation_amount"));
			Integer checkin_amount = Integer.valueOf(req.getParameter("checkin_amount"));
			ReservationService reservationSvc = new ReservationService();
			ReservationVO reservationDates = reservationSvc.getDates(end_date, start_date);
			DateAdd dates = new DateAdd();

			for (int i = 0; i < reservationDates.getDates(); i++) {
				reservationSvc.insert(room_type_id, start_date, room_type_amount, reservation_amount);
				start_date = dates.addDays(start_date, 1);
			}
			String url = "/front_end/member/memberCenter.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}
		//計算時間區間的總價格
		if("dates".equals(action)) {
			JSONObject jsonObject=new JSONObject();
			Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
			Integer room_type_price=Integer.valueOf(req.getParameter("room_type_price"));
			String reservation_amount=req.getParameter("reservation_amount");
			String checkin_amount=req.getParameter("checkin_amount");
			String start_date =req.getParameter("start_date");
			String end_date =req.getParameter("end_date");
			String checkin_max =req.getParameter("checkin_max");
			
			java.sql.Timestamp start_time=java.sql.Timestamp.valueOf(req.getParameter("start_date") + " " + "00:00:00");
			java.sql.Timestamp end_time=java.sql.Timestamp.valueOf(req.getParameter("end_date") + " " + "00:00:00");
			
			RoomSearchService roomSearchSvc=new RoomSearchService();
			RoomSearchVO roomSearchVO=roomSearchSvc.getEmptyRoomByOneRoomByRoomType(room_type_id, start_date);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			ReservationService reservationSvc=new ReservationService();
			ReservationVO reservationVO=reservationSvc.getDates(end_time, start_time);
			
			DateAdd dateAdd=new DateAdd();
			
			String empty_room=null;
			String start_date_add=null;
			ArrayList<String> emptyArr =new ArrayList<String>();
			if(roomSearchVO!=null) {
				for(int i=0;i<reservationVO.getDates();i++) {
				start_date_add=dateFormat.format(start_time).toString();
				roomSearchVO=roomSearchSvc.getEmptyRoomByOneRoomByRoomType(room_type_id, start_date_add);
				empty_room=roomSearchVO.getEmpty_room().toString();
				System.out.println(empty_room);
				emptyArr.add(empty_room);
				start_time=dateAdd.addDays(start_time, 1);
				}
			}
			if(emptyArr.size()!=0) {
			Collections.sort(emptyArr);
			empty_room=emptyArr.get(0);
			}else {
				empty_room="0";
			}
			Integer dates_priceInt=(room_type_price*reservationVO.getDates());
			String dates_price=dates_priceInt.toString();
			if(reservation_amount!=null && reservation_amount.trim().length()!=0&&!"".equals(reservation_amount)) {
				Integer total_priceInt=((Integer.valueOf(reservation_amount))*dates_priceInt);
			String total_price=total_priceInt.toString();
			Integer checkin_amountInt=((Integer.valueOf(reservation_amount))*(Integer.valueOf(checkin_max)));
			String checkin_amount_max=checkin_amountInt.toString();
			
			RoomTypeService roomTypeSvc=new RoomTypeService();
			RoomTypeVO roomTypeVO=roomTypeSvc.getOneRoomType(room_type_id);
			Double discount=1.0;
			String room_sale_price=null;
			if(roomTypeVO.getPromotionItemVORoom().getDiscount_number()!=null) {
				discount=roomTypeVO.getPromotionItemVORoom().getDiscount_number();
			Integer room_sale_priceInt=Integer.valueOf((int)(discount*total_priceInt));
			room_sale_price=room_sale_priceInt.toString();
				jsonObject.put("room_sale_price", room_sale_price);
			}
		
			jsonObject.put("total_price", total_price);
			jsonObject.put("checkin_amount_max", checkin_amount_max);
			}
			if(checkin_amount!=null && checkin_amount.trim().length()!=0 &&!"".equals(checkin_amount)) {
				jsonObject.put("checkin_amount", checkin_amount);
			}
			String dates=reservationVO.getDates().toString();
			jsonObject.put("start_date", start_date);
			jsonObject.put("end_date", end_date);
			jsonObject.put("checkin_max", checkin_max);
			jsonObject.put("dates", dates);
			jsonObject.put("dates_price", dates_price);
			jsonObject.put("reservation_amount", reservation_amount);
			jsonObject.put("empty_room", empty_room);
			res.getWriter().write(jsonObject.toString());
		}
	}
}
