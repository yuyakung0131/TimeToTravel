package com.itinerary.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ItineraryDAO implements ItineraryDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO itinerary (itinerary_type_id,itinerary_start,itinerary_end,itinerary_now,itinerary_price,itinerary_min,itinerary_max,entered_start,entered_end,itinerary_state) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM itinerary";
	private static final String GET_ONE_STMT = "SELECT * FROM  itinerary where  itinerary_id = ?";
//	private static final String DELETE = "DELETE FROM emp2 where empno = ?";
	private static final String UPDATE = "UPDATE itinerary set  itinerary_type_id=?,itinerary_start=?,itinerary_end=?,itinerary_now=?,itinerary_price=?,itinerary_min=?,itinerary_max=?,entered_start=?,entered_end=?,itinerary_state=? where itinerary_id= ?";
	private static final String GET_ITINERARY_TYPE_STMT = "SELECT * FROM  itinerary where  itinerary_type_id = ?";
	
	@Override
	public List<ItineraryVO> findByItinerary_type_id(Integer itinerary_type_id) {
		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
		ItineraryVO itineraryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ITINERARY_TYPE_STMT);
			pstmt.setInt(1, itinerary_type_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
				itineraryVO.setItinerary_now(rs.getInt("itinerary_now"));
				itineraryVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryVO.setEntered_start(rs.getDate("entered_start"));
				itineraryVO.setEntered_end(rs.getDate("entered_end"));
				itineraryVO.setItinerary_state(rs.getByte("itinerary_state"));

				list.add(itineraryVO);
			}

			// Handle any driver errors
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
	
	@Override
	public void insert(ItineraryVO itineraryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineraryVO.getItinerary_type_id());
			pstmt.setDate(2, itineraryVO.getItinerary_start());
			pstmt.setDate(3, itineraryVO.getItinerary_end());
			pstmt.setInt(4, itineraryVO.getItinerary_now());
			pstmt.setInt(5, itineraryVO.getItinerary_price());
			pstmt.setInt(6, itineraryVO.getItinerary_min());
			pstmt.setInt(7, itineraryVO.getItinerary_max());
			pstmt.setDate(8, itineraryVO.getEntered_start());
			pstmt.setDate(9, itineraryVO.getEntered_end());
			pstmt.setByte(10, itineraryVO.getItinerary_state());

			pstmt.executeUpdate();

	
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
	public void update(ItineraryVO itineraryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, itineraryVO.getItinerary_type_id());
			pstmt.setDate(2, itineraryVO.getItinerary_start());
			pstmt.setDate(3, itineraryVO.getItinerary_end());
			pstmt.setInt(4, itineraryVO.getItinerary_now());
			pstmt.setInt(5, itineraryVO.getItinerary_price());
			pstmt.setInt(6, itineraryVO.getItinerary_min());
			pstmt.setInt(7, itineraryVO.getItinerary_max());
			pstmt.setDate(8, itineraryVO.getEntered_start());
			pstmt.setDate(9, itineraryVO.getEntered_end());
			pstmt.setByte(10, itineraryVO.getItinerary_state());
			pstmt.setInt(11, itineraryVO.getItinerary_id());
			

			pstmt.executeUpdate();

	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer itineraryVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public ItineraryVO findByPrimaryKey(Integer itinerary_id) {
		ItineraryVO itineraryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
				itineraryVO.setItinerary_now(rs.getInt("itinerary_now"));
				itineraryVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryVO.setEntered_start(rs.getDate("entered_start"));
				itineraryVO.setEntered_end(rs.getDate("entered_end"));
				itineraryVO.setItinerary_state(rs.getByte("itinerary_state"));

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
		return itineraryVO;
	}
		

	@Override
	public List<ItineraryVO> getAll() {
		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
		ItineraryVO itineraryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
				itineraryVO.setItinerary_now(rs.getInt("itinerary_now"));
				itineraryVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryVO.setEntered_start(rs.getDate("entered_start"));
				itineraryVO.setEntered_end(rs.getDate("entered_end"));
				itineraryVO.setItinerary_state(rs.getByte("itinerary_state"));

				list.add(itineraryVO);
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
//		ItineraryJDBCDAO dao = new ItineraryJDBCDAO();
//
//		// 查詢all
//		List<ItineraryVO> list = dao.getAll();
//		for (ItineraryVO a : list) {
//			System.out.print(a.getItinerary_id() + ",");
//			System.out.print(a.getItinerary_type_id() + ",");
//			System.out.print(a.getItinerary_start() + ",");
//			System.out.print(a.getItinerary_end() + ",");
//			System.out.print(a.getItinerary_now() + ",");
//			System.out.print(a.getItinerary_price() + ",");
//			System.out.print(a.getItinerary_min() + ",");
//			System.out.print(a.getItinerary_max() + ",");
//			System.out.print(a.getEntered_start() + ",");
//			System.out.print(a.getEntered_end() + ",");
//			System.out.print(a.getItinerary_state());
//
//			System.out.println("");
//			
//
//		}
//		
//		//新增
////		ItineraryVO itineraryVO1 = new ItineraryVO();
////		itineraryVO1.setItinerary_type_id(6);
////		itineraryVO1.setItinerary_start(java.sql.Date.valueOf("2023-09-01"));
////		itineraryVO1.setItinerary_end(java.sql.Date.valueOf("2023-09-01"));
////		itineraryVO1.setItinerary_now(0);
////		itineraryVO1.setItinerary_price(1500);
////		itineraryVO1.setItinerary_min(4);
////		itineraryVO1.setItinerary_max(30);
////		itineraryVO1.setEntered_start(java.sql.Date.valueOf("2023-08-01"));
////		itineraryVO1.setEntered_end(java.sql.Date.valueOf("2023-08-25"));
////		itineraryVO1.setItinerary_state(new Byte((byte)3));
////		dao.insert(itineraryVO1);
//
//		
//		//修改
////		ItineraryVO itineraryVO2 = new ItineraryVO();		
////		itineraryVO2.setItinerary_id(6);
////		itineraryVO2.setItinerary_type_id(6);
////		itineraryVO2.setItinerary_start(java.sql.Date.valueOf("2023-10-02"));
////		itineraryVO2.setItinerary_end(java.sql.Date.valueOf("2023-10-03"));
////		itineraryVO2.setItinerary_now(1);
////		itineraryVO2.setItinerary_price(1555);
////		itineraryVO2.setItinerary_min(4);
////		itineraryVO2.setItinerary_max(20);
////		itineraryVO2.setEntered_start(java.sql.Date.valueOf("2023-09-10"));
////		itineraryVO2.setEntered_end(java.sql.Date.valueOf("2023-09-21"));
////		itineraryVO2.setItinerary_state(new Byte((byte)2));
////		dao.update(itineraryVO2);
//		
//		//查詢
//		ItineraryVO itineraryVO3 =dao.findByPrimaryKey(1);
//		System.out.print(itineraryVO3.getItinerary_id() + ",");
//		System.out.print(itineraryVO3.getItinerary_type_id() + ",");
//		System.out.print(itineraryVO3.getItinerary_start() + ",");
//		System.out.print(itineraryVO3.getItinerary_end() + ",");
//		System.out.print(itineraryVO3.getItinerary_now() + ",");
//		System.out.print(itineraryVO3.getItinerary_price() + ",");
//		System.out.print(itineraryVO3.getItinerary_min() + ",");
//		System.out.print(itineraryVO3.getItinerary_max() + ",");
//		System.out.print(itineraryVO3.getEntered_start() + ",");
//		System.out.print(itineraryVO3.getEntered_end() + ",");
//		System.out.print(itineraryVO3.getItinerary_state());
//
//		System.out.println("--------------------------------------");
//		
//	}
}