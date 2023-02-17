package com.roomcomment.model;

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

public class RoomCommentDAO implements RoomCommentDAO_interface {
	private static DataSource ds =null;
	
	static {
		try {
			Context ctx= new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/timetotravel");
		}catch(NamingException e) {
			
			e.printStackTrace();
		}
	}
	private static final String GET_ALL_STMT = "SELECT ROOM_COMMENT_ID,ROOM_TYPE_ID,MEMBER_ID,ROOM_COMMENT_CONTENT,ROOM_COMMENT_STAR,ROOM_COMMENT_TIME"
			+ " FROM room_comment order by ROOM_COMMENT_ID";
	private static final String INSERT_STMT = 
			"INSERT INTO room_comment (ROOM_TYPE_ID,MEMBER_ID,ROOM_COMMENT_CONTENT,ROOM_COMMENT_STAR) VALUES (?, ?, ?, ?)";
	private static final String GET_ONE_STMT = "SELECT room_comment_id,room_type_id,member_id,room_comment_content,room_comment_star,room_comment_time FROM room_comment where room_comment_id = ?";
	private static final String DELETE = "DELETE FROM room_comment where room_comment_id =?";

	private static final String GET_COMMENT_BYROOMTYPE = "SELECT MEMBER_ID,ROOM_COMMENT_CONTENT,ROOM_COMMENT_STAR,ROOM_COMMENT_TIME FROM room_comment where ROOM_TYPE_ID = ?";
	
	
	@Override
	public void insert(RoomCommentVO roomCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {

			con=ds.getConnection();			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomCommentVO.getRoom_type_id());
			pstmt.setInt(2, roomCommentVO.getMember_id());
			pstmt.setString(3, roomCommentVO.getRoom_comment_content());
			pstmt.setInt(4, roomCommentVO.getRoom_comment_star());

			pstmt.executeUpdate();

			// Handle any driver errors
		
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
	public void delete(Integer room_comment_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_comment_id);

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
	public RoomCommentVO findByPrimaryKey(Integer room_comment_id) {
		RoomCommentVO roomCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_comment_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// roomCommentVO 也稱為 Domain objects
				roomCommentVO = new RoomCommentVO();
				roomCommentVO.setRoom_comment_id(rs.getInt("room_comment_id"));
				roomCommentVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomCommentVO.setMember_id(rs.getInt("member_id"));
				roomCommentVO.setRoom_comment_content(rs.getString("room_comment_content"));
				roomCommentVO.setRoom_comment_star(rs.getInt("room_comment_star"));
				roomCommentVO.setRoom_comment_time(rs.getTimestamp("room_comment_time"));

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
		return roomCommentVO;
	}

	@Override
	public List<RoomCommentVO> getAll() {
		List<RoomCommentVO> list = new ArrayList<RoomCommentVO>();
		RoomCommentVO roomCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomCommentVO = new RoomCommentVO();
				roomCommentVO.setRoom_comment_id(rs.getInt("room_comment_id"));
				roomCommentVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomCommentVO.setMember_id(rs.getInt("member_id"));
				roomCommentVO.setRoom_comment_content(rs.getString("room_comment_content"));
				roomCommentVO.setRoom_comment_star(rs.getInt("room_comment_star"));
				roomCommentVO.setRoom_comment_time(rs.getTimestamp("room_comment_time"));

				list.add(roomCommentVO); // Store the row in the list

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
public List<RoomCommentVO> getCommemtByRoomType(Integer room_type_id ) {
	List<RoomCommentVO> list = new ArrayList<RoomCommentVO>();
	RoomCommentVO roomCommentVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		con=ds.getConnection();
		pstmt = con.prepareStatement(GET_COMMENT_BYROOMTYPE);
		pstmt.setInt(1, room_type_id);
		
		rs = pstmt.executeQuery();
		
		
		
		
		
		while (rs.next()) {
			roomCommentVO = new RoomCommentVO();
			
			roomCommentVO.setMember_id(rs.getInt("member_id"));
			roomCommentVO.setRoom_comment_content(rs.getString("room_comment_content"));
			roomCommentVO.setRoom_comment_star(rs.getInt("room_comment_star"));
			roomCommentVO.setRoom_comment_time(rs.getTimestamp("room_comment_time"));
			
			list.add(roomCommentVO); // Store the row in the list
			
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
}
