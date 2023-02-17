package com.func.model;

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

import com.emp.model.EmpVO;

public class FuncDAO implements Func_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "insert into FUNC(FUNC_NAME)\r\n" + "value(?)";
	private static final String GET_ALL_STMT = "select FUNC_ID,FUNC_NAME from FUNC order by FUNC_ID";
	private static final String GET_ONE_STMT = "select * from time_to_travel.func where FUNC_ID = ?";
	
	@Override
	public void insert(FuncVO funcVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, funcVO.getFunc_name());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); 

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
	public List<FuncVO> getAll() {
		/* Default */
		List<FuncVO> list = new ArrayList<FuncVO>(); // 需要new list物件
		FuncVO funcVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select 獨有->resultset(因為要抓值)
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();// 這邊利用resultset去抓集合
			/* Get value from result set */
			while (rs.next()) {
				funcVo = new FuncVO();
				funcVo.setFunc_id(rs.getInt("FUNC_ID"));
				funcVo.setFunc_name(rs.getString("FUNC_NAME"));
				list.add(funcVo);
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
	public FuncVO findByPrimaryKey(Integer func_id) {
		
		FuncVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, func_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				funcVO = new FuncVO();
				funcVO.setFunc_id(rs.getInt("FUNC_ID"));
				funcVO.setFunc_name(rs.getString("FUNC_NAME"));
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
		return funcVO;
	}

}
