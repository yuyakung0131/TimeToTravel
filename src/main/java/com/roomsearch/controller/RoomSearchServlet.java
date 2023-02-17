package com.roomsearch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firm.model.FirmService;
import com.firm.model.FirmVO;
import com.roomcollection.model.RoomCollectionService;
import com.roomcollection.model.RoomCollectionVO;
import com.roomsearch.model.RoomSearchService;
import com.roomsearch.model.RoomSearchVO;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class RoomSearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		
		//用飯店名稱搜尋區間空房
		if("search_roomFirm_by_name".equals(action)){
			String firm_name=req.getParameter("search_words_name");
			String start_date=req.getParameter("start_date");
			String end_date= req.getParameter("end_date");
			RoomSearchService roomSearchSvc=new RoomSearchService();
			List<RoomSearchVO> roomSearchList=roomSearchSvc.getEmptyRoomByFirmName(firm_name, start_date, end_date);
			if(roomSearchList==null||roomSearchList.size()==0) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/roomtype/listAllRoomFirm.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("search_words_name", firm_name);
			req.setAttribute("roomSearchList", roomSearchList);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			String url="/front_end/roomtype/listAllSearchRoomFirm.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//用飯店地址搜尋區間空房
		if("search_roomFirm_by_add".equals(action)){
			String firm_operate_add=req.getParameter("search_words_add");
			String start_date=req.getParameter("start_date");
			String end_date= req.getParameter("end_date");
			RoomSearchService roomSearchSvc=new RoomSearchService();
			List<RoomSearchVO> roomSearchList=roomSearchSvc.getEmptyRoomByFirmAdd(firm_operate_add, start_date, end_date);
			if(roomSearchList==null||roomSearchList.size()==0) {
				RequestDispatcher failView=req.getRequestDispatcher("/front_end/roomtype/listAllRoomFirm.jsp");
				failView.forward(req, res);
				return;
			}
			req.setAttribute("roomSearchList", roomSearchList);
			req.setAttribute("search_words_add", firm_operate_add);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			String url="/front_end/roomtype/listAllSearchRoomFirm.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//點地址搜飯店(含時間)
		if("get_one_firm_add_by_start_date".equals(action)) {
			String firm_operate_add=req.getParameter("firm_operate_add");
			String start_date=req.getParameter("start_date");
			String end_date= req.getParameter("end_date"); 
			
			RoomSearchService roomSearchSvc=new RoomSearchService();
			List<RoomSearchVO> roomSearchList=roomSearchSvc.getFirmByAddByStartDate(firm_operate_add, start_date);
			req.setAttribute("roomSearchList", roomSearchList);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			String url="/front_end/roomtype/listAllSearchRoomFirm.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		//點地址搜飯店(不含時間)
		if("get_one_firm_add".equals(action)) {
			String firm_operate_add=req.getParameter("firm_operate_add");
		
			FirmService firmSvc=new FirmService();
			List<FirmVO> firmList=firmSvc.getRoomFirmAddress(firm_operate_add);
			
			req.setAttribute("roomSearchList", firmList);

		
			String url="/front_end/roomtype/listAllRoomFirm.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		
		//用當個飯店搜尋空房
		if("search_empty_room_byOneFirm".equals(action)) {
			String memberStr=req.getParameter("login_member_id");
			if(!"".equals(memberStr)) {
			Integer member_id=Integer.valueOf(memberStr);
			RoomCollectionService roomCollectionSvc=new RoomCollectionService();
			List<RoomCollectionVO> roomCollectionList=roomCollectionSvc.getCollectionByMember(member_id);
			req.setAttribute("roomCollectionList", roomCollectionList);
			req.setAttribute("member_id", member_id);
			}
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
			req.setAttribute("search_words_name", firm_name);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			req.setAttribute("room_firm_id", roomFirm_id);
			req.setAttribute("roomTypeList", roomTypeList);
			req.setAttribute("roomSearchList", roomSearchList);
			String url="/front_end/roomtype/listAllSearchRoomType.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
	}
}
