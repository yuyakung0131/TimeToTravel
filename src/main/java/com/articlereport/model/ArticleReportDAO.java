package com.articlereport.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArticleReportDAO implements ArticleReportDAO_interface {
	/* Prepare datasource */
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	/* Prepare SQL Statement */
	private static final String INSERT_STMT = "insert into ARTICLE_REPORT(ARTICLE_ID,MEMBER_ID,ARTICLE_REPORT_REASON,ARTICLE_REPORTPROCESS_STATE)\r\n"
			+ "values(?,?,?,?)";
	private static final String UPDATE_STMT = "update ARTICLE_REPORT set  ARTICLE_REPORTPROCESS_STATE=?, ARTICLE_REPORTPROCESS_CONTENT=?,ARTICLE_REPORTPROCESS_TIME=?,EMP_ID=?\r\n"
			+ "where ARTICLE_ID =?";
	private static final String GET_ALL_STMT = "select*from ARTICLE_REPORT  order by ARTICLE_ID";
	private static final String FINDBYID_STMT = "select*from ARTICLE_REPORT where ARTICLE_REPORT_ID =?";
	private static final String FINDSTATEBYID_STMT = "select ARTICLE_REPORTPROCESS_STATE from ARTICLE_REPORT where ARTICLE_ID = ? limit 1";

	/* Override interface method */
	@Override
	/* insert article report front */
	public void insert(ArticleReportVO articleReportVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setInt(1, articleReportVO.getArticle_id());
			pstmt.setInt(2, articleReportVO.getMember_id());
			pstmt.setByte(3, articleReportVO.getArticle_report_reason());
			pstmt.setByte(4, articleReportVO.getArticle_reportprocess_state());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); // mean insert success
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
	/* update article report back */
	public void update(ArticleReportVO articleReportVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			/* Insert value into database */
			pstmt.setByte(1, articleReportVO.getArticle_reportprocess_state());
			pstmt.setString(2, articleReportVO.getArticle_reportprocess_content());
			pstmt.setTimestamp(3, articleReportVO.getArticle_reportprocess_time());
			pstmt.setInt(4, articleReportVO.getEmp_id());
			pstmt.setInt(5, articleReportVO.getArticle_id());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) is updated!!"); // mean insert success
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
	/* get all article report back */
	public List<ArticleReportVO> getALL() {
		/* Default */
		List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
		ArticleReportVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				Integer emp_id = rs.getInt("EMP_ID");
				articleReportVO = new ArticleReportVO();
				articleReportVO.setArticle_report_id(rs.getInt("ARTICLE_REPORT_ID"));
				articleReportVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articleReportVO.setMember_id(rs.getInt("MEMBER_ID"));
				/* emp_id judgement */
				if (emp_id == 0) {
					emp_id = null;
				}
				articleReportVO.setEmp_id(emp_id);
				articleReportVO.setArticle_report_reason(rs.getByte("ARTICLE_REPORT_REASON"));
				articleReportVO.setArticle_report_time(rs.getTimestamp("ARTICLE_REPORT_TIME"));
				articleReportVO.setArticle_reportprocess_time(rs.getTimestamp("ARTICLE_REPORTPROCESS_TIME"));
				articleReportVO.setArticle_reportprocess_state(rs.getByte("ARTICLE_REPORTPROCESS_STATE"));
				articleReportVO.setArticle_reportprocess_content(rs.getString("ARTICLE_REPORTPROCESS_CONTENT"));
				list.add(articleReportVO);
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

	/* get one article report back */
	@Override
	public ArticleReportVO findByID(Integer article_report_id) {
		/* Default */
		ArticleReportVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYID_STMT);
			pstmt.setInt(1, article_report_id);

			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				Integer emp_id = rs.getInt("EMP_ID");
				articleReportVO = new ArticleReportVO();
				articleReportVO.setArticle_report_id(rs.getInt("ARTICLE_REPORT_ID"));
				articleReportVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articleReportVO.setMember_id(rs.getInt("MEMBER_ID"));
				/* emp_id judgement */
				if (emp_id == 0) {
					emp_id = null;
				}
				articleReportVO.setEmp_id(emp_id);
				articleReportVO.setArticle_report_reason(rs.getByte("ARTICLE_REPORT_REASON"));
				articleReportVO.setArticle_report_time(rs.getTimestamp("ARTICLE_REPORT_TIME"));
				articleReportVO.setArticle_reportprocess_time(rs.getTimestamp("ARTICLE_REPORTPROCESS_TIME"));
				articleReportVO.setArticle_reportprocess_state(rs.getByte("ARTICLE_REPORTPROCESS_STATE"));
				articleReportVO.setArticle_reportprocess_content(rs.getString("ARTICLE_REPORTPROCESS_CONTENT"));
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
		return articleReportVO;
	}

	/* get article report process for articleVO->remember write service */
	@Override
	public ArticleReportVO findStateByID(Integer article_id) {
		// TODO Auto-generated method stub
		/* Default */
		ArticleReportVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDSTATEBYID_STMT);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				articleReportVO = new ArticleReportVO();
				articleReportVO.setArticle_reportprocess_state(rs.getByte("ARTICLE_REPORTPROCESS_STATE"));
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
		return articleReportVO;
	}

}
