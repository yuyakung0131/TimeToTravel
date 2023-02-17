package com.shoppinglist.model;

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



public class ShoppingListDAO implements ShoppingListDAO_interface {
	

	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into SHOPPING_LIST(TKT_ID,MEMBER_ID,TKT_AMOUNT) values(?,?,?)";
//	private static final String INSERT_ITEM_STMT="insert into SHOPPING_LIST(TKT_ID,MEMBER_ID) values(?,?)";
	private static final String GET_ALL_STMT = "select * from SHOPPING_LIST order by TKT_ID";
	private static final String GET_ONE_STMT = "select * from SHOPPING_LIST where TKT_ID = ? and MEMBER_ID=?";
	private static final String DELETE = "delete from SHOPPING_LIST where TKT_ID = ? and MEMBER_ID=?";
	private static final String UPDATE = 
			"update SHOPPING_LIST set TKT_AMOUNT=? where TKT_ID=? and MEMBER_ID = ?";
	private static final String GET_ALL_BY_MEMBERID="select * from SHOPPING_LIST where MEMBER_ID=?";
	
	
	
	@Override
	public void insert(ShoppingList shoppinglist) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shoppinglist.getTkt_id());
			pstmt.setInt(2, shoppinglist.getMember_id());
			pstmt.setInt(3, shoppinglist.getTkt_amount());

	
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
	
	
//	@Override
//	public void insertItem(ShoppingList shoppinglist) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	
	
	
	
	
	@Override
	public void update(ShoppingList shoppinglist) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(2, shoppinglist.getTkt_id());
			pstmt.setInt(3, shoppinglist.getMember_id());
			pstmt.setInt(1, shoppinglist.getTkt_amount());


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
	public void delete(Integer tkt_id,Integer member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkt_id);
			pstmt.setInt(2, member_id);


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
	public ShoppingList findByPrimaryKey(Integer tkt_id,Integer member_id) {

		ShoppingList shoppinglist = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_id);
			pstmt.setInt(2, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				shoppinglist = new ShoppingList();
				shoppinglist.setTkt_id(rs.getInt(1));
				shoppinglist.setMember_id(rs.getInt(2));
				shoppinglist.setCart_fav_date(rs.getTimestamp(3));
				shoppinglist.setTkt_amount(rs.getInt(4));

		
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
		return shoppinglist;
	}
	
	@Override
	public List<ShoppingList>getAll() {
		
		List<ShoppingList>list= new ArrayList<ShoppingList>();
		ShoppingList shoppinglist = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				shoppinglist = new ShoppingList();
				shoppinglist.setTkt_id(rs.getInt(1));
				shoppinglist.setMember_id(rs.getInt(2));
				shoppinglist.setCart_fav_date(rs.getTimestamp(3));
				shoppinglist.setTkt_amount(rs.getInt(4));
				list.add(shoppinglist);
		
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
	public List<ShoppingList> getAllbyMember(Integer member_id) {
		List<ShoppingList>list= new ArrayList<ShoppingList>();
		ShoppingList shoppinglist = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBERID);	
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				shoppinglist = new ShoppingList();
				shoppinglist.setTkt_id(rs.getInt(1));
				shoppinglist.setMember_id(rs.getInt(2));
				shoppinglist.setCart_fav_date(rs.getTimestamp(3));
				shoppinglist.setTkt_amount(rs.getInt(4));
				list.add(shoppinglist);
		
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
