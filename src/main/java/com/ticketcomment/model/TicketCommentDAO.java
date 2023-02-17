package com.ticketcomment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class TicketCommentDAO implements TicketCommentDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into TICKET_COMMENT(TKT_ID,MEMBER_ID,TICKET_COMMENT_CONTENT,TKT_TOTAL_SCORE) values(?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from TICKET_COMMENT order by TICKET_COMMENT_ID";
	private static final String GET_ONE_STMT = "select * from TICKET_COMMENT where TICKET_COMMENT_ID = ?";
	private static final String DELETE = "delete from TICKET_COMMENT where TICKET_COMMENT_ID = ?";
	private static final String UPDATE = 
			"UPDATE TICKET_COMMENT set TKT_ID=?, MEMBER_ID=?, TICKET_COMMENT_CONTENT=?, TKT_TOTAL_SCORE=? where TICKET_COMMENT_ID = ?";
	private static final String Get_ALL_COMMENTSCORE="select avg(TKT_TOTAL_SCORE) from TICKET_COMMENT where TKT_ID=?";
	private static final String Get_ALL_COMMENTCOUNT="select count(TICKET_COMMENT_ID) from TICKET_COMMENT where TKT_ID=?;";
	
	@Override
	public void insert(TicketComment ticketcomment) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ticketcomment.getTkt_id());
			pstmt.setInt(2, ticketcomment.getMember_id());
			pstmt.setString(3, ticketcomment.getTicket_comment_content());
			pstmt.setDouble(4, ticketcomment.getTkt_total_score());

			
	

			pstmt.executeUpdate();

			
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
	public void update(TicketComment ticketcomment) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ticketcomment.getTkt_id());
			pstmt.setInt(2, ticketcomment.getMember_id());
			pstmt.setString(3, ticketcomment.getTicket_comment_content());
			pstmt.setDouble(4, ticketcomment.getTkt_total_score());
			pstmt.setInt(5, ticketcomment.getTicket_comment_id());


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
	public void delete(Integer ticket_comment_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ticket_comment_id);

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
	public TicketComment findByPrimaryKey(Integer ticket_comment_id) {

		TicketComment ticketcomment = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ticket_comment_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ticketcomment = new TicketComment();
				ticketcomment.setTicket_comment_id(rs.getInt(1));
				ticketcomment.setTkt_id(rs.getInt(2));
				ticketcomment.setMember_id(rs.getInt(3));
				ticketcomment.setTicket_comment_content(rs.getString(4));
				ticketcomment.setTkt_total_score((double) rs.getInt(5));
				ticketcomment.setTicket_comment_time(rs.getTimestamp(6));
		
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
		return ticketcomment;
	}
	
	
	@Override
	public List<TicketComment>getAll() {
		
		List<TicketComment>list= new ArrayList<TicketComment>();
		TicketComment ticketcomment = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ticketcomment = new TicketComment();
				ticketcomment.setTicket_comment_id(rs.getInt(1));
				ticketcomment.setTkt_id(rs.getInt(2));
				ticketcomment.setMember_id(rs.getInt(3));
				ticketcomment.setTicket_comment_content(rs.getString(4));
				ticketcomment.setTkt_total_score((double) rs.getInt(5));
				ticketcomment.setTicket_comment_time(rs.getTimestamp(6));
				list.add(ticketcomment);
		
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
	public Double getAllCommentScore(Integer tkt_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double score =null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_COMMENTSCORE);
			pstmt.setInt(1, tkt_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				score=(double) rs.getFloat(1);
			}

			
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
		return score;
	}


	@Override
	public Integer getAllCommentCount(Integer tkt_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer total_people =null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_COMMENTCOUNT);
			pstmt.setInt(1, tkt_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				total_people=rs.getInt(1);
			}

			
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
		return total_people;
	}
	
	}
	

