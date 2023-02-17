package com.tktimg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tickettype.model.TicketType;
import com.tktitem.model.TktItem;


public class TktImgDAO implements TktImgDAO_interface {
	

	
	
	
	
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "insert into TKT_IMG(TKT_IMG,TKT_ID) values(?,?) ";
	private static final String GET_ALL_STMT = "select * from TKT_IMG order by TKT_IMG_ID ";
	private static final String GET_ONE_STMT = "select * from TKT_IMG where TKT_IMG_ID = ? ";
	private static final String GET_TKTID_STMT="select * from tkt_img where TKT_ID=? ";
	private static final String DELETE = "delete from TKT_IMG where TKT_IMG_ID = ? ";
	private static final String UPDATE = 
			"update TKT_IMG set TKT_IMG=?, TKT_ID=? where TKT_IMG_ID = ? ";
	
	
	@Override
	public Integer insert(TktImg tktimg) {
		Integer tkt_img_id = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setBytes(1,tktimg.getTkt_img());
			pstmt.setInt(2,tktimg.getTkt_id());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			tkt_img_id = rs.getInt(1);
			

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
          return tkt_img_id;
	}
	
	
	@Override
	public void update(TktImg tktimg) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, tktimg.getTkt_img());
			pstmt.setInt(2, tktimg.getTkt_id());
			pstmt.setInt(3, tktimg.getTkt_img_id());


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
	public void delete(Integer tkt_img_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkt_img_id);

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
	public TktImg findByPrimaryKey(Integer tkt_img_id) {

		TktImg tktimg = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_img_id);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				tktimg = new TktImg();
				tktimg.setTkt_img_id(rs.getInt(1));
				tktimg.setTkt_img(rs.getBytes(2));
				tktimg.setTkt_id(rs.getInt(3));

		
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
		return tktimg;
	}
	
	
	
	@Override
	public List<TktImg> getAll() {
		List<TktImg> list = new ArrayList<TktImg>();
		TktImg tktimg = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				tktimg = new TktImg();
				tktimg.setTkt_img_id(rs.getInt(1));
				tktimg.setTkt_img(rs.getBytes(2));
				tktimg.setTkt_id(rs.getInt(3));
				list.add(tktimg);
		
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
	public List<TktImg> findByTktId(Integer tkt_id) {
		List<TktImg> list = new ArrayList<TktImg>();
		TktImg tktimg = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TKTID_STMT);
			pstmt.setInt(1,tkt_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				tktimg = new TktImg();
				tktimg.setTkt_img_id(rs.getInt(1));
				tktimg.setTkt_img(rs.getBytes(2));
				tktimg.setTkt_id(rs.getInt(3));
				list.add(tktimg);
		
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
	public Set<TktImg> getAllOneImage() {
		 Set<TktImg> set = new HashSet();
		TktImg tktimg = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				tktimg = new TktImg();
				tktimg.setTkt_img_id(rs.getInt(1));
				tktimg.setTkt_img(rs.getBytes(2));
				tktimg.setTkt_id(rs.getInt(3));
				
				
				
				
				
				
				set.add(tktimg);
		
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
		return set;
	}
	
	
	
	
	

}
