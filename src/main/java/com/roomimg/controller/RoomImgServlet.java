package com.roomimg.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.roomimg.model.RoomImgService;
import com.roomimg.model.RoomImgVO;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;
@MultipartConfig
public class RoomImgServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
	
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		
		req.setCharacterEncoding("UTF-8");
		String action= req.getParameter("action");
		
		if("img_data".equals(action)) {
			Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
			RoomTypeService roomTypeSvc=new RoomTypeService();
			RoomTypeVO roomTypeVO=roomTypeSvc.getOneRoomType(room_type_id);
			
		
			req.setAttribute("roomTypeVO",roomTypeVO);
			req.setAttribute("room_type_id",room_type_id);
			String url="/back_end/roomtype/viewRoomImg.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if("insert_room_img".equals(action)) {
			byte[] room_img = req.getPart("roomImgUpFile").getInputStream().readAllBytes();
			Integer room_type_id=Integer.valueOf(req.getParameter("room_type_id"));
			if (!(room_img.length == 0) && !(req.getPart("roomImgUpFile").getContentType().contains("image"))) {
				RequestDispatcher failView=req.getRequestDispatcher("/back_end/roomtype/roomImg.do?action=img_data&room_type_id="+room_type_id);
				failView.forward(req, res);
				return;
			}
			
			if (room_img.length == 0||room_img==null) {
				RequestDispatcher failView=req.getRequestDispatcher("/back_end/roomtype/roomImg.do?action=img_data&room_type_id="+room_type_id);
				failView.forward(req, res);
				return;
			}
			RoomImgService roomImgSvc=new RoomImgService();
			roomImgSvc.insert(room_type_id, room_img);
			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}
		
		if("update_room_img".equals(action)) {
			byte[] room_img =req.getPart("roomImgUpFile").getInputStream().readAllBytes();
			Integer room_img_id =Integer.valueOf(req.getParameter("room_img_id"));
			Integer room_type_id =Integer.valueOf(req.getParameter("room_type_id"));
			RoomImgService roomImgSvc=new RoomImgService();
			if (!(room_img.length == 0) && !(req.getPart("roomImgUpFile").getContentType().contains("image"))) {
				RequestDispatcher failView=req.getRequestDispatcher("/back_end/roomtype/roomImg.do?action=img_data&room_type_id="+room_type_id);
				failView.forward(req, res);
				return;
			}
			
			if (room_img.length == 0||room_img==null) {
				RequestDispatcher failView=req.getRequestDispatcher("/back_end/roomtype/roomImg.do?action=img_data&room_type_id="+room_type_id);
				failView.forward(req, res);
				return;
			}
			
			roomImgSvc.update(room_img_id, room_img);
			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}
		
		if("delete_img".equals(action)) {
			Integer room_img_id=Integer.valueOf(req.getParameter("room_img_id"));
			RoomImgService roomImgSvc=new RoomImgService();
			roomImgSvc.delete(room_img_id);
			String url = "/back_end/roomtype/listAllRoomType.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}
		

	}
	
}
