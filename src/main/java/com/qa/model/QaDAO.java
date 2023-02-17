package com.qa.model;

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

public class QaDAO implements QaDAO_interface{
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into QA(QA_SHOW_NO,QUESTION,ANSWER,QA_STATE,QA_CLASS_ID) values(?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from QA order by QA_NO";
	private static final String GET_ONE_STMT = "select * from QA where QA_NO = ?";
	private static final String DELETE = "delete from QA where QA_NO = ?";
	private static final String UPDATE = 
			"update QA set QA_SHOW_NO=?,QUESTION=?,ANSWER=?,QA_STATE=?,QA_CLASS_ID=? where QA_NO = ?";
	private static final String GET_ALLTYPE_STMT="select * from QA where QA_CLASS_ID=?";
	private static final String GET_STATE_STMT="select * from QA where QA_STATE=?";
	
	
	@Override
	public void insert(QaVO qavo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, qavo.getQa_show_no());
			pstmt.setString(2, qavo.getQuestion());
			pstmt.setString(3, qavo.getAnswer());
			pstmt.setByte(4, qavo.getQa_state());
			pstmt.setInt(5,qavo.getQa_class_id());
			
		
	
			pstmt.executeUpdate();

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
	
	
	@Override
	public void update(QaVO qavo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, qavo.getQa_show_no());
			pstmt.setString(2, qavo.getQuestion());
			pstmt.setString(3, qavo.getAnswer());
			pstmt.setByte(4, qavo.getQa_state());
			pstmt.setInt(5, qavo.getQa_class_id());
			pstmt.setInt(6, qavo.getQa_no());
			


			pstmt.executeUpdate();

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
	
	
	@Override
	public void delete(Integer qa_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, qa_no);


			pstmt.executeUpdate();

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
	
	@Override
	public QaVO findByPrimaryKey(Integer qa_no) {

		QaVO qavo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, qa_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				qavo = new QaVO();
				qavo.setQa_no(rs.getInt(1));
				qavo.setQa_show_no(rs.getInt(2));
				qavo.setQuestion(rs.getString(3));
				qavo.setAnswer(rs.getString(4));
				qavo.setQa_state(rs.getByte(5));
				qavo.setQa_class_id(rs.getInt(6));

		
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
		return qavo;
	}
	
	@Override
	public List<QaVO>getAll() {
		
		List<QaVO>list= new ArrayList<QaVO>();
		QaVO qavo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qavo = new QaVO();
				qavo.setQa_no(rs.getInt(1));
				qavo.setQa_show_no(rs.getInt(2));
				qavo.setQuestion(rs.getString(3));
				qavo.setAnswer(rs.getString(4));
				qavo.setQa_state(rs.getByte(5));
				qavo.setQa_class_id(rs.getInt(6));
				list.add(qavo);
		
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


	@Override
	public List<QaVO> findByState(Byte qa_state) {
		
		List<QaVO>list=new ArrayList<QaVO>();
		QaVO qavo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STATE_STMT);
			pstmt.setInt(1, qa_state);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qavo = new QaVO();
				qavo.setQa_no(rs.getInt(1));
				qavo.setQa_show_no(rs.getInt(2));
				qavo.setQuestion(rs.getString(3));
				qavo.setAnswer(rs.getString(4));
				qavo.setQa_state(rs.getByte(5));
				qavo.setQa_class_id(rs.getInt(6));
				list.add(qavo);
		
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


	@Override
	public List<QaVO> findByType(Integer qa_class_id) {
		List<QaVO>list=new ArrayList<QaVO>();
		QaVO qavo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLTYPE_STMT);
			pstmt.setInt(1, qa_class_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qavo = new QaVO();
				qavo.setQa_no(rs.getInt(1));
				qavo.setQa_show_no(rs.getInt(2));
				qavo.setQuestion(rs.getString(3));
				qavo.setAnswer(rs.getString(4));
				qavo.setQa_state(rs.getByte(5));
				qavo.setQa_class_id(rs.getInt(6));
				list.add(qavo);
		
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
}
	