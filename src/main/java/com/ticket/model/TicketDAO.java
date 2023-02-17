package com.ticket.model;

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




public class TicketDAO implements TicketDAO_interface {
	

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into TICKET(TKT_DATE,TKT_TYPE_ID,TKT_NAME,TKT_PRICE,FIRM_ID,TKT_AMOUNT,INSTRUCTION,TKT_TOTAL_SCORE,TKT_TOTAL_PEOPLE) values(?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from TICKET order by TKT_ID";
	private static final String GET_ALL_TYPE_STMT="select * from TICKET where TKT_TYPE_ID=? order by TKT_ID ";
	private static final String Get_ALL_BETWEEN_PRICE="select * from TICKET where TKT_PRICE between ? and ? order by TKT_PRICE ";
	private static final String GET_SMALL_DATE_STMT="select * from ticket where TKT_DATE<? order by TKT_DATE ";
	private static final String GET_COMMENT_SCORE_STMT="select * from ticket where TKT_TOTAL_SCORE>=? order by TKT_TOTAL_SCORE ";
	private static final String GET_SMALL_AMOUNT_STMT="select * from ticket where TKT_AMOUNT<? order by TKT_AMOUNT  ";
	private static final String GET_ONE_STMT = "select * from TICKET where TKT_ID = ?";
	private static final String DELETE = "delete from TICKET where TKT_ID = ?";
	private static final String UPDATE = 
			"UPDATE ticket set TKT_DATE=?, TKT_TYPE_ID=?, TKT_NAME=?, TKT_PRICE=?, FIRM_ID=?, TKT_AMOUNT=? , INSTRUCTION=?, TKT_TOTAL_SCORE=?, TKT_TOTAL_PEOPLE=? where TKT_ID = ?";
	private static final String UPDATE_TKT_AMOUNT="UPDATE ticket set TKT_AMOUNT=? where TKT_ID = ?";
	private static final String UPDATE_TKT_COMMENT="UPDATE ticket set TKT_TOTAL_SCORE=?, TKT_TOTAL_PEOPLE=? where TKT_ID = ?";
		
		
	@Override
	public void insert(Ticket ticket) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ticket.getTkt_date());
			pstmt.setByte(2, ticket.getTkt_type_id());
			pstmt.setString(3, ticket.getTkt_name());
			pstmt.setInt(4, ticket.getTkt_price());
			pstmt.setInt(5, ticket.getFirm_id());
			pstmt.setInt(6, ticket.getTkt_amount());
			pstmt.setString(7, ticket.getInstruction());
			pstmt.setDouble(8, ticket.getTkt_total_score());
			pstmt.setInt(9, ticket.getTkt_total_people());
			
	

