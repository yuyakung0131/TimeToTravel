package com.roomsearch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomSearchDAO implements RoomSearch_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_EMPTYROOM_BYFIRMNAME = "select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0 and  firm_name like \"%\"?\"%\" and reservation_date between  ?  and  ?  group by firm_id order by reservation_date;";
	private static final String GET_EMPTYROOM_BYFIRADD_BYSTARTDATE = "select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0 and  firm_operate_add like \"%\"?\"%\" and reservation_date between  ?  and ?  group by firm_id order by reservation_date;";
	private static final String GET_BYFIRMADD_BYSTARTDATE = "select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0 and  firm_operate_add like \"%\"?\"%\" and reservation_date=?  group by firm_name order by f.firm_id;";
	private static final String GET_BYFIRMADD_NODATE = " select * from firm where firmtype_id=2 and  firm_operate_add like \"%\"?\"%\" ";
	private static final String GET_BYFIRMNAME_NODATE = "select * from firm where firmtype_id=2 and  firm_name like \"%\"?\"%\" ";
	private static final String GET_BYSTARTDATE ="select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0   and reservation_date= ?  group by firm_name order by reservation_date; ";
	private static final String GET_EMPTYROOM_BYONEFIRM = "select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0 and f.frim_id= ?  firm_name like \"%\"?\"%\" and reservation_date between  ?  and  ?  group by room_tpye_name order by reservation_date;";
	private static final String GET_EMPTYROOM_BYONEFIRM_BYSTARTDATE = "select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0 and f.firm_id=?  and  reservation_date =?  group by room_type_name order by room_type_id;";
	private static final String GET_EMPTYROOM_BYONEROOM_BYROOMTYPE = "select rt.firm_id,f.firm_name,firm_operate_add,r.room_type_id,room_type_name,reservation_date,r.room_type_amount,reservation_amount,r.room_type_amount-sum(reservation_amount) as empty_room from room_type rt  join firm f on rt.firm_id=f.firm_id  join reservation r on rt.room_type_id=r.room_type_id where FIRMTYPE_ID=2 and room_type_state=0 and firm_state=0 and r.room_type_id=?  and  reservation_date =?  group by room_type_name order by room_type_id;";

	public List<RoomSearchVO> getEmptyRoomByFirmName(String firm_name, String start_date, String end_date) {
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPTYROOM_BYFIRMNAME);
			pstmt.setString(1, firm_name);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
				
			}
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
	
	
	public List<RoomSearchVO> getEmptyRoomByFirmAdd(String firm_operate_add, String start_date, String end_date) {
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPTYROOM_BYFIRADD_BYSTARTDATE);
			pstmt.setString(1, firm_operate_add);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
				
			}
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
	
	public List<RoomSearchVO> getEmptyRoomByOneFirm(Integer firm_id , String start_date, String end_date) {
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPTYROOM_BYONEFIRM);
			pstmt.setInt(1, firm_id);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
				
			}
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
	public List<RoomSearchVO> getEmptyRoomByOneFirmByStartDate(Integer firm_id , String start_date){
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPTYROOM_BYONEFIRM_BYSTARTDATE);
			pstmt.setInt(1, firm_id);
			pstmt.setString(2, start_date);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
				
			}
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
	public RoomSearchVO getEmptyRoomByOneRoomByRoomType(Integer room_type_id , String start_date){
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPTYROOM_BYONEROOM_BYROOMTYPE);
			pstmt.setInt(1, room_type_id);
			pstmt.setString(2, start_date);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				
			}
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
		return roomSearchVO;
	}
	public List<RoomSearchVO> getFirmByAddByStartDate(String firm_operate_add , String start_date){
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYFIRMADD_BYSTARTDATE);
			pstmt.setString(1, firm_operate_add);
			pstmt.setString(2, start_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
			}
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
	public List<RoomSearchVO> getFirmByAddNoDate(String firm_operate_add){
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYFIRMADD_NODATE);
			pstmt.setString(1, firm_operate_add);
		
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
			}
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
	public List<RoomSearchVO> getFirmByNameNoDate(String firm_name){
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYFIRMNAME_NODATE);
			pstmt.setString(1, firm_name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
			}
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
	public List<RoomSearchVO> getByStartDate(String start_date){
		List<RoomSearchVO> list=new ArrayList<RoomSearchVO>();
		RoomSearchVO roomSearchVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYSTARTDATE);
			pstmt.setString(1, start_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomSearchVO=new RoomSearchVO();
				roomSearchVO.setFirm_id(rs.getInt("firm_id"));
				roomSearchVO.setFirm_name(rs.getString("firm_name"));
				roomSearchVO.setFirm_operate_add(rs.getString("firm_operate_add"));
				roomSearchVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomSearchVO.setRoom_type_name(rs.getString("room_type_name"));
				roomSearchVO.setReservation_date(rs.getTimestamp("reservation_date"));
				roomSearchVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomSearchVO.setReservation_amount(rs.getInt("reservation_amount"));
				roomSearchVO.setEmpty_room(rs.getInt("empty_room"));
				list.add(roomSearchVO);
			}
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
}
