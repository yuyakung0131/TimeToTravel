package com.roomcollection.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomcollection.model.RoomCollectionService;
import com.roomcollection.model.RoomCollectionVO;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

import oracle.jdbc.driver.json.binary.JsonpOsonObject;

public class RoomCollectionServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		
		//用ajax(collection.js檔)
		if("insert_delete_roomCollection".equals(action)) {
			Integer member_id=Integer.valueOf(req.getParameter("login_member_id"));
			Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
	
			RoomCollectionService roomCollectionSvc=new RoomCollectionService();
			RoomCollectionVO roomCollectionVO=roomCollectionSvc.getOneRoomCollection(member_id, room_type_id);
			req.setAttribute("roomCollectionVO", roomCollectionVO);
			
			if(roomCollectionVO==null) {
				roomCollectionSvc.insert(member_id, room_type_id);
				System.out.println("insert collection");
			}else {
				roomCollectionSvc.delete(member_id, room_type_id);
				System.out.println("delete collection");
			}
			
			
		}
		if("delete_roomCollection".equals(action)) {
			   Integer member_id=Integer.valueOf(req.getParameter("login_member_id"));
			   Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
			   
			   RoomCollectionService roomCollectionSvc=new RoomCollectionService();
			   RoomCollectionVO roomCollectionVO=roomCollectionSvc.getOneRoomCollection(member_id, room_type_id);
			   req.setAttribute("roomCollectionVO", roomCollectionVO);
			   
			   
			    roomCollectionSvc.delete(member_id, room_type_id);
			   String url="/front_end/member/Favlist.jsp";
			   RequestDispatcher successView=req.getRequestDispatcher(url);
			   successView.forward(req, res);
		}
		
	}
}
