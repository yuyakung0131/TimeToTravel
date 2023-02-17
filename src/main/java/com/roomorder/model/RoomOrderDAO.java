package com.roomorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class RoomOrderDAO implements RoomOrderDAO_interface {
private static DataSource ds =null;
	
static {
	try {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
	} catch (NamingException e) {
		e.printStackTrace();
	}
}

	private static final String INSERT_STMT = "INSERT INTO room_order (member_id,room_order_ttl_price) VALUES (?,?) ";
	private static final String GET_ALL_STMT = "SELECT room_order_id,member_id,room_order_date,room_order_state,room_order_ttl_price FROM room_order order by room_order_id";
	private static final String GET_ONE_STMT = "SELECT room_order_id,member_id,room_order_date,room_order_state,room_order_ttl_price FROM room_order where room_order_id = ?";
	private static final String GET_ORDER_BY_MEMBER = "SELECT * FROM room_order where member_id = ?";
	private static final String UPDATSTATE = "UPDATE room_order set ROOM_ORDER_STATE=1 where ROOM_ORDER_ID = ?";
	private static final String GET_STATE = "SELECT * FROM room_order where ROOM_ORDER_STATE=?";
	private static final String GET_VERY_NEW = "SELECT ROOM_ORDER_ID FROM room_order order by ROOM_ORDER_ID DESC LIMIT 1";
	
//只取最新一筆
	public List<RoomOrderVO> getVeryNew() {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_VERY_NEW);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getInt("room_order_id"));
				list.add(roomOrderVO);

			}
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
		return list;
	}
	
	
	
	
	
	
	//上下架選單用
			@Override
			public List<RoomOrderVO> getRoomOrderState(Byte room_order_state) {
				List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
				RoomOrderVO roomOrderVO = null;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				
				try {
					con=ds.getConnection();
					pstmt = con.prepareStatement(GET_STATE);
					
					pstmt.setInt(1,room_order_state);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						roomOrderVO = new RoomOrderVO();
						roomOrderVO.setRoom_order_id(rs.getInt(1));
						roomOrderVO.setMember_id(rs.getInt(2));
						roomOrderVO.setRoom_order_date(rs.getTimestamp(3));
						roomOrderVO.setRoom_order_state(rs.getByte(4));
						roomOrderVO.setRoom_order_ttl_price(rs.getInt(5));

						list.add(roomOrderVO); // Store the row in the list
					}
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
				return list;
			}
			
	
	
	public RoomOrderVO updateState(Integer room_order_id) {
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATSTATE);

			pstmt.setInt(1, room_order_id);
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
		return roomOrderVO;
	}
	public void insert(RoomOrderVO roomOrderVO) {
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			System.out.println(roomOrderVO);
			System.out.println("會員"+roomOrderVO.getMember_id());
			
			pstmt.setInt(1, roomOrderVO.getMember_id());
			pstmt.setInt(2,  roomOrderVO.getRoom_order_ttl_price());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); 

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

	
	
	public RoomOrderVO findByPrimaryKey(Integer room_order_id) {
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_order_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderVO.setMember_id(rs.getInt("member_id"));
				roomOrderVO.setRoom_order_date(rs.getTimestamp("room_order_date"));
				roomOrderVO.setRoom_order_state(rs.getByte("room_order_state"));
				roomOrderVO.setRoom_order_ttl_price(rs.getInt("room_order_ttl_price"));

			}
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
		return roomOrderVO;
	}
	public List<RoomOrderVO> getOrderByMember(Integer member_id){
		
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_BY_MEMBER);
			pstmt.setInt(1, member_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderVO.setMember_id(rs.getInt("member_id"));
				roomOrderVO.setRoom_order_date(rs.getTimestamp("room_order_date"));
				roomOrderVO.setRoom_order_state(rs.getByte("room_order_state"));
				roomOrderVO.setRoom_order_ttl_price(rs.getInt("room_order_ttl_price"));
				list.add(roomOrderVO);

			}
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
		return list;
		
	}

	public List<RoomOrderVO> getAll() {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderVO.setMember_id(rs.getInt("member_id"));
				roomOrderVO.setRoom_order_date(rs.getTimestamp("room_order_date"));
				roomOrderVO.setRoom_order_state(rs.getByte("room_order_state"));
				roomOrderVO.setRoom_order_ttl_price(rs.getInt("room_order_ttl_price"));
				list.add(roomOrderVO);

			}
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
		return list;
	}
	
	

	public static void main(String[] args) {
		RoomOrderDAO dao=new RoomOrderDAO();
		//�s�W
		RoomOrderVO roomOrderVO=new RoomOrderVO();
		roomOrderVO.setMember_id(3);
		roomOrderVO.setRoom_order_ttl_price(2000);
		dao.insert(roomOrderVO);
		
		//�d��
//		RoomOrderVO roomOrderVO2=dao.findByPrimaryKey(1);
//		System.out.print(roomOrderVO2.getRoom_order_id()+",");
//		System.out.print(roomOrderVO2.getMember_id()+",");
//		System.out.print(roomOrderVO2.getRoom_order_date()+",");
//		System.out.print(roomOrderVO2.getRoom_order_state()+",");
//		System.out.println(roomOrderVO2.getRoom_order_ttl_price());
//		System.out.println("====================================================");
		
		//�d��
//		List<RoomOrderVO> list=dao.getAll();
//		for(RoomOrderVO aRoomOrder:list) {
//			System.out.print(aRoomOrder.getRoom_order_id()+",");
//			System.out.print(aRoomOrder.getMember_id()+",");
//			System.out.print(aRoomOrder.getRoom_order_date()+",");
//			System.out.print(aRoomOrder.getRoom_order_state()+",");
//			System.out.println(aRoomOrder.getRoom_order_ttl_price());
//		}
		
	}
}
