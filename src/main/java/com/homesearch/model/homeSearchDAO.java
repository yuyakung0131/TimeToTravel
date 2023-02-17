package com.homesearch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class homeSearchDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String FINALSQL = "SELECT FIRM_ID,firm_type,ft.firmtype_name,PRODUCT,title FROM(\r\n"
			+ "	SELECT tkt_name AS title,TKT_ID AS PRODUCT,FIRM_ID, (SELECT 1) AS firm_type FROM time_to_travel.ticket t\r\n"
			+ "	UNION ALL\r\n"
			+ "	SELECT ITINERARY_TITLE AS title,ITINERARY_TYPE_ID AS PRODUCT,FIRM_ID, (SELECT 3) AS firm_type FROM time_to_travel.itinerary_type WHERE ITINERARY_TYPE_STATE = 0\r\n"
			+ "	UNION ALL\r\n"
			+ "	SELECT firm_name AS title, FIRM_ID AS PRODUCT,FIRM_ID, (SELECT 2) AS firm_type FROM  time_to_travel.firm WHERE firmtype_id = 2 and FIRM_STATE = 0  -- 限定為飯店\r\n"
			+ "	) AS a \r\n"
			+ "JOIN time_to_travel.firm_type ft ON ft.firmtype_id = a.firm_type;";
		 public List<homeSearchVO> getFuzzySearch(){
				List<homeSearchVO> list = new ArrayList<homeSearchVO>();
				homeSearchVO homesearchVO= null;
				
				Connection con = null;
				PreparedStatement ps =null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					ps = con.prepareStatement(FINALSQL);
					rs = ps.executeQuery();
		 
		 while(rs.next()) {
			 
			 homesearchVO = new homeSearchVO();
			 homesearchVO.setFirm_id(rs.getInt("firm_id"));
			 homesearchVO.setFirm_type(rs.getInt("firm_type"));
			 homesearchVO.setFirmtype_name(rs.getString("firmtype_name"));
			 homesearchVO.setProduct(rs.getInt("product"));
			 homesearchVO.setTitle(rs.getString("title"));
				list.add(homesearchVO);	 
		 }
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
}
