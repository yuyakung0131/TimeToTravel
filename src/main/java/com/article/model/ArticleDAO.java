package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.articlephoto.model.ArticlePhotoDAO;
import com.articlephoto.model.ArticlePhotoVO;



public class ArticleDAO implements ArticleDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO ARTICLE (MEMBER_ID, ARTICLE_TITLE, ARTICLE_CONTENT) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ARTICLE order by  article_time  desc";
	
	private static final String GET_ARTICLEID_STMT="select * from article where article_id=?";
	
	private static final String GET_MEMBER_STMT="select * from article where member_id=?";
	
	private static final String GET_ARTICLETITLE_STMT="select * from article where article_title like \"%\"?\"%\" order by article_time desc";
	
	private static final String GET_ARTICLECONTENT_STMT="select * from article where article_content like \"%\"?\"%\" order by article_time desc";
	
	private static final String UPDATE="update article set article_title=? ,article_content=?  where article_id=? ";
	
	private static final String DELETE="delete from article where article_id=?";
	
	@Override
	public void insert(ArticleVO articleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getMember_id());
			pstmt.setString(2, articleVO.getArticle_title());
			pstmt.setString(3, articleVO.getArticle_content());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!");

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
	
	public void insertWithPhoto(ArticleVO articleVO , ArticlePhotoVO articlePhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[]= {"ARTICLE_ID"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);

			pstmt.setInt(1, articleVO.getMember_id());
			pstmt.setString(2, articleVO.getArticle_title());
			pstmt.setString(3, articleVO.getArticle_content());
			pstmt.executeUpdate();
			Integer next_article_id=null;
			ResultSet rs=pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_article_id=rs.getInt(1);
			}
			rs.close();
			
			ArticlePhotoDAO dao=new ArticlePhotoDAO();
			
				articlePhotoVO.setArticle_id(new Integer(next_article_id));
				dao.insertByArticle(articlePhotoVO, con);
			
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3��身摰���xception�����atch��憛
					System.err.print("Transaction is being ");
					System.err.println("rolled back-�-dept");
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
	
	
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, articleVO.getArticle_title());
			pstmt.setString(2, articleVO.getArticle_content());
			pstmt.setInt(3, articleVO.getArticle_id());

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
	
	public void delete(Integer article_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_id);
	

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
	
	public ArticleVO getArticleId(Integer article_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleVO articleVO=null;
		ResultSet rs=null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ARTICLEID_STMT);

			pstmt.setInt(1, article_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				articleVO=new ArticleVO();
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_time(rs.getTimestamp("article_time"));
				
			}
		

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
		return articleVO;
	}
	
	
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			//嚙瞇嚙踝蕭w嚙踝蕭鴘綽蕭嚙複，嚙編嚙踝蕭嚙踝蕭嚙踝蕭A嚙璀嚙瞇嚙踝蕭嚙踝蕭嚙皚嚙踝蕭嚙碼嚙踝蕭嚙踝蕭
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articleVO.setMember_id(rs.getInt("MEMBER_ID"));
				articleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				articleVO.setArticle_content(rs.getString("ARTICLE_CONTENT"));
				articleVO.setArticle_time(rs.getTimestamp("ARTICLE_TIME"));
				articleVO.setArticle_state(rs.getByte("ARTICLE_STATE"));
				list.add(articleVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	public List<ArticleVO> getAllMember(Integer member_id){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_STMT);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			
			//嚙瞇嚙踝蕭w嚙踝蕭鴘綽蕭嚙複，嚙編嚙踝蕭嚙踝蕭嚙踝蕭A嚙璀嚙瞇嚙踝蕭嚙踝蕭嚙皚嚙踝蕭嚙碼嚙踝蕭嚙踝蕭
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articleVO.setMember_id(rs.getInt("MEMBER_ID"));
				articleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				articleVO.setArticle_content(rs.getString("ARTICLE_CONTENT"));
				articleVO.setArticle_time(rs.getTimestamp("ARTICLE_TIME"));
				articleVO.setArticle_state(rs.getByte("ARTICLE_STATE"));
				list.add(articleVO); // Store the row in the list
			}

			// Handle any driver errors
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
		
	public List<ArticleVO> getArticleTitle(String article_title) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ARTICLETITLE_STMT);
			pstmt.setString(1, article_title);
			rs = pstmt.executeQuery();
			
			//嚙瞇嚙踝蕭w嚙踝蕭鴘綽蕭嚙複，嚙編嚙踝蕭嚙踝蕭嚙踝蕭A嚙璀嚙瞇嚙踝蕭嚙踝蕭嚙皚嚙踝蕭嚙碼嚙踝蕭嚙踝蕭
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articleVO.setMember_id(rs.getInt("MEMBER_ID"));
				articleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				articleVO.setArticle_content(rs.getString("ARTICLE_CONTENT"));
				articleVO.setArticle_time(rs.getTimestamp("ARTICLE_TIME"));
				articleVO.setArticle_state(rs.getByte("ARTICLE_STATE"));
				list.add(articleVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	public List<ArticleVO> getArticleContent(String article_content) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ARTICLECONTENT_STMT);
			pstmt.setString(1, article_content);
			rs = pstmt.executeQuery();
			
			//嚙瞇嚙踝蕭w嚙踝蕭鴘綽蕭嚙複，嚙編嚙踝蕭嚙踝蕭嚙踝蕭A嚙璀嚙瞇嚙踝蕭嚙踝蕭嚙皚嚙踝蕭嚙碼嚙踝蕭嚙踝蕭
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("ARTICLE_ID"));
				articleVO.setMember_id(rs.getInt("MEMBER_ID"));
				articleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				articleVO.setArticle_content(rs.getString("ARTICLE_CONTENT"));
				articleVO.setArticle_time(rs.getTimestamp("ARTICLE_TIME"));
				articleVO.setArticle_state(rs.getByte("ARTICLE_STATE"));
				list.add(articleVO); // Store the row in the list
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


	public static void main(String[] args) {
		ArticleDAO dao = new ArticleDAO();
//		
//		//�憓�
//		ArticleVO articleVO1 = new ArticleVO();
//		articleVO1.setMember_id(2);
//		articleVO1.setArticle_title("������22222");
//		articleVO1.setArticle_content("���摰�22222");
//		dao.insert(articleVO1);
		
		
		//靽格
//		ArticleVO articleVO =new ArticleVO();
//		articleVO.setArticle_title("����123456");
//		articleVO.setArticle_content("���摰�123456");
//		articleVO.setArticle_id(2);
//		dao.update(articleVO);
		
		//��(撖血�������������靘����)
//		dao.delete(1);
		
		//����楊���
//		ArticleVO articleVO=dao.getArticleId(2);
//		System.out.println(articleVO.getArticle_id());
//		System.out.println(articleVO.getArticle_title());
//		System.out.println(articleVO.getArticle_content());
//		System.out.println(articleVO.getArticle_time());
		
		
		
//		//���
//		List<ArticleVO> list = dao.getAll();
//		for (ArticleVO xArticle : list) {
//			System.out.print(xArticle.getArticle_id() + ",");
//			System.out.print(xArticle.getMember_id() + ",");
//			System.out.print(xArticle.getArticle_title() + ",");
//			System.out.print(xArticle.getArticle_content() + ",");
//			System.out.print(xArticle.getArticle_time().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+",");
//			System.out.print(xArticle.getArticle_state());
//			System.out.println();
//		}
		
		//���蝺刻��
//		List<ArticleVO> list =dao.getAllMember(2);
//		for (ArticleVO xArticle : list) {
//			System.out.print(xArticle.getArticle_id() + ",");
//			System.out.print(xArticle.getMember_id() + ",");
//			System.out.print(xArticle.getArticle_title() + ",");
//			System.out.print(xArticle.getArticle_content() + ",");
//			System.out.print(xArticle.getArticle_time().toLocalDateTime() + ",");
//			System.out.print(xArticle.getArticle_state());
//			System.out.println();
//		}
		
		
		//�������
//		List<ArticleVO> list = dao.getArticleTitle("璅��");
//		for (ArticleVO xArticle : list) {
//			System.out.print(xArticle.getArticle_id() + ",");
//			System.out.print(xArticle.getMember_id() + ",");
//			System.out.print(xArticle.getArticle_title() + ",");
//			System.out.print(xArticle.getArticle_content() + ",");
//			System.out.print(xArticle.getArticle_time().toLocalDateTime() + ",");
//			System.out.print(xArticle.getArticle_state());
//			System.out.println();
//		}
		
		//����摰�
//		List<ArticleVO> list = dao.getArticleContent("�摰�");
//		for (ArticleVO xArticle : list) {
//			System.out.print(xArticle.getArticle_id() + ",");
//			System.out.print(xArticle.getMember_id() + ",");
//			System.out.print(xArticle.getArticle_title() + ",");
//			System.out.print(xArticle.getArticle_content() + ",");
//			System.out.print(xArticle.getArticle_time().toLocalDateTime() + ",");
//			System.out.print(xArticle.getArticle_state());
//			System.out.println();
//		}
		
	}
	












}
