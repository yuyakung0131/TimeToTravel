package com.commentreport.model;

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

import com.articlereport.model.ArticleReportVO;

public class CommentReportDAO implements CommentReportDAO_interface {
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
	private static final String GET_ALL_STMT = "select*from COMMENT_REPORT";
	private static final String FINDBYID_STMT ="select*from COMMENT_REPORT where COMMENT_REPORT_ID=?";
	private static final String UPDATE_STMT = "update COMMENT_REPORT set  COMMENT_REPORTPROCESS_STATE=?, COMMENT_REPORTPROCESS_CONTENT=?,COMMENT_REPORTPROCESS_TIME=?,EMP_ID=?\r\n"
			+ "where COMMENT_ID =?";
	private static final String FINDSTATEBYID_STMT ="select COMMENT_REPORTPROCESS_STATE from COMMENT_REPORT where COMMENT_ID=? limit 1 ";
	private static final String INSERT_STMT = "insert into COMMENT_REPORT(COMMENT_ID,MEMBER_ID,COMMENT_REPORT_REASON,COMMENT_REPORTPROCESS_STATE)\r\n"
			+ "values(?,?,?,?)";
	/* Override interface method */
	@Override
	/* get all article report back */
	public List<CommentReportVO> getALL() {
		/* Default */
		List<CommentReportVO> list = new ArrayList<CommentReportVO>();
		CommentReportVO commentReportVO = null;
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
				commentReportVO = new CommentReportVO();
				commentReportVO.setComment_report_id(rs.getInt("COMMENT_REPORT_ID"));
				commentReportVO.setComment_id(rs.getInt("COMMENT_ID"));
				commentReportVO.setMember_id(rs.getInt("MEMBER_ID"));
				/* emp_id judgement */
				if (emp_id == 0) {
					emp_id = null;
				}
				commentReportVO.setEmp_id(emp_id);
				commentReportVO.setComment_report_reason(rs.getByte("COMMENT_REPORT_REASON"));
				commentReportVO.setComment_report_time(rs.getTimestamp("COMMENT_REPORT_TIME"));
				commentReportVO.setComment_reportprocess_time(rs.getTimestamp("COMMENT_REPORTPROCESS_TIME"));
				commentReportVO.setComment_reportprocess_state(rs.getByte("COMMENT_REPORTPROCESS_STATE"));
				commentReportVO.setComment_reportprocess_content(rs.getString("COMMENT_REPORTPROCESS_CONTENT"));
				list.add(commentReportVO);
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
	/* get one comment report back */
	public CommentReportVO findBYID(Integer comment_report_id) {
		/* Default */
		CommentReportVO commentReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYID_STMT);
			pstmt.setInt(1, comment_report_id);

			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				Integer emp_id = rs.getInt("EMP_ID");
				commentReportVO = new CommentReportVO();
				commentReportVO.setComment_report_id(rs.getInt("COMMENT_REPORT_ID"));
				commentReportVO.setComment_id(rs.getInt("COMMENT_ID"));
				commentReportVO.setMember_id(rs.getInt("MEMBER_ID"));
				/* emp_id judgement */
				if (emp_id == 0) {
					emp_id = null;
				}
				commentReportVO.setEmp_id(emp_id);
				commentReportVO.setComment_report_reason(rs.getByte("COMMENT_REPORT_REASON"));
				commentReportVO.setComment_report_time(rs.getTimestamp("COMMENT_REPORT_TIME"));
				commentReportVO.setComment_reportprocess_time(rs.getTimestamp("COMMENT_REPORTPROCESS_TIME"));
				commentReportVO.setComment_reportprocess_state(rs.getByte("COMMENT_REPORTPROCESS_STATE"));
				commentReportVO.setComment_reportprocess_content(rs.getString("COMMENT_REPORTPROCESS_CONTENT"));
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
		return commentReportVO;
	}

	@Override
	/* update comment  report back base on comment_id */
	public void update(CommentReportVO commentReportVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			/* Insert value into database */
			pstmt.setByte(1, commentReportVO.getComment_reportprocess_state());
			pstmt.setString(2, commentReportVO.getComment_reportprocess_content());
			pstmt.setTimestamp(3, commentReportVO.getComment_reportprocess_time());
			pstmt.setInt(4, commentReportVO.getEmp_id());
			pstmt.setInt(5, commentReportVO.getComment_id());
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
	/* get article report process for articleVO->remember write service */
	public CommentReportVO findStateByID(Integer comment_id) {
		// TODO Auto-generated method stub
		/* Default */
		CommentReportVO commentReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDSTATEBYID_STMT);
			pstmt.setInt(1, comment_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				commentReportVO = new CommentReportVO();
				commentReportVO.setComment_reportprocess_state(rs.getByte("COMMENT_REPORTPROCESS_STATE"));
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
		return commentReportVO;
	}

	@Override
	/* insert comment report */
	public void insert(CommentReportVO commentReportVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setInt(1, commentReportVO.getComment_id());
			pstmt.setInt(2, commentReportVO.getMember_id());
			pstmt.setByte(3, commentReportVO.getComment_report_reason());
			pstmt.setByte(4, commentReportVO.getComment_reportprocess_state());
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
	
	
	
	
	
}
