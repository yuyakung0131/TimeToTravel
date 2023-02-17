package com.itinerarytype.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.itinerary.model.ItineraryVO;
import com.itinerarycomment.model.ItineraryCommentVO;
import com.itinerarylocdetail.model.ItineraryLocDetailDAO;
import com.itinerarylocdetail.model.ItineraryLocDetailVO;





public class ItineraryTypeDAO  implements ItineraryTypeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Itinerary_Type (itinerary_class_id,firm_id,itinerary_title,itinerary_info,itinerary_other,itinerary_price,itinerary_min,itinerary_max,itinerary_type_state) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_Type";
	private static final String GET_ONE_STMT = "SELECT * FROM  Itinerary_Type where  itinerary_type_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_Type set  itinerary_class_id=?,firm_id=?,itinerary_title=?,itinerary_info=?,itinerary_other=?,itinerary_price=?,itinerary_min=?,itinerary_max=?,itinerary_type_state=?,itinerary_total_score=?,itinerary_total_people=? where itinerary_type_id= ?";
	private static final String DELETE = "DELETE FROM Itinerary_Type where itinerary_type_id = ?";
	private static final String GET_ITINERARY_CLASS_STMT = "SELECT * FROM  itinerary_type where  itinerary_class_id = ?";
	private static final String GET_FIRM_STMT = "SELECT * FROM  itinerary_type where  firm_id = ?";
	//WANG 0126 新增
	private static final String GET_COMMENT_STMT = "SELECT * FROM ITINERARY_COMMENT WHERE ITINERARY_TYPE_ID= ?";
	private static final String UPDATE_BY_COMMENT = "UPDATE ITINERARY_TYPE SET ITINERARY_TOTAL_SCORE = ITINERARY_TOTAL_SCORE + ? ,  ITINERARY_TOTAL_PEOPLE = ITINERARY_TOTAL_PEOPLE + 1 WHERE ITINERARY_TYPE_ID = ?";
	private static final String GET_ITR_TITLE = "SELECT ITINERARY_TITLE,ITINERARY_TYPE_ID FROM itinerary_type WHERE ITINERARY_TYPE_STATE = 0;";
	private static final String INSERT_LOC_DETAIL = "INSERT INTO Itinerary_Loc_Detail (itinerary_loc_id,itinerary_type_id) VALUES (?,?)";
	
	@Override
	public void insert(ItineraryTypeVO itineraryTypeVO ,String [] itinerary_loc_ids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
		
//			pstmt = con.prepareStatement(INSERT_STMT , itinerary_loc_ids);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, itineraryTypeVO.getItinerary_class_id());
			pstmt.setInt(2, itineraryTypeVO.getFirm_id());
			pstmt.setString(3, itineraryTypeVO.getItinerary_title());
			pstmt.setString(4, itineraryTypeVO.getItinerary_info());
			pstmt.setString(5, itineraryTypeVO.getItinerary_other());
			pstmt.setInt(6, itineraryTypeVO.getItinerary_price());
			pstmt.setInt(7, itineraryTypeVO.getItinerary_min());
			pstmt.setInt(8, itineraryTypeVO.getItinerary_max());
			pstmt.setByte(9, itineraryTypeVO.getItinerary_type_state());
//			pstmt.setInt(10, itineraryTypeVO.getItinerary_total_score());
//			pstmt.setInt(11, itineraryTypeVO.getItinerary_total_people());
			
			pstmt.executeUpdate();
			
			
			Integer itinerary_type_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
			
				itinerary_type_id = rs.getInt(1);
				
			}	
