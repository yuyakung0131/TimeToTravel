package com.news.model;

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

public class NewsDAO implements NewsDAO_interface {

	/* JDBC connection(預設為null) ->詳見jdbc講義 */
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
	/* Prepare SQL Statement */
	private static final String INSERT_STMT = "insert into NEWS(NEWS_TITLE,NEWS_CONTENT,news_pic)\r\n"
			+ "values(?,?,?)";
	private static final String GET_ALL_STMT = "select NEWS_NO,NEWS_TITLE,NEWS_CONTENT,NEWS_TIME,NEWS_PIC from NEWS";
//	GET_ALL_STMT 圖片NEWS_PIC
	private static final String GET_ONE_STMT = "SELECT NEWS_NO,NEWS_TITLE,NEWS_CONTENT,NEWS_TIME,news_pic FROM news where NEWS_NO = ?";
	// GET_ONE_STMT 要圖NEWS_PIC
	private static final String DELETE = "DELETE FROM news where NEWS_NO =?";
	private static final String UPDATE = "UPDATE news set NEWS_TITLE=?, NEWS_CONTENT=? ,NEWS_PIC=? where NEWS_NO=?";

//  UPDATE 要圖  NEWS_PIC=?
	@Override
	public void insert(NewsVO newsVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setString(1, newsVO.getNews_title());
			pstmt.setString(2, newsVO.getNews_content());
			pstmt.setBytes(3, newsVO.getNews_pic());
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); // mean insert success
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<NewsVO> getAll() {
		/* Default */
		List<NewsVO> list = new ArrayList<NewsVO>(); // 需要new list物件
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select 獨有->resultset(因為要抓值)
		try {
			/* Start connection */
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();// 這邊利用resultset去抓集合
			/* Get value from result set */
			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getInt("news_no"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsVO.setNews_time(rs.getTimestamp("news_time"));
				newsVO.setNews_pic(rs.getBytes("news_pic"));
				// 圖片未用好
				list.add(newsVO);
			}
		
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
	public void delete(Integer news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, news_no);

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
	public NewsVO findByPrimaryKey(Integer news_no) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, news_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// promotionVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getInt("news_no"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsVO.setNews_time(rs.getTimestamp("news_time"));
				newsVO.setNews_pic(rs.getBytes("news_pic"));

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
		return newsVO;
	}

	@Override
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsVO.getNews_title());
			pstmt.setString(2, newsVO.getNews_content());
			pstmt.setBytes(3, newsVO.getNews_pic());
			pstmt.setInt(4, newsVO.getNews_no());
			
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
}
