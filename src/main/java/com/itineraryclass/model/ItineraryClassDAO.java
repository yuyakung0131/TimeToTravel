package com.itineraryclass.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;






public class ItineraryClassDAO implements ItineraryClassDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO itinerary_class (itinerary_class_name) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT * FROM itinerary_Class";
	private static final String GET_ONE_STMT = "SELECT * FROM  itinerary_class where  itinerary_class_id = ?";
	private static final String UPDATE = "UPDATE itinerary_class set  itinerary_class_name=? where itinerary_class_id = ?";
	
	@Override
	public void insert(ItineraryClassVO itineraryClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itineraryClassVO.getItinerary_class_name());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	}
	
	@Override
	public void update(ItineraryClassVO itineraryClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, itineraryClassVO.getItinerary_class_name());
			pstmt.setInt(2, itineraryClassVO.getItinerary_class_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	}
	
	@Override
	public ItineraryClassVO findByPrimaryKey(Integer itinerary_class_id) {
		ItineraryClassVO itineraryClassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_class_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryClassVO = new ItineraryClassVO();
				itineraryClassVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
				itineraryClassVO.setItinerary_class_name(rs.getString("itinerary_class_name"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return itineraryClassVO;
	}

	@Override
	public List<ItineraryClassVO> getAll() {
		List<ItineraryClassVO> list = new ArrayList<ItineraryClassVO>();
		ItineraryClassVO itineraryClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryClassVO = new ItineraryClassVO();
				itineraryClassVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
				itineraryClassVO.setItinerary_class_name(rs.getString("itinerary_class_name"));
				list.add(itineraryClassVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
	
//	public static void main(String[] args) {
//
//		ItineraryClassJDBCDAO dao = new ItineraryClassJDBCDAO();
//
//		// 查詢all
//		List<ItineraryClassVO> list = dao.getAll();
//		for (ItineraryClassVO a : list) {
//			System.out.print(a.getItinerary_class_id() + ",");
//			System.out.print(a.getItinerary_class_name());
//			System.out.println();
//
//		}
//		
//		//新增
////		ItineraryClassVO itineraryClassVO1 = new ItineraryClassVO();
////		itineraryClassVO1.setItinerary_class_name("單車遊");
////		dao.insert(itineraryClassVO1);
//		
//		//修改
////		ItineraryClassVO itineraryClassVO2 = new ItineraryClassVO();		
////		itineraryClassVO2.setItinerary_class_id(7);
////		itineraryClassVO2.setItinerary_class_name("線上遊");
////		dao.update(itineraryClassVO2);
//		
//		//查詢
//		ItineraryClassVO itineraryClassVO3 =dao.findByPrimaryKey(1);
//		System.out.print(itineraryClassVO3.getItinerary_class_id() + ",");
//		System.out.print(itineraryClassVO3.getItinerary_class_name());
//		System.out.println("----------------------------");
//		
//	}
//	
	
}