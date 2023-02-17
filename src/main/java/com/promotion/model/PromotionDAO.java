package com.promotion.model;

import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromotionDAO implements PromotionDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/room?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "ppom21256212";
	private static DataSource ds =null;
		
	static {
		try {
			Context ctx= new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/timetotravel");
		}catch(NamingException e) {
			
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO promotion (PROMOTION_NAME,PROMOTION_STARTDATE,PROMOTION_ENDDATE,PROMOTION_STATE) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PROMOTION_ID,PROMOTION_NAME,PROMOTION_STARTDATE"
			+ ",PROMOTION_ENDDATE,PROMOTION_STATE FROM promotion";
	private static final String GET_ONE_STMT = "SELECT PROMOTION_ID,PROMOTION_NAME,PROMOTION_STARTDATE,PROMOTION_ENDDATE,PROMOTION_STATE FROM promotion where PROMOTION_ID = ?";
	private static final String DELETE = "DELETE FROM promotion where PROMOTION_ID =?";
	private static final String UPDATE = "UPDATE promotion set PROMOTION_NAME=?, PROMOTION_STARTDATE=?, PROMOTION_ENDDATE=?, PROMOTION_STATE=? where PROMOTION_ID=?";
	private static final String GET_ALL_STATE = "SELECT * FROM PROMOTION where PROMOTION_STATE = ?";

	




	@Override
	public void insert(PromotionVO promotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 連線池
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			// 新增方法
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promotionVO.getPromotion_name());
			pstmt.setDate(2, promotionVO.getPromotion_startdate());
			pstmt.setDate(3, promotionVO.getPromotion_enddate());
			pstmt.setByte(4, promotionVO.getPromotion_state());
			pstmt.executeUpdate();
			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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

	@Override
	public void update(PromotionVO promotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotionVO.getPromotion_name());
			pstmt.setDate(2, promotionVO.getPromotion_startdate());
			pstmt.setDate(3, promotionVO.getPromotion_enddate());
			pstmt.setByte(4, promotionVO.getPromotion_state());
			pstmt.setInt(5, promotionVO.getPromotion_id());
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

	@Override
	public void delete(Integer promotion_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promotion_id);

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

	@Override
	public PromotionVO findByPrimaryKey(Integer promotion_id) {
		PromotionVO promotionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promotion_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// promotionVO 也稱為 Domain objects
				promotionVO = new PromotionVO();
				promotionVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionVO.setPromotion_name(rs.getString("promotion_name"));
				promotionVO.setPromotion_startdate(rs.getDate("promotion_startdate"));
				promotionVO.setPromotion_enddate(rs.getDate("promotion_enddate"));
				promotionVO.setPromotion_state(rs.getByte("promotion_state"));

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
		return promotionVO;
	}

	@Override
	public List<PromotionVO> getAll() {
		List<PromotionVO> list = new ArrayList<PromotionVO>();
		PromotionVO promotionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				promotionVO = new PromotionVO();
				promotionVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionVO.setPromotion_name(rs.getString("promotion_name"));
				promotionVO.setPromotion_startdate(rs.getDate("promotion_startdate"));
				promotionVO.setPromotion_enddate(rs.getDate("promotion_enddate"));
				promotionVO.setPromotion_state(rs.getByte("promotion_state"));

				list.add(promotionVO); // Store the row in the list
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
	//上下架選單用
		@Override
		public List<PromotionVO> getPromotionByState(Byte promotion_state) {
			List<PromotionVO> list = new ArrayList<PromotionVO>();
			PromotionVO promotionVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				con=ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STATE);
				
				pstmt.setInt(1,promotion_state);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					promotionVO = new PromotionVO();
					promotionVO.setPromotion_id(rs.getInt(1));
					promotionVO.setPromotion_name(rs.getString(2));
					promotionVO.setPromotion_startdate(rs.getDate(3));
					promotionVO.setPromotion_enddate(rs.getDate(4));
					promotionVO.setPromotion_state(rs.getByte(5));

					list.add(promotionVO); // Store the row in the list
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
		
		
	

	//以上為上下架
	@Override
	public List<PromotionVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}