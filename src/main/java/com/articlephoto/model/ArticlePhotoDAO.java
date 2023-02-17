package com.articlephoto.model;

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



public class ArticlePhotoDAO implements ArticlePhotoDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Pei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO ARTICLE_PHOTO (ARTICLE_ID,article_pic) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ARTICLE_PHOTO order by ARTICLE_PIC_ID";
	private static final String GET_BY_ARTICLEID="select * from article_photo where article_id=? order by article_pic_id ";
	private static final String GET_BY_PICID="select*from article_photo where article_pic_id=? ";
	private static final String UPDATE="update article_photo set article_pic=? where article_pic_id=? ";
	private static final String DELETE_BY_PICID="delete from article_photo where article_pic_id=? ";
	private static final String DELETE_BY_ARTICLEID="delete from article_photo where article_id=? ";
	
	
	@Override
	public void insert(ArticlePhotoVO articlephotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articlephotoVO.getArticle_id());
			pstmt.setBytes(2, articlephotoVO.getArticle_pic());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!");

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
	
	public void insertByArticle(ArticlePhotoVO articlephotoVO,Connection con) {
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articlephotoVO.getArticle_id());
			pstmt.setBytes(2, articlephotoVO.getArticle_pic());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!");

			// Handle any driver errors
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

	public void update(ArticlePhotoVO articlePhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con =ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, articlePhotoVO.getArticle_pic());
			pstmt.setInt(2,articlePhotoVO.getArticle_pic_id() );

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) updated!!");

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
	
	public void deleteByPicId(Integer article_pic_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_PICID);

			pstmt.setInt(1, article_pic_id);

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) deleted!!");

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
	
	
	public void deleteByArticleId(Integer article_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_ARTICLEID);
			
			pstmt.setInt(1, article_id);
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) deleted!!");
			
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
	
	public ArticlePhotoVO getByPicId(Integer article_pic_id) {
		ArticlePhotoVO articlephotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PICID);
			pstmt.setInt(1, article_pic_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlephotoVO = new ArticlePhotoVO();
				articlephotoVO.setArticle_pic_id(rs.getInt("ARTICLE_PIC_ID"));
				articlephotoVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articlephotoVO.setArticle_pic(rs.getBytes("ARTICLE_PIC"));
			}

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
		return articlephotoVO;
	}
	
	
	public List<ArticlePhotoVO> getByArticleId(Integer article_id){
		List<ArticlePhotoVO> list = new ArrayList<ArticlePhotoVO>();
		ArticlePhotoVO articlephotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ARTICLEID);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlephotoVO = new ArticlePhotoVO();
				articlephotoVO.setArticle_pic_id(rs.getInt("ARTICLE_PIC_ID"));
				articlephotoVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articlephotoVO.setArticle_pic(rs.getBytes("ARTICLE_PIC"));
				list.add(articlephotoVO); // Store the row in the list
			}

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
	public List<ArticlePhotoVO> getAll() {
		List<ArticlePhotoVO> list = new ArrayList<ArticlePhotoVO>();
		ArticlePhotoVO articlephotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlephotoVO = new ArticlePhotoVO();
				articlephotoVO.setArticle_pic_id(rs.getInt("ARTICLE_PIC_ID"));
				articlephotoVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articlephotoVO.setArticle_pic(rs.getBytes("ARTICLE_PIC"));
				list.add(articlephotoVO); // Store the row in the list
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
	
}
