package com.reservation.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReservationDAO implements ReservationDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO reservation (room_type_id,reservation_date,room_type_amount,reservation_amount) VALUES (?, ?, ?, ?)";
	private static final String INSERT_EMPTYRESERVATION = "INSERT INTO reservation (room_type_id,reservation_date,room_type_amount) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * from reservation order by reservation_id";
	private static final String GET_ONE_STMT = "SELECT * from reservation where reservation_id = ?";
	 private static final String GET_BYROOMTYPEBYSTARTDATE = "select reservation_id,room_type_id,reservation_date,room_type_amount,sum(reservation_amount)as reservation_amount from reservation  where  room_type_id= ?  and reservation_date=? ";	private static final String DELETE_STMT = "delete from reservation where reservation_id = ?";
	private static final String DELETE_RESERVATION_DATE = "delete  from reservation where room_type_id= ? and reservation_date= ? ";
	private static final String GET_DATES = "select datediff(?,?) as dates;";

	
	public void insert(ReservationVO reservationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, reservationVO.getRoom_type_id());
			pstmt.setTimestamp(2, reservationVO.getReservation_date());
			pstmt.setInt(3, reservationVO.getRoom_type_amount());
			pstmt.setInt(4, reservationVO.getReservation_amount());

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
	//新增空預約
	public void insertEmptyReservation(ReservationVO reservationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_EMPTYRESERVATION);
			
			pstmt.setInt(1, reservationVO.getRoom_type_id());
			pstmt.setTimestamp(2, reservationVO.getReservation_date());
			pstmt.setInt(3, reservationVO.getRoom_type_amount());
			
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
	
	public ReservationVO getByRoomTypeByStartDate(Integer room_type_id,Timestamp start_date) {
		   ReservationVO reservationVO = null;
		   Connection con = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;

		   try {

		    con = ds.getConnection();
		    pstmt = con.prepareStatement(GET_BYROOMTYPEBYSTARTDATE);

		    pstmt.setInt(1, room_type_id);
		    pstmt.setTimestamp(2, start_date);

		    rs = pstmt.executeQuery();

		    while (rs.next()) {
		     reservationVO = new ReservationVO();
		     reservationVO.setReservation_id(rs.getInt("reservation_id"));
		     reservationVO.setRoom_type_id(rs.getInt("room_type_id"));
		     reservationVO.setReservation_date(rs.getTimestamp("reservation_date"));
		     reservationVO.setRoom_type_amount(rs.getInt("room_type_amount"));
		     reservationVO.setReservation_amount(rs.getInt("reservation_amount"));
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
		   return reservationVO;
		  }
	
	//訂單取消時刪除預約
	public void deleteReservationDate(Integer room_type_id ,Timestamp Start_date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_RESERVATION_DATE);

			pstmt.setInt(1, room_type_id);
			pstmt.setTimestamp(2,Start_date);
			
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
	}
	// 刪除
	public void delete(Integer reservation_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, reservation_id);
			
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
	}


	public ReservationVO findPrimaryKey(Integer reservationVO_id) {
		ReservationVO reservationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reservationVO_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setReservation_id(rs.getInt("reservation_id"));
				reservationVO.setRoom_type_id(rs.getInt("room_type_id"));
				reservationVO.setReservation_date(rs.getTimestamp("reservation_date"));
				reservationVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				reservationVO.setReservation_amount(rs.getInt("reservation_amount"));
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
		return reservationVO;
	}


	public List<ReservationVO> getAll() {
		List<ReservationVO> list = new ArrayList<ReservationVO>();
		ReservationVO reservationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setReservation_id(rs.getInt("reservation_id"));
				reservationVO.setRoom_type_id(rs.getInt("room_type_id"));
				reservationVO.setReservation_date(rs.getTimestamp("reservation_date"));
				reservationVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				reservationVO.setReservation_amount(rs.getInt("reservation_amount"));
				list.add(reservationVO);
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
	public ReservationVO getDates(Timestamp end_date,Timestamp start_date) {
		ReservationVO reservationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DATES);

			pstmt.setTimestamp(1, end_date);
			pstmt.setTimestamp(2, start_date);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationVO = new ReservationVO();
				reservationVO.setDates(rs.getInt("dates"));
				
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
		return reservationVO;
	}

}
