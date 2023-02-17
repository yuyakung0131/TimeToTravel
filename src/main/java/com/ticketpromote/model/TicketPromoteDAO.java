package com.ticketpromote.model;

import java.math.BigDecimal;
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



public class TicketPromoteDAO implements TicketPromoteDAO_interface {
	
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into TICKET_PROMOTE(PROM_NAME,PROM_STATE,DISCOUNT_AMOUNT,PROM_ACHIEVE_NUMBER) values(?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from TICKET_PROMOTE order by PROM_ID";
	private static final String GET_ONE_STMT = "select * from TICKET_PROMOTE where PROM_ID = ?";
	private static final String DELETE = "delete from TICKET_PROMOTE where PROM_ID = ?";
	private static final String UPDATE = 
			"update TICKET_PROMOTE set PROM_NAME=?, PROM_STATE=?, DISCOUNT_AMOUNT=?, PROM_ACHIEVE_NUMBER=? where PROM_ID = ?";
	private static final String GET_ALL_STATE_STMT="select * from TICKET_PROMOTE where PROM_STATE=?";
	private static final String GET_ALL_NUMBER_STMT="select * from TICKET_PROMOTE where PROM_ACHIEVE_NUMBER<?";
	
	@Override
	public void insert(TicketPromote ticketpromote) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ticketpromote.getProm_name());
			pstmt.setByte(2, ticketpromote.getProm_state());
			pstmt.setDouble(3, ticketpromote.getDiscount_amount());
			pstmt.setInt(4, ticketpromote.getProm_achieve_number());
	
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
	public void update(TicketPromote ticketpromote) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ticketpromote.getProm_name());
			pstmt.setByte(2, ticketpromote.getProm_state());
			pstmt.setDouble(3, ticketpromote.getDiscount_amount());
			pstmt.setInt(4, ticketpromote.getProm_id());
			pstmt.setInt(5, ticketpromote.getProm_achieve_number());


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
	public void delete(Integer prom_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prom_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public TicketPromote findByPrimaryKey(Integer prom_id) {

		TicketPromote ticketpromote = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prom_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ticketpromote = new TicketPromote();
				ticketpromote.setProm_id(rs.getInt(1));
				ticketpromote.setProm_name(rs.getString(2));
				ticketpromote.setProm_state(rs.getByte(3));
				ticketpromote.setDiscount_amount(rs.getDouble(4));
				ticketpromote.setProm_achieve_number(rs.getInt(5));

		
			}

			// Handle any driver errors
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
		return ticketpromote;
	}
	
	@Override
	public List<TicketPromote>getAll() {
		
		List<TicketPromote>list= new ArrayList<TicketPromote>();
		TicketPromote ticketpromote = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				ticketpromote = new TicketPromote();
				ticketpromote.setProm_id(rs.getInt(1));
				ticketpromote.setProm_name(rs.getString(2));
				ticketpromote.setProm_state(rs.getByte(3));
				ticketpromote.setDiscount_amount(rs.getDouble(4));
				ticketpromote.setProm_achieve_number(rs.getInt(5));
				list.add(ticketpromote);
		
			}

			// Handle any driver errors
		}catch (SQLException se) {
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

	@Override
	public List<TicketPromote> findByPromState(Byte prom_state) {
		List<TicketPromote>list= new ArrayList<TicketPromote>();
		TicketPromote ticketpromote = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STATE_STMT);
			pstmt.setByte(1,prom_state);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				ticketpromote = new TicketPromote();
				ticketpromote.setProm_id(rs.getInt(1));
				ticketpromote.setProm_name(rs.getString(2));
				ticketpromote.setProm_state(rs.getByte(3));
				ticketpromote.setDiscount_amount(rs.getDouble(4));
				ticketpromote.setProm_achieve_number(rs.getInt(5));
				list.add(ticketpromote);
		
			}

			// Handle any driver errors
		}catch (SQLException se) {
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

	@Override
	public List<TicketPromote> findByNumber(Integer prom_achieve_number) {
		List<TicketPromote>list= new ArrayList<TicketPromote>();
		TicketPromote ticketpromote = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_NUMBER_STMT);
			pstmt.setInt(1, prom_achieve_number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				ticketpromote = new TicketPromote();
				ticketpromote.setProm_id(rs.getInt(1));
				ticketpromote.setProm_name(rs.getString(2));
				ticketpromote.setProm_state(rs.getByte(3));
				ticketpromote.setDiscount_amount(rs.getDouble(4));
				ticketpromote.setProm_achieve_number(rs.getInt(5));
				list.add(ticketpromote);
		
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
