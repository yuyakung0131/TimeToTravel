package com.itinerarycomment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itinerarycollection.model.ItineraryCollectionService;
import com.itinerarycollection.model.ItineraryCollectionVO;
import com.itinerarycomment.model.ItineraryCommentService;
import com.itinerarycomment.model.ItineraryCommentVO;
import com.itinerarytype.model.ItineraryTypeService;
import com.itinerarytype.model.ItineraryTypeVO;

public class ItineraryCommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addItrComment".equals(action)) {
			/*************************** 2.接收請求參數 *****************************************/
//			獲取insert欄位
			Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id").trim());
			Integer member_id = Integer.valueOf(req.getParameter("member_id").trim());
			String itinerary_comment_post = req.getParameter("itinerary_comment_post").trim();
			Integer itinerary_comment_star = Integer.valueOf(req.getParameter("itinerary_comment_star").trim());
			ItineraryCommentVO itineraryCommentVO = new ItineraryCommentVO();

			itineraryCommentVO.setItinerary_type_id(itinerary_type_id);
			itineraryCommentVO.setMember_id(member_id);
			itineraryCommentVO.setItinerary_comment_post(itinerary_comment_post);
			itineraryCommentVO.setItinerary_comment_star(itinerary_comment_star);

//			==========
			ItineraryCommentService itineraryCommentSvc = new ItineraryCommentService();
			ItineraryTypeVO itineraryTypevo = new ItineraryTypeVO();
			itineraryTypevo.setItinerary_type_id(itinerary_type_id);
			itineraryTypevo.setItinerary_total_score(itinerary_comment_star);

			/*************************** 2.開始新增資料 ***************************************/

			itineraryCommentVO = itineraryCommentSvc.addItineraryComment(itinerary_type_id, member_id,
					itinerary_comment_post, itinerary_comment_star);
			/***************************
			 * 3.同時更新行程種類評論資料
			 ***************************************/
			ItineraryTypeService itineraryTypeSvc = new ItineraryTypeService();
			itineraryTypevo = itineraryTypeSvc.updateItrTotalComment(itinerary_type_id, itinerary_comment_star);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/member/Orderlist.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交.jsp
			successView.forward(req, res);
		}
	}
}
