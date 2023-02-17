package com.firmtype.model;

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

public class FirmTypeDAO implements FirmType_interface {
	
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
	public static final String INSERT_STMT = "insert into FIRM_TYPE(FIRMTYPE_NAME)\r\n" + "value(?)";
	public static final String GET_ALL_STMT = "select FIRMTYPE_ID,FIRMTYPE_NAME from FIRM_TYPE order by FIRMTYPE_ID;";
	public static final String GET_ONE = "select * from FIRM_TYPE where FIRMTYPE_ID = ?";
	
	@Override
	public void insert(FirmTypeVO firmTypeVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setString(1, firmTypeVO.getFirmtype_name());
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
	public List<FirmTypeVO> getAll() {

		List<FirmTypeVO> list = new ArrayList<FirmTypeVO>(); 
		FirmTypeVO firmTypeVO = null;
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
				firmTypeVO = new FirmTypeVO();
				firmTypeVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmTypeVO.setFirmtype_name(rs.getString("FIRMTYPE_NAME"));
				list.add(firmTypeVO);
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
	public FirmTypeVO findByPrimaryKey(Integer FIRMTYPE_ID) {
		
		FirmTypeVO firmTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, FIRMTYPE_ID); 
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmTypeVO = new FirmTypeVO();
				firmTypeVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmTypeVO.setFirmtype_name(rs.getString("FIRMTYPE_NAME"));
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
		return firmTypeVO;
	}
}

