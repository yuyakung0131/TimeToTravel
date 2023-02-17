package com.empfunc.model;

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

public class EmpFuncDAO implements EmpFunc_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static final String INSERT_STMT = "insert into time_to_travel.EMP_FUNC(EMP_ID,FUNC_ID) values(?,?)";
	public static final String GET_ALL_STMT = "select * from time_to_travel.EMP_FUNC order by EMP_ID ";
	public static final String GET_BY_EMPID = "select * from time_to_travel.EMP_FUNC where EMP_ID = ? order by FUNC_ID";
	public static final String GET_BY_FUNCID = "select * from time_to_travel.EMP_FUNC where FUNC_ID = ? order by EMP_ID";
	public static final String UPDATE = "update time_to_travel.EMP_FUNC set FUNC_ID = ? where (EMP_ID = ?) and (FUNC_ID = ?)";
	public static final String DELETE = "delete from time_to_travel.EMP_FUNC where (EMP_ID = ?) and (FUNC_ID = ?)";
	
	@Override
	public void insert(EmpFuncVO empFuncVO) {
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, empFuncVO.getEmp_id());
			pstmt.setInt(2, empFuncVO.getFunc_id());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<EmpFuncVO> getAll() {
		List<EmpFuncVO> list = new ArrayList<EmpFuncVO>();
		EmpFuncVO empFuncVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empFuncVO = new EmpFuncVO();
				empFuncVO.setEmp_id(rs.getInt("EMP_ID"));
				empFuncVO.setFunc_id(rs.getInt("FUNC_ID"));
				list.add(empFuncVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(EmpFuncVO empFuncVO, Integer func_id) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, func_id);
			pstmt.setInt(2, empFuncVO.getEmp_id());
			pstmt.setInt(3, empFuncVO.getFunc_id());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) inserted!!"); 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(EmpFuncVO empFuncVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empFuncVO.getEmp_id());
			pstmt.setInt(2, empFuncVO.getFunc_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<EmpFuncVO> findByEmpID(Integer emp_id) {
		List<EmpFuncVO> list = new ArrayList<EmpFuncVO>();
		EmpFuncVO empFuncVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_EMPID);
			pstmt.setInt(1, emp_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empFuncVO = new EmpFuncVO();
				empFuncVO.setEmp_id(rs.getInt("EMP_ID"));
				empFuncVO.setFunc_id(rs.getInt("FUNC_ID"));
				list.add(empFuncVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<EmpFuncVO> findByFuncID(Integer func_id) {
		List<EmpFuncVO> list = new ArrayList<EmpFuncVO>();
		EmpFuncVO empFuncVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_FUNCID);
			pstmt.setInt(1, func_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empFuncVO = new EmpFuncVO();
				empFuncVO.setEmp_id(rs.getInt("EMP_ID"));
				empFuncVO.setFunc_id(rs.getInt("FUNC_ID"));
				list.add(empFuncVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
