package com.itinerarycomment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItineraryCommentDAO implements ItineraryCommentDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO Itinerary_Comment (itinerary_type_id,member_id,itinerary_comment_post,itinerary_comment_star) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Itinerary_Comment";
	private static final String GET_ONE_STMT = "SELECT * FROM  Itinerary_Comment where Itinerary_Comment_id = ?";
	private static final String UPDATE = "UPDATE Itinerary_Comment set itinerary_type_id=?,member_id=?,itinerary_comment_post=?,itinerary_comment_star=? where  itinerary_comment_id =?";

	@Override
	public void insert(ItineraryCommentVO itineraryCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, itineraryCommentVO.getItinerary_type_id());
			pstmt.setInt(2, itineraryCommentVO.getMember_id());
			pstmt.setString(3, itineraryCommentVO.getItinerary_comment_post());
			pstmt.setInt(4, itineraryCommentVO.getItinerary_comment_star());
	

			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					Integer id = rs.getInt(1);
				}
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
	}
	
	@Override
	public void  update(ItineraryCommentVO itineraryCommentVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, itineraryCommentVO.getItinerary_type_id());
			pstmt.setInt(2, itineraryCommentVO.getMember_id());
			pstmt.setString(3, itineraryCommentVO.getItinerary_comment_post());
			pstmt.setInt(4, itineraryCommentVO.getItinerary_comment_star());
			pstmt.setInt(5, itineraryCommentVO.getItinerary_comment_id());
			

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
	public ItineraryCommentVO findByPrimaryKey(Integer itinerary_Comment_id) {
		ItineraryCommentVO itineraryCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itinerary_Comment_id);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryCommentVO = new ItineraryCommentVO();
				itineraryCommentVO.setItinerary_comment_id(rs.getInt("itinerary_comment_id"));
				itineraryCommentVO.setItinerary_type_id(rs.getInt("itinerary_type_id"));
				itineraryCommentVO.setMember_id(rs.getInt("member_id"));
				itineraryCommentVO.setItinerary_comment_post(rs.getString("itinerary_comment_post"));
				itineraryCommentVO.setItinerary_comment_star(rs.getInt("itinerary_comment_star"));
				itineraryCommentVO.setItinerary_comment_time(rs.getTimestamp("itinerary_comment_time"));
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
		return itineraryCommentVO;
	}
	
	
	@Override
	public List<ItineraryCommentVO> getAll() {
		List<ItineraryCommentVO> list = new ArrayList<ItineraryCommentVO>();
		ItineraryCommentVO itineraryCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
		}  catch (SQLException se) {
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
	
	public static void main(String[] args) {

		ItineraryCommentDAO dao = new ItineraryCommentDAO();

		// 查詢all
		List<ItineraryCommentVO> list = dao.getAll();
		for (ItineraryCommentVO a : list) {
			System.out.print(a.getItinerary_comment_id() + ",");
			System.out.print(a.getItinerary_type_id() + ",");
			System.out.print(a.getMember_id() + ",");
			System.out.print(a.getItinerary_comment_post() + ",");
			System.out.print(a.getItinerary_comment_star() + ",");
			System.out.print(a.getItinerary_comment_time() );

			System.out.println();

		}
		
		//新增
		ItineraryCommentVO itineraryCommentVO1 = new ItineraryCommentVO();
		itineraryCommentVO1.setItinerary_type_id(5);
		itineraryCommentVO1.setMember_id(5);
		itineraryCommentVO1.setItinerary_comment_post("123天氣好");
		itineraryCommentVO1.setItinerary_comment_star(5);
		dao.insert(itineraryCommentVO1);
		
		//修改
		ItineraryCommentVO itineraryCommentVO2 = new ItineraryCommentVO();		
		itineraryCommentVO2.setItinerary_comment_id(5);
		itineraryCommentVO2.setItinerary_type_id(5);
		itineraryCommentVO2.setMember_id(5);
		itineraryCommentVO2.setItinerary_comment_post("有夠好玩");
		itineraryCommentVO2.setItinerary_comment_star(5);
		dao.update(itineraryCommentVO2);		
		
		ItineraryCommentVO itineraryCommentVO3 =dao.findByPrimaryKey(5);
		System.out.print(itineraryCommentVO3.getItinerary_comment_id() + ",");
		System.out.print(itineraryCommentVO3.getItinerary_type_id() + ",");
		System.out.print(itineraryCommentVO3.getMember_id() + ",");
		System.out.print(itineraryCommentVO3.getItinerary_comment_post() + ",");
		System.out.print(itineraryCommentVO3.getItinerary_comment_star() + ",");
		System.out.print(itineraryCommentVO3.getItinerary_comment_time() );

		System.out.println("------------------------------");
		
	}
	
}