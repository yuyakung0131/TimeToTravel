package com.tktitem.model;

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



public class TktItemDAO implements TktItemDAO_interface {
	

	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into TKT_ITEM(TKT_ID,TKT_ORDER_ID,AMOUNT,TKT_PRICE,TKT_DEADLINE) values(?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from TKT_ITEM order by TKT_ID";
	private static final String GET_ONE_STMT = "select * from TKT_ITEM where TKT_ID = ? and TKT_ORDER_ID=?";
	private static final String DELETE = "delete from TKT_ITEM where TKT_ID = ?";
	private static final String GET_BY_ORDERID = "select * from TKT_ITEM where TKT_ORDER_ID=?";
	private static final String UPDATE = //???�ǩ�?
			"UPDATE TKT_ITEM set TKT_ID=?, AMOUNT=?, TKT_PRICE=?, TKT_DEADLINE=? where TKT_ORDER_ID = ?";
	
	
	
	@Override
	public void insert(TktItem tktitem) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tktitem.getTkt_id());
			pstmt.setInt(2, tktitem.getTkt_order_id());
			pstmt.setInt(3, tktitem.getAmount());
			pstmt.setInt(4, tktitem.getTkt_price());
			pstmt.setObject(5, tktitem.getTkt_deadline());

			
	

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
	public void update(TktItem tktitem) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tktitem.getTkt_order_id());
			pstmt.setInt(2, tktitem.getAmount());
			pstmt.setInt(3, tktitem.getTkt_price());
			pstmt.setObject(4, tktitem.getTkt_deadline());
			pstmt.setInt(5, tktitem.getTkt_id());
	


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
	public void delete(Integer tkt_id,Integer tkt_order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkt_id);
			pstmt.setInt(2, tkt_order_id);

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
	public TktItem findByPrimaryKey(Integer tkt_id,Integer tkt_order_id) {

		TktItem tktitem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_id);
			pstmt.setInt(2, tkt_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				tktitem = new TktItem();
				tktitem.setTkt_id(rs.getInt(1));
				tktitem.setTkt_order_id(rs.getInt(2));
				tktitem.setAmount(rs.getInt(3));
				tktitem.setTkt_price(rs.getInt(4));
				tktitem.setTkt_deadline((LocalDateTime)(rs.getObject(5)));
		
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
		return tktitem;
	}
	
	
	@Override
	public List<TktItem> getAll() {
		List<TktItem> list = new ArrayList<TktItem>();
		TktItem tktitem = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				tktitem = new TktItem();
				tktitem.setTkt_id(rs.getInt(1));
				tktitem.setTkt_order_id(rs.getInt(2));
				tktitem.setAmount(rs.getInt(3));
				tktitem.setTkt_price(rs.getInt(4));
				tktitem.setTkt_deadline((LocalDateTime)(rs.getObject(5)));
				list.add(tktitem);
		
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
	   public List<TktItem> findByOrderId(Integer tkt_order_id){
				List<TktItem> list = new ArrayList<TktItem>();
				TktItem tktitem = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_BY_ORDERID);
					pstmt.setInt(1, tkt_order_id);
		            rs = pstmt.executeQuery();
					while (rs.next()) {
						tktitem = new TktItem();
						tktitem.setTkt_id(rs.getInt(1));
						tktitem.setTkt_order_id(rs.getInt(2));
						tktitem.setAmount(rs.getInt(3));
						tktitem.setTkt_price(rs.getInt(4));
						tktitem.setTkt_deadline((LocalDateTime) rs.getObject(5));
						list.add(tktitem);
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
