package com.roomimg.model;

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

public class RoomImgDAO implements RoomImg_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into room_img(room_type_id,room_img)values(?,?)";
	private static final String GET_ALL_STMT = "select * from room_img order by room_img_id ";
	private static final String GET_ONE_STMT = "select * from room_img where room_img_id=? ";
	private static final String GET_IMG_BYROOMTYPE = "select * from room_img where room_type_id=? ";
	private static final String GET_IMG_BYROOMTYPE_LIMIT1 = "select * from room_img where room_type_id=? limit 1";
	private static final String DELETE_STMT = "delete from room_img where room_img_id=? ";
	private static final String UPDATE = "update room_img set room_img =?  where room_img_id=? ;";

	public void insert(RoomImgVO roomImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, roomImgVO.getRoom_type_id());
			pstmt.setBytes(2, roomImgVO.getRoom_img());

			pstmt.executeUpdate();

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

	public void delete(Integer room_img_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, room_img_id);
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

	public void update(RoomImgVO roomImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setBytes(1, roomImgVO.getRoom_img());
			pstmt.setInt(2, roomImgVO.getRoom_img_id());

			pstmt.executeUpdate();

		}catch (SQLException se) {
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
	
	public RoomImgVO findPrimaryKey(Integer room_img_id) {
		RoomImgVO roomImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_img_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomImgVO = new RoomImgVO();
				roomImgVO.setRoom_img_id(rs.getInt("room_img_id"));
				roomImgVO.setRoom_type_id(rs.getInt("room_type_id"));
			}

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
		return roomImgVO;
	}
	
	public RoomImgVO getImgByRoomTypeLimit1(Integer room_type_id) {
		RoomImgVO roomImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(GET_IMG_BYROOMTYPE_LIMIT1);

			pstmt.setInt(1, room_type_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomImgVO = new RoomImgVO();
				roomImgVO.setRoom_img_id(rs.getInt("room_img_id"));
				roomImgVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomImgVO.setRoom_img(rs.getBytes("room_img"));
			}

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
		return roomImgVO;
	}

	public List<RoomImgVO> getImgByRoomType(Integer room_type_id){
		RoomImgVO roomImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomImgVO> list = new ArrayList<RoomImgVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IMG_BYROOMTYPE);
			pstmt.setInt(1, room_type_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomImgVO = new RoomImgVO();
				roomImgVO.setRoom_img_id(rs.getInt("room_img_id"));
				roomImgVO.setRoom_type_id(rs.getInt("room_type_id"));
				list.add(roomImgVO);
			}

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
	
	public List<RoomImgVO> getAll() {
		RoomImgVO roomImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomImgVO> list = new ArrayList<RoomImgVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomImgVO = new RoomImgVO();
				roomImgVO.setRoom_img_id(rs.getInt("room_img_id"));
				roomImgVO.setRoom_type_id(rs.getInt("room_type_id"));
				list.add(roomImgVO);
			}

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
