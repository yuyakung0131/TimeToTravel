package com.itineraryloc.model;

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


public class ItineraryLocDAO implements ItineraryLocDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO Itinerary_Loc (itinerary_loc_name) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_Loc";
	private static final String GET_ONE_STMT = "SELECT * FROM  Itinerary_Loc where itinerary_loc_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_Loc set itinerary_loc_name =? where itinerary_loc_id = ?";
	private static final String DELETE = "DELETE FROM Itinerary_Loc where itinerary_loc_id = ?";
	
	@Override
	public void insert(ItineraryLocVO itineraryLocVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itineraryLocVO.getItinerary_loc_name());

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
	public void  update(ItineraryLocVO itineraryLocVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, itineraryLocVO.getItinerary_loc_name());
			pstmt.setInt(2, itineraryLocVO.getItinerary_loc_id());
			

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
	public void delete(Integer itinerary_loc_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, itinerary_loc_id);

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
	public ItineraryLocVO findByPrimaryKey(Integer itinerary_loc_id) {
		ItineraryLocVO itineraryLocVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_loc_id);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryLocVO = new ItineraryLocVO();
				itineraryLocVO.setItinerary_loc_id(rs.getInt("itinerary_loc_id"));
				itineraryLocVO.setItinerary_loc_name(rs.getString("itinerary_loc_name"));
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
		return itineraryLocVO;
	}
	
	@Override
	public List<ItineraryLocVO> getAll() {
		List<ItineraryLocVO> list = new ArrayList<ItineraryLocVO>();
		ItineraryLocVO itineraryLocVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryLocVO = new ItineraryLocVO();
				itineraryLocVO.setItinerary_loc_id(rs.getInt("itinerary_loc_id"));
				itineraryLocVO.setItinerary_loc_name(rs.getString("itinerary_loc_name"));
				list.add(itineraryLocVO);
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
//		ItineraryLocJDBCDAO dao = new ItineraryLocJDBCDAO();
//
//		// 查詢all
//		List<ItineraryLocVO> list = dao.getAll();
//		for (ItineraryLocVO a : list) {
//			System.out.print(a.getItinerary_loc_id() + ",");
//			System.out.print(a.getItinerary_loc_name());
//
//
//			System.out.println();
//
//		}
//		
//		//新增
//		ItineraryLocVO itineraryLocVO1 = new ItineraryLocVO();
//		itineraryLocVO1.setItinerary_loc_name("蘆洲");
//		dao.insert(itineraryLocVO1);
//		
//		//修改
////		ItineraryLocVO itineraryLocVO2 = new ItineraryLocVO();
////		itineraryLocVO2.setItinerary_loc_name("三重");
////		itineraryLocVO2.setItinerary_loc_id(21);	
////		dao.update(itineraryLocVO2);
//		
//		//刪除
////		dao.delete(21);
//		
//		//查詢
//		ItineraryLocVO itineraryLocVO3 = dao.findByPrimaryKey(1);
//		System.out.print(itineraryLocVO3.getItinerary_loc_id() + ",");
//		System.out.print(itineraryLocVO3.getItinerary_loc_name());
//
//
//		System.out.println("-----------------");
//	}
	
	
}