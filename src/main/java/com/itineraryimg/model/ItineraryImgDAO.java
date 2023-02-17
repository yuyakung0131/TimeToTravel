package com.itineraryimg.model;

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



public class ItineraryImgDAO implements ItineraryImgDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Itinerary_img (itinerary_img,itinerary_type_id) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_img";
	private static final String GET_ONE_STMT = "SELECT * FROM  Itinerary_img where itinerary_img_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_img set itinerary_type_id =?, itinerary_img=?  where itinerary_img_id = ?";
	private static final String DELETE = "DELETE FROM Itinerary_img where itinerary_img_id = ?";
	private static final String GET_ITINERARYTYPEID_STMT="select * from itinerary_img where itinerary_type_id=? ";
	private static final String GET_TYPE_STMT = "SELECT * FROM Itinerary_img WHERE ITINERARY_TYPE_ID=?";
	@Override
	public void insert(ItineraryImgVO itineraryImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			System.out.println(itineraryImgVO.getItinerary_img());
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setBytes(1, itineraryImgVO.getItinerary_img());
			pstmt.setInt(2, itineraryImgVO.getItinerary_type_id());

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
	public void update(ItineraryImgVO itineraryImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, itineraryImgVO.getItinerary_type_id());
			pstmt.setBytes(2, itineraryImgVO.getItinerary_img());
			pstmt.setInt(3, itineraryImgVO.getItinerary_img_id());

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
	public void delete(Integer itinerary_img_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, itinerary_img_id);

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
	public ItineraryImgVO findByPrimaryKey(Integer itinerary_img_id) {
		ItineraryImgVO itineraryImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_img_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryImgVO = new ItineraryImgVO();
				itineraryImgVO.setItinerary_img_id(rs.getInt("itinerary_img_id"));
				itineraryImgVO.setItinerary_img(rs.getBytes("itinerary_img"));
				itineraryImgVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
			}
			// Handle any SQL errors
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
		return itineraryImgVO;
	}

	@Override
	public List<ItineraryImgVO> getAll() {
		List<ItineraryImgVO> list = new ArrayList<ItineraryImgVO>();
		ItineraryImgVO itineraryImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryImgVO = new ItineraryImgVO();
				itineraryImgVO.setItinerary_img_id(rs.getInt("itinerary_img_id"));
				itineraryImgVO.setItinerary_img(rs.getBytes("itinerary_img"));
				itineraryImgVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));

				list.add(itineraryImgVO);
			}

			// Handle any SQL errors
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
	public List<ItineraryImgVO> findByItineraryTypeId(Integer itinerary_type_id) {
		List<ItineraryImgVO> list = new ArrayList<ItineraryImgVO>();
		ItineraryImgVO itineraryImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ITINERARYTYPEID_STMT);
			pstmt.setInt(1,itinerary_type_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				itineraryImgVO = new ItineraryImgVO();
				itineraryImgVO.setItinerary_img_id(rs.getInt("itinerary_img_id"));
				itineraryImgVO.setItinerary_img(rs.getBytes("itinerary_img"));
				itineraryImgVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				list.add(itineraryImgVO);
		
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

	
	//0201 新增
			@Override
			public List<ItineraryImgVO> getItrImgByType(Integer itinerary_type_id) {
				
					List<ItineraryImgVO> list = new ArrayList<ItineraryImgVO>();
					ItineraryImgVO itineraryImgVO = null;

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_TYPE_STMT);

						pstmt.setInt(1, itinerary_type_id);

						rs = pstmt.executeQuery();

						while (rs.next()) {
							itineraryImgVO = new ItineraryImgVO();
							itineraryImgVO.setItinerary_img_id(rs.getInt("itinerary_img_id"));
							itineraryImgVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));

							list.add(itineraryImgVO);
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
//		
//		ItineraryImgJDBCDAO dao = new ItineraryImgJDBCDAO();
//		
//		//查詢all
//		List<ItineraryImgVO>list = dao.getAll();
//		for(ItineraryImgVO a : list) {
//			System.out.print(a.getItinerary_img_id() + ",");
//			System.out.print(a.getItinerary_type_id() + ",");
//			System.out.print(a.getItinerary_img());
//
//			System.out.println();
//		}
//
//		//新增
////		ItineraryImgVO itineraryImgVO1 = new ItineraryImgVO();
////		itineraryImgVO1.setItinerary_type_id(1);
////		dao.insert(itineraryImgVO1);
//	
//		//修改
////		ItineraryImgVO itineraryImgVO2 = new ItineraryImgVO();
////		itineraryImgVO2.setItinerary_type_id(2);
////		itineraryImgVO2.setItinerary_img_id(1);
////		dao.update(itineraryImgVO2);
//		
//		//刪除
////		dao.delete(2);
//	
//		//查詢
//		ItineraryImgVO itineraryImgVO3 = dao.findByPrimaryKey(1);
//		System.out.print(itineraryImgVO3.getItinerary_img_id() + ",");
//		System.out.print(itineraryImgVO3.getItinerary_type_id() + ",");
//	
//		
//		System.out.println("-----------------------");
//
//	}

}
