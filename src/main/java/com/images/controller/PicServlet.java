package com.images.controller;

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

public class PicServlet extends HttpServlet {
	
	Connection con;//�A�Ω�d��,���A�Ω�transaction

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try
		 {
			Statement stmt = con.createStatement();
			String tkt_img_id =req.getParameter("tkt_img_id").trim();
			ResultSet rs = stmt.executeQuery(
				"select TKT_IMG from TKT_IMG where TKT_IMG_ID="+tkt_img_id);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(1));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				

				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len); 
				}
				in.close();
			} 
//			else {
////				res.sendError(HttpServletResponse.SC_NOT_FOUND);//�䤣�쨺�i��
//				InputStream in =getServletContext().getResourceAsStream("/NoData/none2.jpg");
////				byte[]b=new byte[in.available()];
////				in.read(b);
////				out.write(b);
////				in.close();
//				
////				�ϥ�java 9���s��k�Ӿާ@
//				byte[]b=in.readAllBytes();
//				out.write(b);
//				in.close();
//				
//			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);//�ŭ�
//			InputStream in =getServletContext().getResourceAsStream("/NoData/null.jpg");
////			byte[]b=new byte[in.available()];
////			in.read(b);
////			out.write(b);
////			in.close();
//			
////			�ϥ�java 9���s��k�Ӿާ@
//			byte[]b=in.readAllBytes();
//			out.write(b);
//			in.close();
		}
	}

	public void init() throws ServletException {
    	try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
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
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}