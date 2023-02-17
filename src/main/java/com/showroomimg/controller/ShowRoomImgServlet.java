package com.showroomimg.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ShowRoomImgServlet extends HttpServlet {
	
	Connection con;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();

		try
		 {
			Statement stmt = con.createStatement();
			String room_img_id =req.getParameter("room_img_id").trim();
			ResultSet rs = stmt.executeQuery(
				"select * from room_img where room_img_id= " +room_img_id );

			while (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(3));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				

				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len); 
				}
				in.close();
			} 
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
    	try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
			 con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}