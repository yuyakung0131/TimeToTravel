package com.itinerarycollection.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itinerary.model.ItineraryVO;
import com.itinerarycollection.model.ItineraryCollectionService;
import com.itinerarycollection.model.ItineraryCollectionVO;
import com.itinerarytype.model.ItineraryTypeService;



public class ItineraryCollectionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("showFavorite".equals(action)) {
//			/**** 1.取得參數 ****/
//			Integer member_id = new Integer(req.getParameter("member_id"));
//
//			/**** 3.查詢行程資料list ****/
//			ItineraryCollectionService itineraryCollectionSvc = new ItineraryCollectionService();
//			List<ItineraryCollectionVO> itineraryCollectionList = itineraryCollectionSvc.getCollectionByMember(member_id);
//			req.setAttribute("itineraryCollectionList", itineraryCollectionList);
//
//			/**** 5.轉交 ****/
//			String url = "/front_end/member/mcItrFavorite.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//
//		}
		
		if("insert_delete_itineraryCollection".equals(action)) {
			Integer member_id=Integer.valueOf(req.getParameter("member_id"));
			Integer itinerary_type_id=Integer.valueOf(req.getParameter("itinerary_type_id"));
			
			ItineraryCollectionService itineraryCollectionSvc=new ItineraryCollectionService();
			ItineraryCollectionVO itineraryCollectionVO=itineraryCollectionSvc.getOneItineraryCollection(member_id, itinerary_type_id);
//			req.setAttribute("itineraryCollectionVO", itineraryCollectionVO);
			
			String color;
			if(itineraryCollectionVO==null) {
				itineraryCollectionSvc.addItineraryCollection(member_id, itinerary_type_id);
				System.out.println("success");
				color="red";
			}else {
				itineraryCollectionSvc.delete(member_id, itinerary_type_id);
				System.out.println("fail");
				color="gray";
			}
			PrintWriter out = res.getWriter();
			out.print(color);
			out.close();
			
//			String url = "/front_end/itinerarytype/do?itinerary_type_id="+itinerary_type_id+"&action=showDetail";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			
			
		}
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//	
//				/***************************1.接收請求參數***************************************/
//				Integer member_id=Integer.valueOf(req.getParameter("member_id"));
//				Integer itinerary_type_id=Integer.valueOf(req.getParameter("itinerary_type_id"));
//				
//				/***************************2.開始刪除資料***************************************/
//				ItineraryCollectionService itineraryCollectionSvc=new ItineraryCollectionService();
//				itineraryCollectionSvc.delete(member_id,itinerary_type_id);
//				
//				String url = "/front_end/member/mcItrFavorite.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//		}
		 if ("itrFavoritedelete".equals(action)) { // 來自listAllEmp.jsp

				
				/***************************1.接收請求參數***************************************/
				Integer member_id=Integer.valueOf(req.getParameter("member_id"));
				Integer itinerary_type_id=Integer.valueOf(req.getParameter("itinerary_type_id"));
				
				/***************************2.開始刪除資料***************************************/
				ItineraryCollectionService itineraryCollectionSvc=new ItineraryCollectionService();
				itineraryCollectionSvc.delete(member_id,itinerary_type_id);
				
				String url = "/front_end/member/Favlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}
	}
}