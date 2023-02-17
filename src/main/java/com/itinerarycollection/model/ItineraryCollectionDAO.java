package com.itinerarycollection.model;

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


public class ItineraryCollectionDAO implements ItineraryCollectionDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Itinerary_Collection (member_id,itinerary_type_id) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_Collection";
	private static final String GET_ONE_MEMBER_STMT = "SELECT * FROM  Itinerary_Collection where member_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_Collection set itinerary_type_id =? where member_id = ?";
	private static final String DELETE = "DELETE FROM Itinerary_Collection where member_id = ? and itinerary_type_id = ?";
	private static final String GET_ITINERARY_TYPE_STMT = "SELECT * FROM  Itinerary_Collection where  itinerary_type_id = ?";
	private static final String GET_ONE_ITRCLTN = "SELECT * FROM  Itinerary_Collection where member_id = ? and itinerary_type_id = ?";
	private static final String GET_ONE_MEMBER_LIST_STMT = "SELECT * FROM  Itinerary_Collection where member_id = ?";
	
	@Override
	public List<ItineraryCollectionVO> findByItinerary_type_id(Integer itinerary_type_id) {
		List<ItineraryCollectionVO> list = new ArrayList<ItineraryCollectionVO>();
		ItineraryCollectionVO itineraryCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ITINERARY_TYPE_STMT);
			pstmt.setInt(1, itinerary_type_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryCollectionVO = new ItineraryCollectionVO();
				itineraryCollectionVO.setMember_id(rs.getInt("member_id"));
				itineraryCollectionVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryCollectionVO.setItinerary_fav_date(rs.getTimestamp("itinerary_fav_date"));

				list.add(itineraryCollectionVO);
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
	public void insert(ItineraryCollectionVO itineraryCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineraryCollectionVO.getMember_id());
			pstmt.setInt(2, itineraryCollectionVO.getItinerary_type_id());

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
	public void  update(ItineraryCollectionVO itineraryCollectionVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, itineraryCollectionVO.getItinerary_type_id());
			pstmt.setInt(2, itineraryCollectionVO.getMember_id());
			

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
	public ItineraryCollectionVO findByPrimaryKey(Integer member_id) {
		ItineraryCollectionVO itineraryCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMBER_STMT);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryCollectionVO = new ItineraryCollectionVO();
				itineraryCollectionVO.setMember_id(rs.getInt("member_id"));
				itineraryCollectionVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryCollectionVO.setItinerary_fav_date(rs.getTimestamp("itinerary_fav_date"));
				

			}
			// Handle any driver errors
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
		return itineraryCollectionVO;
		
	}

	@Override
	public List<ItineraryCollectionVO> getAll() {
		List<ItineraryCollectionVO> list = new ArrayList<ItineraryCollectionVO>();
		ItineraryCollectionVO itineraryCollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryCollectionVO = new ItineraryCollectionVO();
				itineraryCollectionVO.setMember_id(rs.getInt("member_id"));
				itineraryCollectionVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryCollectionVO.setItinerary_fav_date(rs.getTimestamp("itinerary_fav_date"));
				
				list.add(itineraryCollectionVO);
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
	
	public void delete(Integer itinerary_type_id, Integer member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, itinerary_type_id);
			pstmt.setInt(2, member_id);

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
	
	public ItineraryCollectionVO getOneItineraryCollection(Integer member_id,Integer itinerary_type_id) {
		ItineraryCollectionVO itineraryCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ITRCLTN);
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, itinerary_type_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryCollectionVO = new ItineraryCollectionVO();
				itineraryCollectionVO.setMember_id(rs.getInt("member_id"));
				itineraryCollectionVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryCollectionVO.setItinerary_fav_date(rs.getTimestamp("itinerary_fav_date"));
			}
			// Handle any driver errors
		}catch (SQLException se) {
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
		return itineraryCollectionVO;
	}
	
	
	@Override
	public List<ItineraryCollectionVO> getCollectionList(Integer member_id) {
		List<ItineraryCollectionVO> list = new ArrayList<ItineraryCollectionVO>();
		ItineraryCollectionVO itineraryCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMBER_LIST_STMT);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryCollectionVO = new ItineraryCollectionVO();
				itineraryCollectionVO.setMember_id(rs.getInt("member_id"));
				itineraryCollectionVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryCollectionVO.setItinerary_fav_date(rs.getTimestamp("itinerary_fav_date"));
				list.add(itineraryCollectionVO);

			}
			// Handle any driver errors
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
		return list;
	}
//	public static void main(String[] args) {
//
//		ItineraryCollectionJDBCDAO dao = new ItineraryCollectionJDBCDAO();
//
//		// 查詢all
////		List<ItineraryCollectionVO> list = dao.getAll();
////		for (ItineraryCollectionVO a : list) {
////			System.out.print(a.getMember_id() + ",");
////			System.out.print(a.getItinerary_type_id() + ",");
////			System.out.print(a.getItinerary_fav_date() + "");
////
////			System.out.println();
////
////		}
//		
//		//新增
////		ItineraryCollectionVO itineraryCollectionVO1 = new ItineraryCollectionVO();
////		itineraryCollectionVO1.setMember_id(6);
////		itineraryCollectionVO1.setItinerary_type_id(6);
////		dao.insert(itineraryCollectionVO1);
//		
//		//修改
////		ItineraryCollectionVO itineraryCollectionVO2 = new ItineraryCollectionVO();
////		itineraryCollectionVO2.setItinerary_type_id(2);
////		itineraryCollectionVO2.setMember_id(1);
////		dao.update(itineraryCollectionVO2);
//		
//		//刪除
////		dao.delete(6, 6);
//		
//		//查詢
//		List<ItineraryCollectionVO> list =dao.findByPrimaryKey(1);
//		for (ItineraryCollectionVO b : list) {
//			System.out.print(b.getMember_id() + ",");
//			System.out.print(b.getItinerary_type_id() + ",");
//			System.out.print(b.getItinerary_fav_date() + "");
//
//			System.out.println();
//		}
//	}

}
