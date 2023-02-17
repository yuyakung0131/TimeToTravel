package com.promotionitem.model;

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

public class PromotionItemDAO implements PromotionItemDAO_interface {
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
	private static final String GET_ALL_STMT = "SELECT PROMOTION_ID,ROOM_TYPE_ID,DISCOUNT_NUMBER"
			+ " FROM promotion_item order by PROMOTION_ID";
	private static final String INSERT_STMT = "insert into promotion_item(PROMOTION_ID, ROOM_TYPE_ID, DISCOUNT_NUMBER)"
			+ "values(?, ?, ?)";
	//育生新增房型抓促銷用
	private static final String GET_PROBYROOMTYPE= "SELECT * FROM promotion_item where room_type_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM promotion_item where PROMOTION_ID = ?";
	private static final String UPDATE = "UPDATE promotion_item set DISCOUNT_NUMBER=? where PROMOTION_ID=? and room_type_id =? ";

	@Override
	public List<PromotionItemVO> getAll() {
		List<PromotionItemVO> list = new ArrayList<PromotionItemVO>();
		PromotionItemVO promotionItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				promotionItemVO = new PromotionItemVO();
				promotionItemVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				promotionItemVO.setDiscount_number(rs.getDouble("discount_number"));

				list.add(promotionItemVO);
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

	@Override
	public void insert(PromotionItemVO promotionItemVO) {

		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setDouble(1, promotionItemVO.getPromotion_id());
			pstmt.setDouble(2, promotionItemVO.getRoom_type_id());
			pstmt.setDouble(3, promotionItemVO.getDiscount_number());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); // mean insert success
		
		} catch (SQLException se) {
			throw new RuntimeException("資料庫問題! " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close(); // statement system close
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close(); // connection system close
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(PromotionItemVO promotionItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, promotionItemVO.getDiscount_number());
			
			pstmt.setInt(2, promotionItemVO.getPromotion_id());
			pstmt.setInt(3, promotionItemVO.getRoom_type_id());

			pstmt.executeUpdate();

			
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
	//育生新增房型抓促銷用
	public PromotionItemVO getProByRoomType(Integer room_type_id){
		PromotionItemVO promotionItemVO = new PromotionItemVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_PROBYROOMTYPE);

			pstmt.setInt(1, room_type_id);

			rs = pstmt.executeQuery();

	
			while (rs.next()) {
				promotionItemVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				promotionItemVO.setDiscount_number(rs.getDouble("discount_number"));

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
		return promotionItemVO;
	}
	
	@Override
	public PromotionItemVO findByPrimaryKey(Integer promotion_id) {
		PromotionItemVO promotionItemVO = new PromotionItemVO();
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
				promotionItemVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				promotionItemVO.setDiscount_number(rs.getDouble("discount_number"));

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
		return promotionItemVO;
	}

	@Override
	public PromotionItemVO findByTwo(Integer promotion_id, Integer room_type_id) {
		PromotionItemVO promotionItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement("select * from promotion_item where PROMOTION_ID = ? and ROOM_TYPE_ID = ?");
			pstmt.setInt(1, promotion_id);
			pstmt.setInt(2, room_type_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// promotionVO 也稱為 Domain objects
				promotionItemVO = new PromotionItemVO();
				promotionItemVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionItemVO.setRoom_type_id(rs.getInt("room_type_id"));
				promotionItemVO.setDiscount_number(rs.getDouble("discount_number"));

			}
			return promotionItemVO;
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
	}
public static void main(String[] args) {
	PromotionItemDAO dItemJDBCDAO= new PromotionItemDAO();
	PromotionItemVO vo = new PromotionItemVO();
	vo.setRoom_type_id(1);
	vo.setPromotion_id(1);
	vo.setDiscount_number(0.4);
	dItemJDBCDAO.update(vo);
}
}
