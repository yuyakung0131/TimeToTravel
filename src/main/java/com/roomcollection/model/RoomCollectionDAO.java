package com.roomcollection.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomCollectionDAO implements RoomCollection_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO room_collection (member_id,room_type_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT member_id,room_type_id,fav_date FROM room_collection order by member_id";
	private static final String GET_ONE_ROOMCOLLECTION = "SELECT member_id,room_type_id,fav_date FROM room_collection where member_id = ? and room_type_id=? ";
	private static final String GET_BYMEMBER = "SELECT member_id,room_type_id,fav_date FROM room_collection where member_id = ?";
	private static final String DELETE_STMT = "delete from room_collection where  member_id=? and ROOM_TYPE_ID=? ";

	public void insert(RoomCollectionVO roomCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, roomCollectionVO.getMember_id());
			pstmt.setInt(2, roomCollectionVO.getRoom_type_id());
			
			pstmt.executeUpdate();
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
	}

	
	public void delete(Integer member_id, Integer room_type_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, member_id);
			pstmt.setInt(2,room_type_id);

			pstmt.executeUpdate();
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
	}

	public RoomCollectionVO getOneRoomCollection(Integer member_id,Integer room_type_id) {
		RoomCollectionVO roomCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ROOMCOLLECTION);
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, room_type_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomCollectionVO = new RoomCollectionVO();
				roomCollectionVO.setMember_id(rs.getInt("member_id"));
				roomCollectionVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomCollectionVO.setFav_date(rs.getTimestamp("fav_date"));
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
		return roomCollectionVO;
	}
	public List<RoomCollectionVO> getCollectionByMember(Integer member_id) {
		List<RoomCollectionVO> list= new ArrayList<RoomCollectionVO>();
		RoomCollectionVO roomCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(GET_BYMEMBER);
			pstmt.setInt(1, member_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomCollectionVO = new RoomCollectionVO();
				roomCollectionVO.setMember_id(rs.getInt("member_id"));
				roomCollectionVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomCollectionVO.setFav_date(rs.getTimestamp("fav_date"));
				list.add(roomCollectionVO);
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
		return list;
	}

	public List<RoomCollectionVO> getAll() {
		List<RoomCollectionVO> list = new ArrayList<RoomCollectionVO>();
		RoomCollectionVO roomCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomCollectionVO = new RoomCollectionVO();
				roomCollectionVO.setMember_id(rs.getInt("member_id"));
				roomCollectionVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomCollectionVO.setFav_date(rs.getTimestamp("fav_date"));
				list.add(roomCollectionVO);
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
		return list;
	}

}
