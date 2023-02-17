package com.tktorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TktOrderDAO implements TktOrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO TKT_ORDER(MEMBER_ID,PROM_ID,TOTAL,TOTAL_DISCOUNT) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM TKT_ORDER order by TKT_ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM TKT_ORDER where TKT_ORDER_ID = ?";
	private static final String GET_ONE_BYMEMBERID = "SELECT * FROM TKT_ORDER where MEMBER_ID = ?";
	private static final String GET__BYORDERSTATE = "SELECT * FROM TKT_ORDER where TKT_ORDER_STATE = ?";
//			private static final String DELETE = 
//				"DELETE FROM TKT_ORDER where TKT_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE TKT_ORDER set MEMBER_ID=?, PROM_ID=?, TOTAL=?, TKT_ORDER_STATE=?, TOTAL_DISCOUNT=? where TKT_ORDER_ID = ?";

	public Integer insert(TktOrder tktorder) {
		
		Integer tkt_order_id=null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, tktorder.getMember_id());
			pstmt.setInt(2, tktorder.getPromo_id());
			pstmt.setInt(3, tktorder.getTotal());
			pstmt.setInt(4, tktorder.getTotal_discount());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			tkt_order_id=rs.getInt(1);

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
		
		return tkt_order_id;

	}

	public void update(TktOrder tktorder) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tktorder.getMember_id());
			pstmt.setInt(2, tktorder.getPromo_id());
			pstmt.setInt(3, tktorder.getTotal());
			pstmt.setByte(4, tktorder.getTkt_order_state());
			pstmt.setInt(5, tktorder.getTotal_discount());
			pstmt.setInt(6, tktorder.getTkt_order_id());

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

	public TktOrder findByPrimaryKey(Integer tkt_order_id) {

		TktOrder tktorder = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tktorder �]�٬� Domain objects
				tktorder = new TktOrder();
				tktorder.setTkt_order_id(rs.getInt(1));
				tktorder.setMember_id(rs.getInt(2));
				tktorder.setOrder_date(rs.getTimestamp(3));
				tktorder.setPromo_id(rs.getInt(4));
				tktorder.setTotal(rs.getInt(5));
				tktorder.setTkt_order_state(rs.getByte(6));
				tktorder.setTotal_discount(rs.getInt(7));

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
		return tktorder;

	}

	public List<TktOrder> getAll() {
		List<TktOrder> list = new ArrayList<TktOrder>();
		TktOrder tktorder = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tktorder �]�٬� Domain objects
				tktorder = new TktOrder();
				tktorder.setTkt_order_id(rs.getInt(1));
				tktorder.setMember_id(rs.getInt(2));
				tktorder.setOrder_date(rs.getTimestamp(3));
				tktorder.setPromo_id(rs.getInt(4));
				tktorder.setTotal(rs.getInt(5));
				tktorder.setTkt_order_state(rs.getByte(6));
				tktorder.setTotal_discount(rs.getInt(7));
				list.add(tktorder);
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

	@Override
	public List<TktOrder> findByMemberId(Integer member_id) {
		List<TktOrder> list = new ArrayList<TktOrder>();
		TktOrder tktorder = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYMEMBERID);
			
			pstmt.setInt(1,member_id);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// tktorder �]�٬� Domain objects
				tktorder = new TktOrder();
				tktorder.setTkt_order_id(rs.getInt(1));
				tktorder.setMember_id(rs.getInt(2));
				tktorder.setOrder_date(rs.getTimestamp(3));
				tktorder.setPromo_id(rs.getInt(4));
				tktorder.setTotal(rs.getInt(5));
				tktorder.setTkt_order_state(rs.getByte(6));
				tktorder.setTotal_discount(rs.getInt(7));
				list.add(tktorder);
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
	
	@Override
	public List<TktOrder> selectByTktOrderState(Byte tkt_order_state) {
		List<TktOrder> list = new ArrayList<TktOrder>();
		TktOrder tktorder = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET__BYORDERSTATE);
			
			pstmt.setInt(1,tkt_order_state);
            rs = pstmt.executeQuery();			

			while (rs.next()) {
				// tktorder �]�٬� Domain objects
				tktorder = new TktOrder();
				tktorder.setTkt_order_id(rs.getInt(1));
				tktorder.setMember_id(rs.getInt(2));
				tktorder.setOrder_date(rs.getTimestamp(3));
				tktorder.setPromo_id(rs.getInt(4));
				tktorder.setTotal(rs.getInt(5));
				tktorder.setTkt_order_state(rs.getByte(6));
				tktorder.setTotal_discount(rs.getInt(7));
				list.add(tktorder);
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

}
