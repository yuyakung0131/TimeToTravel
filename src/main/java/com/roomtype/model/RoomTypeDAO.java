package com.roomtype.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class RoomTypeDAO implements RoomTypeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO room_type (firm_id,room_type_name,room_type_amount,room_type_content,room_type_price) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM room_type order by room_type_id";
	private static final String GET_ONE_STMT = "SELECT * FROM room_type where room_type_id = ?";
	private static final String GET_ROOMTYPEBYFIRMID = "SELECT * FROM room_type where firm_id = ? and ROOM_TYPE_STATE=0 ";
	private static final String DELETE = "DELETE FROM room_type where room_type_id = ?";
	private static final String UPDATE = "UPDATE room_type set  room_type_state=? where room_type_id = ?";
	

	
	public void insert(RoomTypeVO roomTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomTypeVO.getFirm_id());
			pstmt.setString(2, roomTypeVO.getRoom_type_name());
			pstmt.setInt(3, roomTypeVO.getRoom_type_amount());
			pstmt.setString(4, roomTypeVO.getRoom_type_content());
			pstmt.setInt(5, roomTypeVO.getRoom_type_price());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update(RoomTypeVO roomTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setByte(1, roomTypeVO.getRoom_type_state());
			pstmt.setInt(2, roomTypeVO.getRoom_type_id());

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

	public void delete(Integer room_type_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_type_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	};

	public RoomTypeVO findByPrimaryKey(Integer room_type_id) {
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_type_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomTypeVO.setFirm_id(rs.getInt("firm_id"));
				roomTypeVO.setRoom_type_name(rs.getString("room_type_name"));
				roomTypeVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoom_type_content(rs.getString("room_type_content"));
				roomTypeVO.setRoom_type_state(rs.getByte("room_type_state"));
				roomTypeVO.setRoom_type_price(rs.getInt("room_type_price"));
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
		return roomTypeVO;
	};

	public List<RoomTypeVO> getRoomTypeByFirmID(Integer firm_id){
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ROOMTYPEBYFIRMID);
			pstmt.setInt(1, firm_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomTypeVO.setFirm_id(rs.getInt("firm_id"));
				roomTypeVO.setRoom_type_name(rs.getString("room_type_name"));
				roomTypeVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoom_type_content(rs.getString("room_type_content"));
				roomTypeVO.setRoom_type_state(rs.getByte("room_type_state"));
				roomTypeVO.setRoom_type_price(rs.getInt("room_type_price"));
				list.add(roomTypeVO); // Store the row in the list
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
	
	
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomTypeVO.setFirm_id(rs.getInt("firm_id"));
				roomTypeVO.setRoom_type_name(rs.getString("room_type_name"));
				roomTypeVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoom_type_content(rs.getString("room_type_content"));
				roomTypeVO.setRoom_type_state(rs.getByte("room_type_state"));
				roomTypeVO.setRoom_type_price(rs.getInt("room_type_price"));
				list.add(roomTypeVO); // Store the row in the list
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

	public static void main(String[] args) {
		RoomTypeDAO dao = new RoomTypeDAO();
		// �s�W
//		RoomTypeVO roomTypeVO1 = new RoomTypeVO();
//		roomTypeVO1.setFirm_id(2);
//		roomTypeVO1.setRoom_type_name("�W�ų�H��");
//		roomTypeVO1.setRoom_type_amount(4);
//		roomTypeVO1.setRoom_type_content("�@�ӤH�W�ũt�W");
//		roomTypeVO1.setRoom_type_state(new Byte("1"));
//		roomTypeVO1.setRoom_type_price(300);
//		dao.insert(roomTypeVO1);

		// �ק�
//		RoomTypeVO roomTypeVO2 = new RoomTypeVO();
//		roomTypeVO2.setRoom_type_id(6);
//		roomTypeVO2.setFirm_id(2);
//		roomTypeVO2.setRoom_type_name("�h�a�|�H��");
//		roomTypeVO2.setRoom_type_amount(10);
//		roomTypeVO2.setRoom_type_content("�S�����N���֨ӭq");
//		roomTypeVO2.setRoom_type_state(new Byte("1"));
//		roomTypeVO2.setRoom_type_price(2000);
//		dao.update(roomTypeVO2);

		// �R��
//		dao.delete(1);

		// �d��
		RoomTypeVO roomTypeVO3 = dao.findByPrimaryKey(5);
		System.out.print(roomTypeVO3.getRoom_type_id() + ",");
		System.out.print(roomTypeVO3.getFirm_id() + ",");
		System.out.print(roomTypeVO3.getRoom_type_name() + ",");
		System.out.print(roomTypeVO3.getRoom_type_amount() + ",");
		System.out.print(roomTypeVO3.getRoom_type_content() + ",");
		System.out.print(roomTypeVO3.getRoom_type_state() + ",");
		System.out.println(roomTypeVO3.getRoom_type_price());
		System.out.println("==================================================");

		// �d��
		List<RoomTypeVO> list = dao.getAll();
		for (RoomTypeVO aRoomType : list) {
			System.out.print(aRoomType.getRoom_type_id() + ",");
			System.out.print(aRoomType.getFirm_id() + ",");
			System.out.print(aRoomType.getRoom_type_name() + ",");
			System.out.print(aRoomType.getRoom_type_amount() + ",");
			System.out.print(aRoomType.getRoom_type_content() + ",");
			System.out.print(aRoomType.getRoom_type_state() + ",");
			System.out.print(aRoomType.getRoom_type_price());
			System.out.println();
		}

	}

}
