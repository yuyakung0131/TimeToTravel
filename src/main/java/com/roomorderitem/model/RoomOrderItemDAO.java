package com.roomorderitem.model;

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

import com.roomorder.model.RoomOrderVO;

public class RoomOrderItemDAO implements RoomOrderItemDAO_interface {
	private static DataSource ds =null;
	
	static {
		try {
			Context ctx= new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/timetotravel");
		}catch(NamingException e) {
			
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = "insert into room_order_item(room_order_id,room_type_id,room_amount,room_type_price,room_sale_price,special_req,checkin_amount,room_order_checkin_date,room_order_checkout_date,room_guest_name)\r\n"
			+ "values(?,?,?,?,?,?,?,?,?,?);";
	private static final String GET_ALL_STMT = "SELECT room_order_id,room_type_id,room_order_checkin_date,room_order_checkout_date FROM room_order_item order by room_order_id";
	private static final String GET_ONE_STMT = "SELECT room_order_id,room_type_id,ROOM_AMOUNT,ROOM_TYPE_PRICE,ROOM_SALE_PRICE,SPECIAL_REQ,CHECKIN_AMOUNT,room_order_checkin_date,room_order_checkout_date,ROOM_GUEST_NAME FROM room_order_item where room_order_id = ?";
	private static final String GET_ALL_LONG= "SELECT * FROM room_order_item ";
	private static final String GET_ONE_FOR_ORDERID= "SELECT * FROM room_order_item where ROOM_ORDER_ID=?";

	
	//找訂單的所有訂單明細
	public List<RoomOrderItemVO> getOneForOrderId(Integer room_order_id) {
		List<RoomOrderItemVO> list = new ArrayList<RoomOrderItemVO>();
		RoomOrderItemVO roomOrderItemVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FOR_ORDERID);
			
			pstmt.setInt(1,room_order_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomOrderItemVO = new RoomOrderItemVO();
				roomOrderItemVO.setRoom_order_id(rs.getInt(1));
				roomOrderItemVO.setRoom_type_id(rs.getInt(2));
				roomOrderItemVO.setRoom_amount(rs.getInt(3));
				roomOrderItemVO.setRoom_type_price(rs.getInt(4));
				roomOrderItemVO.setRoom_sale_price(rs.getInt(5));
				roomOrderItemVO.setSpecial_req(rs.getString(6));
				roomOrderItemVO.setCheckin_amount(rs.getInt(7));
				roomOrderItemVO.setRoom_order_checkin_date(rs.getDate(8));
				roomOrderItemVO.setRoom_order_checkout_date(rs.getDate(9));
				roomOrderItemVO.setRoom_guest_name(rs.getString(10));
				

				list.add(roomOrderItemVO); // Store the row in the list
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
	
	
	
	public void insert(RoomOrderItemVO roomOrderItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomOrderItemVO.getRoom_order_id());
			pstmt.setInt(2, roomOrderItemVO.getRoom_type_id());
			pstmt.setInt(3, roomOrderItemVO.getRoom_amount());
			pstmt.setInt(4, roomOrderItemVO.getRoom_type_price());
			pstmt.setInt(5, roomOrderItemVO.getRoom_sale_price());
			pstmt.setString(6, roomOrderItemVO.getSpecial_req());
			pstmt.setInt(7, roomOrderItemVO.getCheckin_amount());
			pstmt.setDate(8, roomOrderItemVO.getRoom_order_checkin_date());
			pstmt.setDate(9, roomOrderItemVO.getRoom_order_checkout_date());
			pstmt.setString(10, roomOrderItemVO.getRoom_guest_name());

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

	public RoomOrderItemVO findByPrimaryKey(Integer room_order_id) {

		RoomOrderItemVO roomOrderItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_order_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderItemVO = new RoomOrderItemVO();
				roomOrderItemVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomOrderItemVO.setRoom_amount(rs.getInt("room_amount"));
				roomOrderItemVO.setRoom_type_price(rs.getInt("room_type_price"));
				roomOrderItemVO.setRoom_sale_price(rs.getInt("room_sale_price"));
				roomOrderItemVO.setSpecial_req(rs.getString("special_req"));
				roomOrderItemVO.setCheckin_amount(rs.getInt("checkin_amount"));
				roomOrderItemVO.setRoom_order_checkin_date(rs.getDate("room_order_checkin_date"));
				roomOrderItemVO.setRoom_order_checkout_date(rs.getDate("room_order_checkout_date"));
				roomOrderItemVO.setRoom_guest_name(rs.getString("room_guest_name"));

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
		return roomOrderItemVO;
	}

	public List<RoomOrderItemVO> getAll() {
		List<RoomOrderItemVO> list = new ArrayList<RoomOrderItemVO>();
		RoomOrderItemVO roomOrderItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderItemVO = new RoomOrderItemVO();
				roomOrderItemVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomOrderItemVO.setRoom_order_checkin_date(rs.getDate("room_order_checkin_date"));
				roomOrderItemVO.setRoom_order_checkout_date(rs.getDate("room_order_checkout_date"));
				list.add(roomOrderItemVO);

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
	
	//全部!!
	public List<RoomOrderItemVO> getAllLong() {
		List<RoomOrderItemVO> list = new ArrayList<RoomOrderItemVO>();
		RoomOrderItemVO roomOrderItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_LONG);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrderItemVO = new RoomOrderItemVO();
				roomOrderItemVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomOrderItemVO.setRoom_amount(rs.getInt("room_amount"));
				roomOrderItemVO.setRoom_type_price(rs.getInt("room_type_price"));
				roomOrderItemVO.setRoom_sale_price(rs.getInt("room_sale_price"));
				roomOrderItemVO.setSpecial_req(rs.getString("special_req"));
				roomOrderItemVO.setCheckin_amount(rs.getInt("checkin_amount"));
				roomOrderItemVO.setRoom_order_checkin_date(rs.getDate("room_order_checkin_date"));
				roomOrderItemVO.setRoom_order_checkout_date(rs.getDate("room_order_checkout_date"));
				roomOrderItemVO.setRoom_guest_name(rs.getString("room_guest_name"));
				
				list.add(roomOrderItemVO);
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

//	public static void main(String[] args) {
//		RoomOrderItemJDBCDAO dao = new RoomOrderItemJDBCDAO();
		// �s�W
//		RoomOrderItemVO roomOrderItemVO=new RoomOrderItemVO();
//		roomOrderItemVO.setRoom_order_id(2);
//		roomOrderItemVO.setRoom_type_id(5);
//		roomOrderItemVO.setRoom_amount(1);
//		roomOrderItemVO.setRoom_type_price(10000);
//		roomOrderItemVO.setRoom_sale_price(0);
//		roomOrderItemVO.setSpecial_req("�ڭ̦��@�өЫȻݭn������������");
//		roomOrderItemVO.setCheckin_amount(8);
//		roomOrderItemVO.setRoom_order_checkin_date(Timestamp.valueOf("2022-12-15 15:00:00"));
//		roomOrderItemVO.setRoom_order_checkout_date(Timestamp.valueOf("2022-12-20 11:00:00"));
//		roomOrderItemVO.setRoom_guest_name("�ܦh�H�٨S���b�F");
//		dao.insert(roomOrderItemVO);

		// �d��
//		RoomOrderItemVO roomOrderItemVO2 = dao.findByPrimaryKey(1,5);
//		System.out.print(roomOrderItemVO2.getRoom_order_id() + ",");
//		System.out.print(roomOrderItemVO2.getRoom_type_id() + ",");
//		System.out.print(roomOrderItemVO2.getRoom_order_checkin_date().toLocalDateTime() + ",");
//		System.out.println(roomOrderItemVO2.getRoom_order_checkout_date().toLocalDateTime());
//		System.out.println("===============================================");

		// �d��
//		List<RoomOrderItemVO> list = dao.getAll();
//		for (RoomOrderItemVO aRoomOI : list) {
//			System.out.print(aRoomOI.getRoom_order_id() + ",");
//			System.out.print(aRoomOI.getRoom_type_id() + ",");
//			System.out.print(aRoomOI.getRoom_order_checkin_date().toLocalDateTime() + ",");
//			System.out.println(aRoomOI.getRoom_order_checkout_date().toLocalDateTime());
//		}
//	}
}
