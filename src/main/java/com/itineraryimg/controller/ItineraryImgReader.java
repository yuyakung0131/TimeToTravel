package com.itineraryimg.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.itineraryimg.model.ItineraryImgService;
import com.itineraryimg.model.ItineraryImgVO;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ItineraryImgReader extends HttpServlet {

	Connection con;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
			try {
				Statement stmt = con.createStatement();
				String itinerary_type_id = req.getParameter("itinerary_type_id").trim();
				String offset = req.getParameter("offset").trim();
				
				
//				ItineraryImgService itrImgSvcImgService = new ItineraryImgService();
//				List<ItineraryImgVO> imgList = itrImgSvcImgService.getItrImgByType(Integer.valueOf(itinerary_type_id).intValue());
				
//				for (int i=0 ; i< imgList.size();i++) {
//					System.out.println(imgList.size());
				
				
//				ResultSet rs = stmt.executeQuery("SELECT * FROM itinerary_img where ITINERARY_TYPE_ID = " + itinerary_type_id);
//				ResultSet rs = stmt.executeQuery("SELECT * FROM itinerary_img where ITINERARY_TYPE_ID IN (1) order by ITINERARY_IMG_ID  LIMIT 1 OFFSET 0;");
				ResultSet rs = stmt.executeQuery("SELECT * FROM itinerary_img where ITINERARY_TYPE_ID IN ("+itinerary_type_id+") order by ITINERARY_IMG_ID  LIMIT 1 OFFSET "+offset);
				
				
//				System.out.println("SELECT * FROM itinerary_img where ITINERARY_TYPE_ID IN ("+itinerary_type_id+") order by ITINERARY_IMG_ID  LIMIT 1 OFFSET "+offset);
				
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("itinerary_img"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
				
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
						
					}
					in.close();
				} else {
					// res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			 }catch (Exception e) {
				// System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
				byte[] b = in.readAllBytes(); // Java 9之後才能用的方法
//			byte[] b = new byte[in.available()];   // Java 9之前用的方法
//			in.read(b);
				out.write(b);
				in.close();
			}
		}


	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource 	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}