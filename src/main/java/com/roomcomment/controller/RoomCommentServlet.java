package com.roomcomment.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomcomment.model.RoomCommentService;
import com.roomcomment.model.RoomCommentVO;

public class RoomCommentServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//評論
		
		
		// 新增insert=======================================================================================
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// 將此集合存儲在請求範圍內，以備不時之需
			// 發送 ErrorPage 視圖。
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer room_type_id = Integer.valueOf(req.getParameter("room_type_id").trim());
			Integer member_id = Integer.valueOf(req.getParameter("member_id").trim());
			String room_comment_content = String.valueOf(req.getParameter("room_comment_content").trim());
			Integer room_comment_star = Integer.valueOf(req.getParameter("room_comment_star").trim());

			// 建立這個NewsVO物件是為了讓輸入錯誤的話，可以儲存其他正確的值
			// 宣告變數 創立物件
			RoomCommentVO roomCommentVO = new RoomCommentVO();
			roomCommentVO.setRoom_type_id(room_type_id);
			roomCommentVO.setMember_id(member_id);
			roomCommentVO.setRoom_comment_content(room_comment_content);
			roomCommentVO.setRoom_comment_star(room_comment_star);

			// 如果有錯誤，請將使用發送回表單
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("roomCommentVO", roomCommentVO); // 含有輸入格式錯誤的newsVO物件,也存入req 將使用者輸入錯的在顯示一次
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/roomOrder_Comment.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			RoomCommentService roomCommentService = new RoomCommentService();
			roomCommentVO = roomCommentService.addRoomComment(room_type_id, member_id, room_comment_content,
					room_comment_star);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/member/Orderlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNews.jsp
			successView.forward(req, res);
		}
	}

}
