package com.itinerarylocdetail.model;

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




public class ItineraryLocDetailDAO implements ItineraryLocDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO Itinerary_Loc_Detail (itinerary_loc_id,itinerary_type_id) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_Loc_Detail";
	private static final String GET_ONE_STMT = "SELECT * FROM  Itinerary_Loc_Detail where itinerary_loc_id = ? and itinerary_type_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_Loc_Detail set itinerary_type_id =? where itinerary_loc_id = ?";
	
	@Override
	public void insert(ItineraryLocDetailVO itineraryLocDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineraryLocDetailVO.getItinerary_loc_id());
			pstmt.setInt(2, itineraryLocDetailVO.getItinerary_type_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void  update(ItineraryLocDetailVO itineraryLocDetailVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, itineraryLocDetailVO.getItinerary_type_id());
			pstmt.setInt(2, itineraryLocDetailVO.getItinerary_loc_id());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public ItineraryLocDetailVO findByPrimaryKey(Integer itinerary_loc_id, Integer itinerary_type_id) {
		ItineraryLocDetailVO itineraryLocDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_loc_id);
			pstmt.setInt(2, itinerary_type_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryLocDetailVO = new ItineraryLocDetailVO();
				itineraryLocDetailVO.setItinerary_loc_id(rs.getInt("itinerary_loc_id"));
				itineraryLocDetailVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
			}
			// Handle any driver errors
		}  catch (SQLException se) {
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
		return itineraryLocDetailVO;
	}

	@Override
	public List<ItineraryLocDetailVO> getAll() {
		List<ItineraryLocDetailVO> list = new ArrayList<ItineraryLocDetailVO>();
		ItineraryLocDetailVO itineraryLocDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryLocDetailVO = new ItineraryLocDetailVO();
				itineraryLocDetailVO.setItinerary_loc_id(rs.getInt("itinerary_loc_id"));
				itineraryLocDetailVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				list.add(itineraryLocDetailVO);
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	
	public static void main(String[] args) {

		ItineraryLocDetailDAO dao = new ItineraryLocDetailDAO();

		// 查詢all
		List<ItineraryLocDetailVO> list = dao.getAll();
		for (ItineraryLocDetailVO a : list) {
			System.out.print(a.getItinerary_loc_id() + ",");
			System.out.print(a.getItinerary_type_id());


			System.out.println();

		}
		
		//新增
//		ItineraryLocDetailVO itineraryLocDetailVO1 = new ItineraryLocDetailVO();
//		itineraryLocDetailVO1.setItinerary_loc_id(22);
//		itineraryLocDetailVO1.setItinerary_type_id(6);
//		dao.insert(itineraryLocDetailVO1);
//		
		//修改
		ItineraryLocDetailVO itineraryLocDetailVO2 = new ItineraryLocDetailVO();
		itineraryLocDetailVO2.setItinerary_type_id(5);
		itineraryLocDetailVO2.setItinerary_loc_id(17);
		dao.update(itineraryLocDetailVO2);
		
		//查詢
		ItineraryLocDetailVO itineraryLocDetailVO3 =dao.findByPrimaryKey(20, 4);
		
		System.out.print(itineraryLocDetailVO3.getItinerary_loc_id() + ",");
		System.out.print(itineraryLocDetailVO3.getItinerary_type_id());


		System.out.println("------------------");
		
	}

	public void insert(ItineraryLocDetailVO itineraryLocDetailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			
		
			pstmt = con.prepareStatement(INSERT_STMT);
			
			System.out.println(itineraryLocDetailVO.getItinerary_loc_id());
			System.out.println(itineraryLocDetailVO.getItinerary_type_id());
			
			pstmt.setInt(1, itineraryLocDetailVO.getItinerary_loc_id());
			pstmt.setInt(2, itineraryLocDetailVO.getItinerary_type_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	
}