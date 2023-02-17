package com.roomtype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sound.midi.Soundbank;

import com.firm.model.FirmService;
import com.firm.model.FirmVO;
import com.promotionitem.model.PromotionItemService;
import com.promotionitem.model.PromotionItemVO;
import com.reservation.model.ReservationService;
import com.reservation.model.ReservationVO;
import com.roomcollection.model.RoomCollectionService;
import com.roomcollection.model.RoomCollectionVO;
import com.roomsearch.model.RoomSearchService;
import com.roomsearch.model.RoomSearchVO;
import com.roomtype.model.*;

//@WebServlet("/back-end/roomtype/roomtype.do")
public class RoomTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 查詢
		if ("getOne_room_type".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("room_type_id");
			if (str == null || str.trim().length() == 0) {
				errorMsgs.put("room_type_id", "請輸入房型編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomtype/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer room_type_id = null;
			try {
				room_type_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("room_type_id", "房型編號格式輸入錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomtype/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			RoomTypeService roomTypeSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(room_type_id);
			if (roomTypeVO == null) {
				errorMsgs.put("room_type_id", "查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomtype/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			req.setAttribute("roomTypeVO", roomTypeVO);
			String url = "/back_end/roomtype/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		// 新增
		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String firmStr = req.getParameter("firm_id");
			if ("請選擇".equals(firmStr)) {
				errorMsgs.put("firm_id", "請選擇廠商名稱");
			}

			Integer firm_id = null;
			try {
				firm_id = Integer.valueOf(firmStr);
			} catch (NumberFormatException e) {
				errorMsgs.put("firm_id", "請輸入正確格式");
			}

			String room_type_name = req.getParameter("room_type_name").trim();
			String room_type_name_reg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			if (room_type_name == null || room_type_name.trim().length() == 0) {
				errorMsgs.put("room_type_name", "請輸入房型名稱");
			} else if (!room_type_name.trim().matches(room_type_name_reg)) {
				errorMsgs.put("room_type_name", "房型名稱:只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
			}

			String amountStr = req.getParameter("room_type_amount");
			if (amountStr == null || amountStr.trim().length() == 0) {
				errorMsgs.put("room_tpye_amount", "請填入房型數量");
			}

			Integer room_type_amount = null;
			try {
				room_type_amount = Integer.valueOf(amountStr);
			} catch (Exception e) {
				room_type_amount = 0;
				errorMsgs.put("room_tpye_amount", "請填入正確格式");
			}

			String room_type_content = req.getParameter("room_type_content").trim();
			if (room_type_content == null || room_type_content.trim().length() == 0) {
				errorMsgs.put("room_type_content", "房型說明:不可為空白");
			}

			String priceStr = req.getParameter("room_type_price");
			if (priceStr == null || priceStr.trim().length() == 0) {
				errorMsgs.put("room_type_price", "請填入價格");
			}

			Integer room_type_price = null;
			try {
				room_type_price = Integer.valueOf(priceStr);
			} catch (Exception e) {
				errorMsgs.put("room_type_state", "請填入正確格式");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/roomtype/addRoomType.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeSvc.insert(firm_id, room_type_name, room_type_amount, room_type_content, room_type_price);

			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}

		
		if ("delete".equals(action)) {
			Integer room_type_id = null;

			room_type_id = Integer.valueOf(req.getParameter("room_type_id"));

			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeSvc.delete(room_type_id);

			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update_view".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer room_type_id = null;
			room_type_id = Integer.valueOf(req.getParameter("room_type_id"));

			RoomTypeService roomTypeSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(room_type_id);

			String param = "?room_type_id=" + roomTypeVO.getRoom_type_id() + "&room_type_state="
					+ roomTypeVO.getRoom_type_state();
			req.setAttribute("roomTypeVO", roomTypeVO);
			String url = "/back_end/roomtype/updateRoomTypePage.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id"));

			Byte room_type_state = Byte.valueOf(req.getParameter("room_type_state"));

			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeSvc.update(room_type_id, room_type_state);

			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("roomType_view".equals(action)) {
			String search_words=req.getParameter("search_words");
			Integer roomFirm_id=Integer.valueOf(req.getParameter("room_firm_id"));
			FirmService firmSvc=new FirmService();
			FirmVO firmVO=firmSvc.getOneDeptByRoomLin(roomFirm_id);
			RoomTypeService roomTypeSvc=new RoomTypeService();
			List<RoomTypeVO> roomTypeList=roomTypeSvc.getRoomTypeByFirmID(roomFirm_id);
			req.setAttribute("roomTypeList", roomTypeList);
			req.setAttribute("firmVO", firmVO);
			req.setAttribute("search_words", search_words);
			String url="/front_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("roomType_view_by_search_date".equals(action)) {
			Integer roomFirm_id=Integer.valueOf(req.getParameter("room_firm_id"));
			FirmService firmSvc=new FirmService();
			FirmVO firmVO=firmSvc.getOneDeptByRoomLin(roomFirm_id);
			String firm_name=firmVO.getFirm_name();
			String start_date=req.getParameter("start_date");
			String end_date= req.getParameter("end_date");
			RoomSearchService roomSearchSvc=new RoomSearchService();
			List<RoomSearchVO> roomSearchList=roomSearchSvc.getEmptyRoomByOneFirmByStartDate(roomFirm_id, start_date);
			RoomTypeService roomTypeSvc=new RoomTypeService();
			List<RoomTypeVO> roomTypeList=roomTypeSvc.getRoomTypeByFirmID(roomFirm_id);
			req.setAttribute("room_firm_id", roomFirm_id);
			req.setAttribute("search_words_name", firm_name);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			req.setAttribute("roomTypeList", roomTypeList);
			req.setAttribute("roomSearchList", roomSearchList);
			String url="/front_end/roomtype/listAllSearchRoomType.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
			
		}
		
		if("roomTypeDetails".equals(action)) {
			Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
			String memberStr=req.getParameter("login_member_id");
			if(!"".equals(memberStr)) {
				Integer member_id=Integer.valueOf(memberStr);
				RoomCollectionService roomCollectionSvc=new RoomCollectionService();
				RoomCollectionVO roomCollectionVO=roomCollectionSvc.getOneRoomCollection(member_id, room_type_id);
				req.setAttribute("roomCollectionVO", roomCollectionVO);
			}
			RoomTypeService roomTypeSvc=new RoomTypeService();
			RoomTypeVO roomTypeVO =roomTypeSvc.getOneRoomType(room_type_id);
			String firm_name=roomTypeVO.getFirm_id_byFirm().getFirm_name();
			Integer checkin_max=0;
			 if(roomTypeVO.getRoom_type_name().contains("雙")||roomTypeVO.getRoom_type_name().contains("2")||roomTypeVO.getRoom_type_name().contains("兩")||roomTypeVO.getRoom_type_name().contains("二")||roomTypeVO.getRoom_type_name().contains("鴛鴦")) {
				 checkin_max=2;
			 }else if(roomTypeVO.getRoom_type_name().contains("單")||roomTypeVO.getRoom_type_name().contains("一")||roomTypeVO.getRoom_type_name().contains("1")||roomTypeVO.getRoom_type_name().contains("獨")) {
				 checkin_max=1;
			 }else if(roomTypeVO.getRoom_type_name().contains("三")||roomTypeVO.getRoom_type_name().contains("3")) {
				 checkin_max=3;
			 }else if(roomTypeVO.getRoom_type_name().contains("四")||roomTypeVO.getRoom_type_name().contains("4")) {
				 checkin_max=4;
			 }else if(roomTypeVO.getRoom_type_name().contains("六")||roomTypeVO.getRoom_type_name().contains("6")) {
				 checkin_max=6;
			 }else if(roomTypeVO.getRoom_type_name().contains("八")||roomTypeVO.getRoom_type_name().contains("8")) {
				 checkin_max=8;
			 }else if(roomTypeVO.getRoom_type_name().contains("十")||roomTypeVO.getRoom_type_name().contains("10")||roomTypeVO.getRoom_type_name().contains("總統")) {
				 checkin_max=10;
			 }
			req.setAttribute("roomTypeVO", roomTypeVO);
			req.setAttribute("firm_name", firm_name);
			req.setAttribute("checkin_max", checkin_max);
			String url="/front_end/roomtype/RoomTypeDetails.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("roomTypeDetails_by_searh_date".equals(action)) {
			Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
			String memberStr=req.getParameter("login_member_id");
			if(!"".equals(memberStr)) {
				Integer member_id=Integer.valueOf(memberStr);
				RoomCollectionService roomCollectionSvc=new RoomCollectionService();
				RoomCollectionVO roomCollectionVO=roomCollectionSvc.getOneRoomCollection(member_id, room_type_id);
				req.setAttribute("roomCollectionVO", roomCollectionVO);
			}
			
			String start_date=req.getParameter("start_date");
			String end_date= req.getParameter("end_date");
			java.sql.Timestamp start_time=java.sql.Timestamp.valueOf(req.getParameter("start_date") + " " + "00:00:00");
			java.sql.Timestamp end_time=java.sql.Timestamp.valueOf(req.getParameter("end_date") + " " + "00:00:00");
			ReservationService reservationSvc=new ReservationService();
			ReservationVO reservationVO=reservationSvc.getDates(end_time, start_time);
			Integer dates=reservationVO.getDates();
			RoomSearchService roomSearchSvc=new RoomSearchService();
			RoomSearchVO roomSearchVO=roomSearchSvc.getEmptyRoomByOneRoomByRoomType(room_type_id, start_date);
			Integer empty_room=roomSearchVO.getEmpty_room();
			RoomTypeService roomTypeSvc=new RoomTypeService();
			RoomTypeVO roomTypeVO=roomTypeSvc.getOneRoomType(room_type_id);
			String firm_name=roomSearchVO.getFirm_name();
			Integer checkin_max=0;
			 if(roomTypeVO.getRoom_type_name().contains("雙")||roomTypeVO.getRoom_type_name().contains("2")||roomTypeVO.getRoom_type_name().contains("兩")||roomTypeVO.getRoom_type_name().contains("二")||roomTypeVO.getRoom_type_name().contains("鴛鴦")) {
				 checkin_max=2;
			 }else if(roomTypeVO.getRoom_type_name().contains("單")||roomTypeVO.getRoom_type_name().contains("一")||roomTypeVO.getRoom_type_name().contains("1")||roomTypeVO.getRoom_type_name().contains("獨")) {
				 checkin_max=1;
			 }else if(roomTypeVO.getRoom_type_name().contains("三")||roomTypeVO.getRoom_type_name().contains("3")) {
				 checkin_max=3;
			 }else if(roomTypeVO.getRoom_type_name().contains("四")||roomTypeVO.getRoom_type_name().contains("4")) {
				 checkin_max=4;
			 }else if(roomTypeVO.getRoom_type_name().contains("六")||roomTypeVO.getRoom_type_name().contains("6")) {
				 checkin_max=6;
			 }else if(roomTypeVO.getRoom_type_name().contains("八")||roomTypeVO.getRoom_type_name().contains("8")) {
				 checkin_max=8;
			 }else if(roomTypeVO.getRoom_type_name().contains("十")||roomTypeVO.getRoom_type_name().contains("10")||roomTypeVO.getRoom_type_name().contains("總統")) {
				 checkin_max=10;
			 }
			req.setAttribute("dates", dates);
			req.setAttribute("firm_name", firm_name);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			req.setAttribute("checkin_max", checkin_max);
			req.setAttribute("roomTypeVO", roomTypeVO);
			req.setAttribute("empty_room", empty_room);
			req.setAttribute("roomSearchVO", roomSearchVO);
			String url="/front_end/roomtype/RoomTypeDetails.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
		
		
		
	}
}
