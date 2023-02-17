package com.itineraryorder.model;

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

import com.itinerarytype.model.ItineraryTypeVO;



public class ItineraryOrderDAO implements ItineraryOrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO Itinerary_order (member_id,itinerary_id,itinerary_people_num,itinerary_ttl_price,itinerary_order_state,itinerary_refund_state,itinerary_order_memo) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_order";
	private static final String GET_ONE_STMT = "SELECT * FROM  Itinerary_order where itinerary_order_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_order set member_id =?, itinerary_id=?, itinerary_people_num=?, itinerary_ttl_price=?, itinerary_order_state=?, itinerary_refund_state=?, itinerary_order_memo=?where itinerary_order_id = ?";
	private static final String GET_MEMBER_STMT = "SELECT * FROM  itinerary_order where  member_id = ?";
	private static final String GET_LATEST_ONE_STMT = "SELECT * FROM itinerary_order where member_id = ? and itinerary_id = ? order by ITINERARY_ORDER_TIME desc limit 1;";
	private static final String UPDATE_ITRORDER = "UPDATE Itinerary_order set itinerary_order_state=?, itinerary_refund_state=? where itinerary_order_id = ?";
//	private static final String GET_ITINERARY = "SELECT * FROM  itinerary_order where  itinerary_id = ?;";
	
	
	@Override	
	public void insert(ItineraryOrderVO itineraryOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineraryOrderVO.getMember_id());
			pstmt.setInt(2, itineraryOrderVO.getItinerary_id());
			pstmt.setInt(3, itineraryOrderVO.getItinerary_people_num());
			pstmt.setInt(4, itineraryOrderVO.getItinerary_ttl_price());
			pstmt.setInt(5, itineraryOrderVO.getItinerary_order_state());
			pstmt.setInt(6, itineraryOrderVO.getItinerary_refund_state());
			pstmt.setString(7, itineraryOrderVO.getItinerary_order_memo());


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
	public void update(ItineraryOrderVO itineraryOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, itineraryOrderVO.getMember_id());
			pstmt.setInt(2, itineraryOrderVO.getItinerary_id());
			pstmt.setInt(3, itineraryOrderVO.getItinerary_people_num());
			pstmt.setInt(4, itineraryOrderVO.getItinerary_ttl_price());
			pstmt.setInt(5, itineraryOrderVO.getItinerary_order_state());
			pstmt.setInt(6, itineraryOrderVO.getItinerary_refund_state());
			pstmt.setString(7, itineraryOrderVO.getItinerary_order_memo());
			pstmt.setInt(8, itineraryOrderVO.getItinerary_order_id());
			

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
	public ItineraryOrderVO findByPrimaryKey(Integer itinerary_order_id) {
		ItineraryOrderVO itineraryOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryOrderVO = new ItineraryOrderVO();
				itineraryOrderVO.setItinerary_order_id(rs.getInt("itinerary_order_id"));
				itineraryOrderVO.setMember_id(rs.getInt("member_id"));
				itineraryOrderVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryOrderVO.setItinerary_order_time(rs.getTimestamp("itinerary_order_time"));
				itineraryOrderVO.setItinerary_people_num(rs.getInt("itinerary_people_num"));
				itineraryOrderVO.setItinerary_ttl_price(rs.getInt("itinerary_ttl_price"));
				itineraryOrderVO.setItinerary_order_state(rs.getByte("itinerary_order_state"));
				itineraryOrderVO.setItinerary_refund_state(rs.getByte("itinerary_refund_state"));
				itineraryOrderVO.setItinerary_order_memo(rs.getString("itinerary_order_memo"));

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
		return itineraryOrderVO;
	}

	@Override
	public List<ItineraryOrderVO> getAll() {
		List<ItineraryOrderVO> list = new ArrayList<ItineraryOrderVO>();
		ItineraryOrderVO itineraryOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryOrderVO = new ItineraryOrderVO();
				itineraryOrderVO.setItinerary_order_id(rs.getInt("itinerary_order_id"));
				itineraryOrderVO.setMember_id(rs.getInt("member_id"));
				itineraryOrderVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryOrderVO.setItinerary_order_time(rs.getTimestamp("itinerary_order_time"));
				itineraryOrderVO.setItinerary_people_num(rs.getInt("itinerary_people_num"));
				itineraryOrderVO.setItinerary_ttl_price(rs.getInt("itinerary_ttl_price"));
				itineraryOrderVO.setItinerary_order_state(rs.getByte("itinerary_order_state"));
				itineraryOrderVO.setItinerary_refund_state(rs.getByte("itinerary_refund_state"));
				itineraryOrderVO.setItinerary_order_memo(rs.getString("itinerary_order_memo"));
				list.add(itineraryOrderVO);
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
	
	@Override
	public List<ItineraryOrderVO> findByMember_id(Integer member_id) {
		List<ItineraryOrderVO> list = new ArrayList<ItineraryOrderVO>();
		ItineraryOrderVO itineraryOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_MEMBER_STMT);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryOrderVO = new ItineraryOrderVO();
				itineraryOrderVO.setItinerary_order_id(rs.getInt("itinerary_order_id"));
				itineraryOrderVO.setMember_id(rs.getInt("member_id"));
				itineraryOrderVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryOrderVO.setItinerary_order_time(rs.getTimestamp("itinerary_order_time"));
				itineraryOrderVO.setItinerary_people_num(rs.getInt("itinerary_people_num"));
				itineraryOrderVO.setItinerary_ttl_price(rs.getInt("itinerary_ttl_price"));
				itineraryOrderVO.setItinerary_order_state(rs.getByte("itinerary_order_state"));
				itineraryOrderVO.setItinerary_refund_state(rs.getByte("itinerary_refund_state"));
				itineraryOrderVO.setItinerary_order_memo(rs.getString("itinerary_order_memo"));
				list.add(itineraryOrderVO);
				
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
	public List<ItineraryOrderVO> findByItinerary_id(Integer itinerary_id) {
		List<ItineraryOrderVO> list = new ArrayList<ItineraryOrderVO>();
		ItineraryOrderVO itineraryOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_MEMBER_STMT);
			pstmt.setInt(1, itinerary_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryOrderVO = new ItineraryOrderVO();
				itineraryOrderVO.setItinerary_order_id(rs.getInt("itinerary_order_id"));
				itineraryOrderVO.setMember_id(rs.getInt("member_id"));
				itineraryOrderVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryOrderVO.setItinerary_order_time(rs.getTimestamp("itinerary_order_time"));
				itineraryOrderVO.setItinerary_people_num(rs.getInt("itinerary_people_num"));
				itineraryOrderVO.setItinerary_ttl_price(rs.getInt("itinerary_ttl_price"));
				itineraryOrderVO.setItinerary_order_state(rs.getByte("itinerary_order_state"));
				itineraryOrderVO.setItinerary_refund_state(rs.getByte("itinerary_refund_state"));
				itineraryOrderVO.setItinerary_order_memo(rs.getString("itinerary_order_memo"));
				list.add(itineraryOrderVO);
				
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

//	@Override
//	public List<ItineraryOrderVO> getItinerary(Integer itinerary_id) {
//		List<ItineraryOrderVO> list = new ArrayList<ItineraryOrderVO>();
//		ItineraryOrderVO itineraryOrderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//
//			pstmt = con.prepareStatement(GET_ITINERARY);
//			pstmt.setInt(1, itinerary_id);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				itineraryOrderVO = new ItineraryOrderVO();
//				itineraryOrderVO.setItinerary_order_id(rs.getInt("itinerary_order_id"));
//				itineraryOrderVO.setMember_id(rs.getInt("member_id"));
//				itineraryOrderVO.setItinerary_id(rs.getInt("itinerary_id"));
//				itineraryOrderVO.setItinerary_order_time(rs.getTimestamp("itinerary_order_time"));
//				itineraryOrderVO.setItinerary_people_num(rs.getInt("itinerary_people_num"));
//				itineraryOrderVO.setItinerary_ttl_price(rs.getInt("itinerary_ttl_price"));
//				itineraryOrderVO.setItinerary_order_state(rs.getByte("itinerary_order_state"));
//				itineraryOrderVO.setItinerary_refund_state(rs.getByte("itinerary_refund_state"));
//				itineraryOrderVO.setItinerary_order_memo(rs.getString("itinerary_order_memo"));
//				list.add(itineraryOrderVO);
//				
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
	
	@Override
	public ItineraryOrderVO getLatestOrder(Integer member_id, Integer itinerary_id) {
		ItineraryOrderVO itineraryOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_LATEST_ONE_STMT);
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, itinerary_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryOrderVO = new ItineraryOrderVO();
				itineraryOrderVO.setItinerary_order_id(rs.getInt("itinerary_order_id"));
				itineraryOrderVO.setMember_id(rs.getInt("member_id"));
				itineraryOrderVO.setItinerary_id(rs.getInt("itinerary_id"));
				itineraryOrderVO.setItinerary_order_time(rs.getTimestamp("itinerary_order_time"));
				itineraryOrderVO.setItinerary_people_num(rs.getInt("itinerary_people_num"));
				itineraryOrderVO.setItinerary_ttl_price(rs.getInt("itinerary_ttl_price"));
				itineraryOrderVO.setItinerary_order_state(rs.getByte("itinerary_order_state"));
				itineraryOrderVO.setItinerary_refund_state(rs.getByte("itinerary_refund_state"));
				itineraryOrderVO.setItinerary_order_memo(rs.getString("itinerary_order_memo"));
				
				
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
		return itineraryOrderVO;
	}
	
	@Override
	public void updateItrOrder(ItineraryOrderVO itineraryOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_ITRORDER);
			
		
			pstmt.setInt(1, itineraryOrderVO.getItinerary_order_state());
			pstmt.setInt(2, itineraryOrderVO.getItinerary_refund_state());
			pstmt.setInt(3, itineraryOrderVO.getItinerary_order_id());
			
			
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


}