			pstmt.executeUpdate();

	
 
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
	public void update(Ticket ticket) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ticket.getTkt_date());
			pstmt.setByte(2, ticket.getTkt_type_id());
			pstmt.setString(3, ticket.getTkt_name());
			pstmt.setInt(4, ticket.getTkt_price());
			pstmt.setInt(5, ticket.getFirm_id());
			pstmt.setInt(6, ticket.getTkt_amount());
			pstmt.setString(7, ticket.getInstruction());
			pstmt.setDouble(8, ticket.getTkt_total_score());
			pstmt.setInt(9, ticket.getTkt_total_people());
			pstmt.setInt(10, ticket.getTkt_id());

			pstmt.executeUpdate();

			
		}  catch (SQLException se) {
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
	public void updatebyAmount(Ticket ticket) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_TKT_AMOUNT);


			pstmt.setInt(1, ticket.getTkt_amount());
			pstmt.setInt(2, ticket.getTkt_id());

			pstmt.executeUpdate();

			
		}  catch (SQLException se) {
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
	public void updatebyComment(Ticket ticket) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_TKT_COMMENT);


			pstmt.setDouble(1, ticket.getTkt_total_score());
			pstmt.setInt(2, ticket.getTkt_total_people());
			pstmt.setInt(3, ticket.getTkt_id());

			pstmt.executeUpdate();

			
		}  catch (SQLException se) {
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
	public void delete(Integer tkt_date) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkt_date);

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
	public Ticket findByPrimaryKey(Integer tkt_id) {

		Ticket ticket = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ticket = new Ticket();
				ticket.setTkt_id(rs.getInt(1));
				ticket.setTkt_date(rs.getInt(2));
				ticket.setTkt_type_id(rs.getByte(3));
				ticket.setTkt_name(rs.getString(4));
				ticket.setTkt_price(rs.getInt(5));
				ticket.setFirm_id(rs.getInt(6));
				ticket.setTkt_amount(rs.getInt(7));
				ticket.setInstruction(rs.getString(8));
				ticket.setTkt_total_score((double) rs.getInt(9));
				ticket.setTkt_total_people(rs.getInt(10));
			}

		
		}  catch (SQLException se) {
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
		return ticket;
	}
	
	
	@Override
	public List<Ticket> getAll() {
		List<Ticket> list = new ArrayList<Ticket>();
		Ticket ticket = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
	
				ticket = new Ticket();
				ticket.setTkt_id(rs.getInt(1));
				ticket.setTkt_date(rs.getInt(2));
				ticket.setTkt_type_id(rs.getByte(3));
				ticket.setTkt_name(rs.getString(4));
				ticket.setTkt_price(rs.getInt(5));
				ticket.setFirm_id(rs.getInt(6));
				ticket.setTkt_amount(rs.getInt(7));
				ticket.setInstruction(rs.getString(8));
				ticket.setTkt_total_score((double) rs.getInt(9));
				ticket.setTkt_total_people(rs.getInt(10));
				list.add(ticket); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
public List<Ticket>findByType(Integer tkt_type_id){
	
	List<Ticket> list = new ArrayList<Ticket>();
	Ticket ticket = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

	con = ds.getConnection();
	pstmt = con.prepareStatement(GET_ALL_TYPE_STMT);
	pstmt.setInt(1,tkt_type_id);
	rs = pstmt.executeQuery();
	while (rs.next()) {

		ticket = new Ticket();
		ticket.setTkt_id(rs.getInt(1));
		ticket.setTkt_date(rs.getInt(2));
		ticket.setTkt_type_id(rs.getByte(3));
		ticket.setTkt_name(rs.getString(4));
		ticket.setTkt_price(rs.getInt(5));
		ticket.setFirm_id(rs.getInt(6));
		ticket.setTkt_amount(rs.getInt(7));
		ticket.setInstruction(rs.getString(8));
		ticket.setTkt_total_score((double) rs.getInt(9));
		ticket.setTkt_total_people(rs.getInt(10));
		list.add(ticket); // Store the row in the list
	}

	// Handle any driver errors
}  catch (SQLException se) {
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
public List<Ticket> findBetweenPrice(Integer min, Integer max) {
	// TODO Auto-generated method stub
	List<Ticket> list = new ArrayList<Ticket>();
	Ticket ticket = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

	con = ds.getConnection();
	pstmt = con.prepareStatement(Get_ALL_BETWEEN_PRICE);
	pstmt.setInt(1,min);
	pstmt.setInt(2,max);
	rs = pstmt.executeQuery();
	while (rs.next()) {

		ticket = new Ticket();
		ticket.setTkt_id(rs.getInt(1));
		ticket.setTkt_date(rs.getInt(2));
		ticket.setTkt_type_id(rs.getByte(3));
		ticket.setTkt_name(rs.getString(4));
		ticket.setTkt_price(rs.getInt(5));
		ticket.setFirm_id(rs.getInt(6));
		ticket.setTkt_amount(rs.getInt(7));
		ticket.setInstruction(rs.getString(8));
		ticket.setTkt_total_score((double) rs.getInt(9));
		ticket.setTkt_total_people(rs.getInt(10));
		list.add(ticket); // Store the row in the list
	}

	// Handle any driver errors
}  catch (SQLException se) {
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
public List<Ticket> findBySmallAmount(Integer tkt_amount) {
	List<Ticket> list = new ArrayList<Ticket>();
	Ticket ticket = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

	con = ds.getConnection();
	pstmt = con.prepareStatement(GET_SMALL_AMOUNT_STMT);
	pstmt.setInt(1,tkt_amount);
	rs = pstmt.executeQuery();
	while (rs.next()) {
	
		ticket = new Ticket();
		ticket.setTkt_id(rs.getInt(1));
		ticket.setTkt_date(rs.getInt(2));
		ticket.setTkt_type_id(rs.getByte(3));
		ticket.setTkt_name(rs.getString(4));
		ticket.setTkt_price(rs.getInt(5));
		ticket.setFirm_id(rs.getInt(6));
		ticket.setTkt_amount(rs.getInt(7));
		ticket.setInstruction(rs.getString(8));
		ticket.setTkt_total_score((double) rs.getInt(9));
		ticket.setTkt_total_people(rs.getInt(10));
		list.add(ticket); // Store the row in the list
	}

	// Handle any driver errors
}  catch (SQLException se) {
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
public List<Ticket> findBySmallDate(Integer tkt_date) {
	List<Ticket> list = new ArrayList<Ticket>();
	Ticket ticket = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

	con = ds.getConnection();
	pstmt = con.prepareStatement(GET_SMALL_DATE_STMT);
	pstmt.setInt(1,tkt_date);
	rs = pstmt.executeQuery();
	while (rs.next()) {
		// empVO �]�٬� Domain objects
		ticket = new Ticket();
		ticket.setTkt_id(rs.getInt(1));
		ticket.setTkt_date(rs.getInt(2));
		ticket.setTkt_type_id(rs.getByte(3));
		ticket.setTkt_name(rs.getString(4));
		ticket.setTkt_price(rs.getInt(5));
		ticket.setFirm_id(rs.getInt(6));
		ticket.setTkt_amount(rs.getInt(7));
		ticket.setInstruction(rs.getString(8));
		ticket.setTkt_total_score((double) rs.getInt(9));
		ticket.setTkt_total_people(rs.getInt(10));
		list.add(ticket); // Store the row in the list
	}

	// Handle any driver errors
}  catch (SQLException se) {
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
public List<Ticket> findByCommentScore(Integer tkt_total_score) {
	List<Ticket> list = new ArrayList<Ticket>();
	Ticket ticket = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

	con = ds.getConnection();
	pstmt = con.prepareStatement(GET_COMMENT_SCORE_STMT);
	pstmt.setInt(1,tkt_total_score);
	rs = pstmt.executeQuery();
	while (rs.next()) {

		ticket = new Ticket();
		ticket.setTkt_id(rs.getInt(1));
		ticket.setTkt_date(rs.getInt(2));
		ticket.setTkt_type_id(rs.getByte(3));
		ticket.setTkt_name(rs.getString(4));
		ticket.setTkt_price(rs.getInt(5));
		ticket.setFirm_id(rs.getInt(6));
		ticket.setTkt_amount(rs.getInt(7));
		ticket.setInstruction(rs.getString(8));
		ticket.setTkt_total_score((double) rs.getInt(9));
		ticket.setTkt_total_people(rs.getInt(10));
		list.add(ticket); // Store the row in the list
	}

	// Handle any driver errors
}  catch (SQLException se) {
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





