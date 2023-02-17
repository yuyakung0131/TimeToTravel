package com.comment.model;

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

import com.article.model.ArticleVO;

public class CommentDAO implements CommentDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO COMMENT (ARTICLE_ID, MEMBER_ID, COMMENT_CONTENT) VALUES (?, ?, ?) ";
	private static final String UPDATE = "update comment set comment_content=?  where comment_id=?  ";
	private static final String GET_BY_ARTICLEID = "SELECT * FROM COMMENT where article_id=? order by comment_time desc ";
	private static final String GET_BY_COMMENTID = "SELECT * FROM COMMENT where comment_id=? ";
	private static final String GET_COMMENT_BY_ID ="select COMMENT_CONTENT,ARTICLE_ID from COMMENT where COMMENT_ID=?";
	private static final String DELETE = "delete from comment where comment_id=? ";

	@Override
	public void insert(CommentVO commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, commentVO.getArticle_id());
			pstmt.setInt(2, commentVO.getMember_id());
			pstmt.setString(3, commentVO.getComment_content());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!");

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

	public void update(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, commentVO.getComment_content());
			pstmt.setInt(2, commentVO.getComment_id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!");

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

	public void delete(Integer comment_id) {

	}
	
	
	public CommentVO getByCommentId(Integer comment_id) {
		CommentVO commentVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_BY_COMMENTID);
			pstmt.setInt(1, comment_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				commentVO=new CommentVO();
				commentVO.setArticle_id(rs.getInt("article_id"));
				commentVO.setMember_id(rs.getInt("member_id"));
				commentVO.setComment_id(rs.getInt("comment_id"));
				commentVO.setComment_content(rs.getString("comment_content"));
				commentVO.setComment_state(rs.getByte("comment_state"));
				commentVO.setComment_time(rs.getTimestamp("comment_time"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return commentVO;
	}

	@Override
	public List<CommentVO> getByArticleId(Integer article_id) {

		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ARTICLEID);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				commentVO = new CommentVO();
				commentVO.setComment_id(rs.getInt("COMMENT_ID"));
				commentVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				commentVO.setMember_id(rs.getInt("MEMBER_ID"));
				commentVO.setComment_content(rs.getString("COMMENT_CONTENT"));
				commentVO.setComment_time(rs.getTimestamp("COMMENT_TIME"));
				commentVO.setComment_state(rs.getByte("COMMENT_STATE"));
				list.add(commentVO); // Store the row in the list
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
	/*get content by id*/

	@Override
	public CommentVO getCommentByID(Integer comment_id) {
		CommentVO commentVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_COMMENT_BY_ID);
			pstmt.setInt(1, comment_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				commentVO=new CommentVO();
				commentVO.setComment_content(rs.getString("COMMENT_CONTENT"));	
				commentVO.setArticle_id(rs.getInt("ARTICLE_ID"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return commentVO;
	}
	

}