//			ItineraryLocDetailDAO itrLocDetailDao = new ItineraryLocDetailDAO();
//			ItineraryLocDetailVO itineraryLocDetailVO = new ItineraryLocDetailVO();
//			
//			if ( itinerary_loc_ids != null) {
//			for ( String itinerary_loc_id :  itinerary_loc_ids   ) {
//				System.out.println(itinerary_loc_id);
//				itineraryLocDetailVO.setItinerary_loc_id(Integer.parseInt(itinerary_loc_id));
//				itineraryLocDetailVO.setItinerary_type_id(itinerary_type_id);				
//				itrLocDetailDao.insert(itineraryLocDetailVO, con);
//				
//			}
//				
//			}
			if ( itinerary_loc_ids != null) {
				for ( String itinerary_loc_id :  itinerary_loc_ids   ) {	
			pstmt = con.prepareStatement(INSERT_LOC_DETAIL);
			
			pstmt.setInt(1, Integer.parseInt(itinerary_loc_id));
			pstmt.setInt(2, itinerary_type_id);
			
			pstmt.executeUpdate();
			
			
				}
			
			}
			con.commit();
			con.setAutoCommit(true);
			
			
			// Handle any SQL errors
		} 
		
		catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public void update(ItineraryTypeVO itineraryTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, itineraryTypeVO.getItinerary_class_id());
			pstmt.setInt(2, itineraryTypeVO.getFirm_id());
			pstmt.setString(3, itineraryTypeVO.getItinerary_title());
			pstmt.setString(4, itineraryTypeVO.getItinerary_info());
			pstmt.setString(5, itineraryTypeVO.getItinerary_other());
			pstmt.setInt(6, itineraryTypeVO.getItinerary_price());
			pstmt.setInt(7, itineraryTypeVO.getItinerary_min());
			pstmt.setInt(8, itineraryTypeVO.getItinerary_max());
			pstmt.setByte(9, itineraryTypeVO.getItinerary_type_state());
			pstmt.setInt(10, itineraryTypeVO.getItinerary_total_score());
			pstmt.setInt(11, itineraryTypeVO.getItinerary_total_people());
			pstmt.setInt(12, itineraryTypeVO.getItinerary_type_id());
			

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public ItineraryTypeVO findByPrimaryKey(Integer itinerary_type_id) {
		ItineraryTypeVO itineraryTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_type_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryTypeVO = new ItineraryTypeVO();
				itineraryTypeVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryTypeVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
				itineraryTypeVO.setFirm_id(rs.getInt("firm_id"));
				itineraryTypeVO.setItinerary_title(rs.getString("itinerary_title"));
//				itineraryTypeVO.setItinerary_img(rs.getByte[]("itinerary_img"));
				itineraryTypeVO.setItinerary_info(rs.getString("itinerary_info"));
				itineraryTypeVO.setItinerary_other(rs.getString("itinerary_other"));
				itineraryTypeVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryTypeVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryTypeVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryTypeVO.setItinerary_type_state(rs.getByte("itinerary_type_state"));
				itineraryTypeVO.setItinerary_total_score(rs.getInt("itinerary_total_score"));
				itineraryTypeVO.setItinerary_total_people(rs.getInt("itinerary_total_people"));
			}

			// Handle any SQL errors
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
		return itineraryTypeVO;
	}
	
	@Override
	public List<ItineraryTypeVO> getAll() {
		List<ItineraryTypeVO> list = new ArrayList<ItineraryTypeVO>();
		ItineraryTypeVO itineraryTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				itineraryTypeVO = new ItineraryTypeVO();
				itineraryTypeVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryTypeVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
				itineraryTypeVO.setFirm_id(rs.getInt("firm_id"));
				itineraryTypeVO.setItinerary_title(rs.getString("itinerary_title"));
				itineraryTypeVO.setItinerary_info(rs.getString("itinerary_info"));
				itineraryTypeVO.setItinerary_other(rs.getString("itinerary_other"));
				itineraryTypeVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryTypeVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryTypeVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryTypeVO.setItinerary_type_state(rs.getByte("itinerary_type_state"));
				itineraryTypeVO.setItinerary_total_score(rs.getInt("itinerary_total_score"));
				itineraryTypeVO.setItinerary_total_people(rs.getInt("itinerary_total_people"));
				list.add(itineraryTypeVO);
			}

			// Handle any SQL errors
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
	
	public void delete(Integer itinerary_type_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, itinerary_type_id);

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public List<ItineraryTypeVO> findByItinerary_class_id(Integer itinerary_class_id) {
		List<ItineraryTypeVO> list = new ArrayList<ItineraryTypeVO>();
		ItineraryTypeVO itineraryTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ITINERARY_CLASS_STMT);
			pstmt.setInt(1, itinerary_class_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryTypeVO = new ItineraryTypeVO();
				itineraryTypeVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryTypeVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
				itineraryTypeVO.setFirm_id(rs.getInt("firm_id"));
				itineraryTypeVO.setItinerary_title(rs.getString("itinerary_title"));
				itineraryTypeVO.setItinerary_info(rs.getString("itinerary_info"));
				itineraryTypeVO.setItinerary_other(rs.getString("itinerary_other"));
				itineraryTypeVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryTypeVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryTypeVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryTypeVO.setItinerary_type_state(rs.getByte("itinerary_type_state"));
				itineraryTypeVO.setItinerary_total_score(rs.getInt("itinerary_total_score"));
				itineraryTypeVO.setItinerary_total_people(rs.getInt("itinerary_total_people"));
				list.add(itineraryTypeVO);
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
	public List<ItineraryTypeVO> findByFirm_id(Integer firm_id) {
		List<ItineraryTypeVO> list = new ArrayList<ItineraryTypeVO>();
		ItineraryTypeVO itineraryTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_FIRM_STMT);
			pstmt.setInt(1, firm_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itineraryTypeVO = new ItineraryTypeVO();
				itineraryTypeVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryTypeVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
				itineraryTypeVO.setFirm_id(rs.getInt("firm_id"));
				itineraryTypeVO.setItinerary_title(rs.getString("itinerary_title"));
				itineraryTypeVO.setItinerary_info(rs.getString("itinerary_info"));
				itineraryTypeVO.setItinerary_other(rs.getString("itinerary_other"));
				itineraryTypeVO.setItinerary_price(rs.getInt("itinerary_price"));
				itineraryTypeVO.setItinerary_min(rs.getInt("itinerary_min"));
				itineraryTypeVO.setItinerary_max(rs.getInt("itinerary_max"));
				itineraryTypeVO.setItinerary_type_state(rs.getByte("itinerary_type_state"));
				itineraryTypeVO.setItinerary_total_score(rs.getInt("itinerary_total_score"));
				itineraryTypeVO.setItinerary_total_people(rs.getInt("itinerary_total_people"));
				list.add(itineraryTypeVO);
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

	
	
	


	
//	public static void main(String[] args) {
//		ItineraryTypeJDBCDAO dao = new ItineraryTypeJDBCDAO();
//		// 查詢all
//		List<ItineraryTypeVO> list = dao.getAll();
//		for (ItineraryTypeVO a : list) {
//			System.out.print(a.getItinerary_type_id() + ",");
//			System.out.print(a.getItinerary_class_id() + ",");
//			System.out.print(a.getFirm_id() + ",");
//			System.out.print(a.getItinerary_title() + ",");
//			System.out.print(a.getItinerary_info() + ",");
//			System.out.print(a.getItinerary_other() + ",");
//			System.out.print(a.getItinerary_price() + ",");
//			System.out.print(a.getItinerary_min() + ",");
//			System.out.print(a.getItinerary_max() + ",");
//			System.out.print(a.getItinerary_type_state() + ",");
//			System.out.print(a.getItinerary_total_score() + ",");
//			System.out.print(a.getItinerary_total_people());
//			System.out.println();
//		}
		
		//新增
//		ItineraryTypeVO itineraryTypeVO1= new ItineraryTypeVO();		
//		itineraryTypeVO1.setItinerary_class_id(7);
//		itineraryTypeVO1.setFirm_id(21);
//		itineraryTypeVO1.setItinerary_title("自行車西進武嶺一日來回");
//		itineraryTypeVO1.setItinerary_info("單車騎上武嶺一日來回");
//		itineraryTypeVO1.setItinerary_other("自備單車");
//		itineraryTypeVO1.setItinerary_price(1500);
//		itineraryTypeVO1.setItinerary_min(4);
//		itineraryTypeVO1.setItinerary_max(30);
//		itineraryTypeVO1.setItinerary_type_state(new Byte((byte)1));
//		itineraryTypeVO1.setItinerary_total_score(1);
//		itineraryTypeVO1.setItinerary_total_people(1);
//		dao.insert(itineraryTypeVO1);
		
		//修改
//		ItineraryTypeVO itineraryTypeVO2= new ItineraryTypeVO();		
//		itineraryTypeVO2.setItinerary_class_id(7);
//		itineraryTypeVO2.setFirm_id(19);
//		itineraryTypeVO2.setItinerary_title("自行車西進武嶺一日來回");
//		itineraryTypeVO2.setItinerary_info("單車騎上武嶺一日來回");
//		itineraryTypeVO2.setItinerary_other("無");
//		itineraryTypeVO2.setItinerary_price(1500);
//		itineraryTypeVO2.setItinerary_min(4);
//		itineraryTypeVO2.setItinerary_max(26);
//		itineraryTypeVO2.setItinerary_type_state(new Byte((byte)0));
//		itineraryTypeVO2.setItinerary_total_score(5);
//		itineraryTypeVO2.setItinerary_total_people(10);
//		itineraryTypeVO2.setItinerary_type_id(6);
//		dao.update(itineraryTypeVO2);
		
		//刪除
//		dao.delete(8);
		
		//查詢
//		ItineraryTypeVO itineraryTypeVO3 =dao.findByPrimaryKey(1);
//		System.out.print(itineraryTypeVO3.getItinerary_type_id() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_class_id() + ",");
//		System.out.print(itineraryTypeVO3.getFirm_id() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_title() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_info() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_other() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_price() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_min() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_max() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_type_state() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_total_score() + ",");
//		System.out.print(itineraryTypeVO3.getItinerary_total_people());
//		
//		
//		System.out.println("--------------------------------------");
//		}
		

		//WANG 0126 新增
				public List<ItineraryCommentVO> getCommentByType(Integer itinerary_type_id) {
					List<ItineraryCommentVO> list = new ArrayList<ItineraryCommentVO>();
					ItineraryCommentVO itineraryCommentVO = null;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {
						con = ds.getConnection();

						pstmt = con.prepareStatement(GET_COMMENT_STMT);
						pstmt.setInt(1, itinerary_type_id);
						rs = pstmt.executeQuery();
						while (rs.next()) {
							itineraryCommentVO = new ItineraryCommentVO();
							itineraryCommentVO.setItinerary_comment_id(rs.getInt("itinerary_comment_id"));
							itineraryCommentVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
							itineraryCommentVO.setMember_id(rs.getInt("member_id"));
							itineraryCommentVO.setItinerary_comment_post(rs.getString("itinerary_comment_post"));
							itineraryCommentVO.setItinerary_comment_star(rs.getInt("itinerary_comment_star"));
							itineraryCommentVO.setItinerary_comment_time(rs.getTimestamp("itinerary_comment_time"));
							list.add(itineraryCommentVO);
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
				public List<ItineraryTypeVO> getTypeByMula(String locList, String price1, String price2, String classId) {
					List<ItineraryTypeVO> list = new ArrayList<ItineraryTypeVO>();
					ItineraryTypeVO itineraryTypeVO =null;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String GET_BY = "SELECT * FROM itinerary_type t LEFT JOIN itinerary_loc_detail c ON t.ITINERARY_TYPE_ID = c.ITINERARY_TYPE_ID where ITINERARY_LOC_ID in ( " + locList + " ) AND ITINERARY_PRICE between " + price1 + " and " + price2 + " AND itinerary_class_id = " + classId + " AND ITINERARY_TYPE_STATE = 0 group by ITINERARY_TITLE;";
					try {
						
						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_BY);
						rs = pstmt.executeQuery();
						while (rs.next()) {
							itineraryTypeVO = new ItineraryTypeVO();
							itineraryTypeVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
							itineraryTypeVO.setItinerary_class_id(rs.getInt("itinerary_class_id"));
							itineraryTypeVO.setFirm_id(rs.getInt("firm_id"));
							itineraryTypeVO.setItinerary_title(rs.getString("itinerary_title"));
							itineraryTypeVO.setItinerary_info(rs.getString("itinerary_info"));
							itineraryTypeVO.setItinerary_other(rs.getString("itinerary_other"));
							itineraryTypeVO.setItinerary_price(rs.getInt("itinerary_price"));
							itineraryTypeVO.setItinerary_min(rs.getInt("itinerary_min"));
							itineraryTypeVO.setItinerary_max(rs.getInt("itinerary_max"));
							itineraryTypeVO.setItinerary_type_state(rs.getByte("itinerary_type_state"));
							itineraryTypeVO.setItinerary_total_score(rs.getInt("itinerary_total_score"));
							itineraryTypeVO.setItinerary_total_people(rs.getInt("itinerary_total_people"));
							list.add(itineraryTypeVO);
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
				public List<ItineraryTypeVO> getItrTitle() {
						List<ItineraryTypeVO> list = new ArrayList<ItineraryTypeVO>();
						ItineraryTypeVO itineraryTypeVO = null;

						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;

						try {

							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_ITR_TITLE);
							rs = pstmt.executeQuery();

							while (rs.next()) {
								itineraryTypeVO = new ItineraryTypeVO();
								itineraryTypeVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
								itineraryTypeVO.setItinerary_title(rs.getString("itinerary_title"));
								list.add(itineraryTypeVO);
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
				public void updateItrTotalComment(ItineraryTypeVO itineraryTypeVO) {
					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(UPDATE_BY_COMMENT);
						pstmt.setInt(1, itineraryTypeVO.getItinerary_total_score());
//						pstmt.setInt(11, itineraryTypeVO.getItinerary_total_people());
						pstmt.setInt(2, itineraryTypeVO.getItinerary_type_id());
						

						pstmt.executeUpdate();

						// Handle any driver errors
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
				public void insert(ItineraryTypeVO itineraryTypeVO) {
					// TODO Auto-generated method stub
					
				}
}