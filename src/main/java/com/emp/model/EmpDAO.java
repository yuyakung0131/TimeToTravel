package com.emp.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;

public class EmpDAO implements EmpDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/time_to_travel?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "ggYY8877";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into time_to_travel.EMP(EMP_ACCOUNT,EMP_PWD,EMP_NAME,EMP_NAMEENG,EMP_IMG,EMP_STATE,EMP_DATE)\r\n"
			+ "values(?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select EMP_ID,EMP_ACCOUNT,EMP_PWD,EMP_NAME,\r\n"
			+ "EMP_NAMEENG,EMP_IMG,EMP_STATE,EMP_DATE from time_to_travel.EMP order by EMP_ID;";
	private static final String GET_ONE_STMT = "select EMP_ID,EMP_ACCOUNT,EMP_PWD,EMP_NAME,\r\n"
			+ "EMP_NAMEENG,EMP_IMG,EMP_STATE,EMP_DATE from time_to_travel.EMP where EMP_ID = ?;";
	private static final String UPDATE = "update time_to_travel.EMP set EMP_ACCOUNT=?, EMP_PWD=?, EMP_NAME=?, EMP_NAMEENG=?, EMP_IMG=?, EMP_STATE=?, EMP_DATE=? where EMP_ID = ?";
	private static final String GET_ONE_BYACCOUNT_STMT = "select * from time_to_travel.EMP where EMP_ACCOUNT = ?";
	
	@Override
	public void insert(EmpVO empVO) {
		
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setString(1, empVO.getEmp_account());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setString(4, empVO.getEmp_nameeng());
			pstmt.setBytes(5, empVO.getEmp_img());
			pstmt.setByte(6, empVO.getEmp_state());
			pstmt.setDate(7, empVO.getEmp_date());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); // mean insert success
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {		// Java 7 之後finally的部分不寫，也會自動關閉連線。
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
	public List<EmpVO> getAll() {
		/* Default */
		List<EmpVO> list = new ArrayList<EmpVO>(); // 需要new list物件
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select 獨有->resultset(因為要抓值)
		try {
			/* Start connection */
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();// 這邊利用resultset去抓集合
			/* Get value from result set */
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("EMP_ID"));
				empVO.setEmp_account(rs.getString("EMP_ACCOUNT"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_nameeng(rs.getString("EMP_NAMEENG"));
				empVO.setEmp_img(rs.getBytes("EMP_IMG"));
				empVO.setEmp_state(rs.getByte("EMP_STATE"));
				empVO.setEmp_date(rs.getDate("EMP_DATE"));
				list.add(empVO);
			}
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
		return list;
	}

	@Override
	public EmpVO findByPrimaryKey(Integer emp_id) {
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("EMP_ID"));
				empVO.setEmp_account(rs.getString("EMP_ACCOUNT"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_nameeng(rs.getString("EMP_NAMEENG"));
				empVO.setEmp_img(rs.getBytes("EMP_IMG"));
				empVO.setEmp_state(rs.getByte("EMP_STATE"));
				empVO.setEmp_date(rs.getDate("EMP_DATE"));
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
		return empVO;
	}

	@Override
	public void update(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_account());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setString(4, empVO.getEmp_nameeng());
			pstmt.setBytes(5, empVO.getEmp_img());
			pstmt.setByte(6, empVO.getEmp_state());
			pstmt.setDate(7, empVO.getEmp_date());
			pstmt.setInt(8, empVO.getEmp_id());

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
	public EmpVO findByAccount(String emp_account) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYACCOUNT_STMT);

			pstmt.setString(1, emp_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("EMP_ID"));
				empVO.setEmp_account(rs.getString("EMP_ACCOUNT"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_nameeng(rs.getString("EMP_NAMEENG"));
				empVO.setEmp_img(rs.getBytes("EMP_IMG"));
				empVO.setEmp_state(rs.getByte("EMP_STATE"));
				empVO.setEmp_date(rs.getDate("EMP_DATE"));
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
		return empVO;
	}
	
	
}


