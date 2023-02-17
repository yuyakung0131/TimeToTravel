package com.firm.model;

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

public class FirmDAO implements Firm_interface {

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
	private static final String INSERT_STMT = "insert into time_to_travel.FIRM(FIRMTYPE_ID,FIRM_PRIM,FIRM_NAME,\r\n"
			+ "FIRM_REGIST_ADD,FIRM_OPERATE_ADD,FIRM_POC,FIRM_PHONE,FIRM_EMAIL,FIRM_REVIEW_PETITION)\r\n"
			+ "values(?,?,?,?,?,?,?,?,?)";

	private static final String GET_BY_TYPE = "select * from time_to_travel.FIRM where FIRMTYPE_ID = ?";

	private static final String GET_BY_NAME = "select * from time_to_travel.FIRM where FIRM_NAME = ?";

	private static final String GET_BY_STATE = "select * from time_to_travel.FIRM where FIRM_STATE = ?";

	private static final String GET_BYREVIEWSTATE = "select FIRM_ID,EMP_ID,FIRMTYPE_ID,FIRM_PRIM,FIRM_NAME,\r\n"
			+ "FIRM_REGIST_ADD,FIRM_OPERATE_ADD,FIRM_POC,FIRM_PHONE,\r\n"
			+ "FIRM_EMAIL,FIRM_APPLY_DATE,FIRM_STATE,FIRM_REVIEW_STATE,FIRM_REVIEW_PETITION from time_to_travel.FIRM where FIRM_REVIEW_STATE = 0";

	private static final String UPDATE = "update time_to_travel.FIRM set EMP_ID = ?, FIRM_STATE = ?, FIRM_REVIEW_STATE = ? where FIRM_ID = ?";

	private static final String GET_BY_ID = "select * from time_to_travel.FIRM where FIRM_ID = ?";

	/* Ticket Firm sql */
	private static final String GET_ONE_STMT_TICKET = "select firm_id , firm_name from FIRM where firm_id = ?";;
	private static final String GET_ALL_TICKET = "select * from FIRM";
	/* Room Firm sql */
	private static final String GET_ALLROOMFIRM = "select * from Firm where firmtype_id=2 and firm_state=0 and FIRM_REVIEW_STATE=1 order by firm_id";
	private static final String GET_ROOMFIRMADDRESS = "select * from firm where FIRM_OPERATE_ADD like \"%\"?\"%\"  and FIRMTYPE_ID=2 and firm_state=0 and FIRM_REVIEW_STATE=1";
	private static final String GET_ROOMFIRMNAME = "select * from firm where FIRM_NAME like \"%\"?\"%\"  and FIRMTYPE_ID=2 and firm_state=0 and FIRM_REVIEW_STATE=1";
	private static final String GET_ONE_ROOM_LIN = "SELECT * FROM firm where firm_id = ?";
	/* Itr Firm sql */
	private static final String GET_BY_ID_ITR = "select * from time_to_travel.FIRM where FIRM_ID = ?";
	private static final String GET_ITR_FIRM_STMT_ITR = "select * from firm where firmtype_id = 3 and FIRM_STATE = 0 and FIRM_REVIEW_STATE = 1;";

	@Override
	public void insert(FirmVO firmVO) {
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setInt(1, firmVO.getFirmtype_id());
			pstmt.setString(2, firmVO.getFirm_prim());
			pstmt.setString(3, firmVO.getFirm_name());
			pstmt.setString(4, firmVO.getFirm_regist_add());
			pstmt.setString(5, firmVO.getFirm_operate_add());
			pstmt.setString(6, firmVO.getFirm_poc());
			pstmt.setString(7, firmVO.getFirm_phone());
			pstmt.setString(8, firmVO.getFirm_email());
			pstmt.setBytes(9, firmVO.getFirm_review_petition());

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
	public List<FirmVO> getFirmByType(Integer firmtype_id) {

		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_TYPE);
			pstmt.setInt(1, firmtype_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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
	public FirmVO getFirmByName(String firm_name) {

		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_NAME);
			pstmt.setString(1, firm_name);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
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
		return firmVO;
	}

	@Override
	public List<FirmVO> getFirmByState(byte firm_state) {

		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_STATE);
			pstmt.setInt(1, firm_state);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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
	public List<FirmVO> getFirmByReviewState() {
		/* Default */
		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYREVIEWSTATE);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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
	public void updateFirm(FirmVO firmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, firmVO.getEmp_id());
			pstmt.setByte(2, firmVO.getFirm_state());
			pstmt.setByte(3, firmVO.getFirm_review_state());
			pstmt.setInt(4, firmVO.getFirm_id());

			pstmt.executeUpdate();

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

	@Override
	public FirmVO getFirmByID(Integer firm_id) {
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, firm_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
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
		return firmVO;
	}
	/* Ticket Firm dao */

	@Override
	public FirmVO findByPrimaryKey_Ticket(Integer firm_id) {
		FirmVO firm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_TICKET);
			pstmt.setInt(1, firm_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				firm = new FirmVO();
				firm.setFirm_id(rs.getInt(1));
				firm.setFirm_name(rs.getString(2));
			}

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
		return firm;
	}

	@Override
	public List<FirmVO> getAll_Ticket() {
		FirmVO firm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FirmVO> list = new ArrayList<FirmVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_TICKET);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				firm = new FirmVO();
				firm.setFirm_id(rs.getInt(1));
				firm.setEmp_id(rs.getInt(2));
				firm.setFirmtype_id(rs.getInt(3));
				firm.setFirm_prim(rs.getString(4));
				firm.setFirm_name(rs.getString(5));
				firm.setFirm_regist_add(rs.getString(6));
				firm.setFirm_operate_add(rs.getString(7));
				firm.setFirm_poc(rs.getString(8));
				firm.setFirm_phone(rs.getString(9));
				firm.setFirm_email(rs.getString(10));
				firm.setFirm_apply_date(rs.getTimestamp(11));
				firm.setFirm_state(rs.getByte(12));
				firm.setFirm_review_state(rs.getByte(13));
				firm.setFirm_review_petition(rs.getBytes(14));
				list.add(firm);
			}
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
	public List<FirmVO> getAllRoomFirm() {
		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLROOMFIRM);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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
	public List<FirmVO> getRoomFirmAddress(String firm_operate_add) {
		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ROOMFIRMADDRESS);
			pstmt.setString(1, firm_operate_add);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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
	public List<FirmVO> getRoomFirmName(String firm_name) {
		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ROOMFIRMNAME);
			pstmt.setString(1, firm_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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
	public FirmVO findByPrimaryKeyLin(Integer firm_id) {
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ROOM_LIN);

			pstmt.setInt(1, firm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));

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
		return firmVO;
	}

	@Override
	public FirmVO getFirmByID_ITR(Integer firm_id) {
		FirmVO firmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, firm_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
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
		return firmVO;
	}

	@Override
	public List<FirmVO> itrGetItrFirm() {
		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ITR_FIRM_STMT_ITR);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				firmVO = new FirmVO();
				firmVO.setFirm_id(rs.getInt("FIRM_ID"));
				firmVO.setEmp_id(rs.getInt("EMP_ID"));
				firmVO.setFirmtype_id(rs.getInt("FIRMTYPE_ID"));
				firmVO.setFirm_prim(rs.getString("FIRM_PRIM"));
				firmVO.setFirm_name(rs.getString("FIRM_NAME"));
				firmVO.setFirm_regist_add(rs.getString("FIRM_REGIST_ADD"));
				firmVO.setFirm_operate_add(rs.getString("FIRM_OPERATE_ADD"));
				firmVO.setFirm_poc(rs.getString("FIRM_POC"));
				firmVO.setFirm_phone(rs.getString("FIRM_PHONE"));
				firmVO.setFirm_email(rs.getString("FIRM_EMAIL"));
				firmVO.setFirm_apply_date(rs.getTimestamp("FIRM_APPLY_DATE"));
				firmVO.setFirm_state(rs.getByte("FIRM_STATE"));
				firmVO.setFirm_review_state(rs.getByte("FIRM_REVIEW_STATE"));
				firmVO.setFirm_review_petition(rs.getBytes("FIRM_REVIEW_PETITION"));
				list.add(firmVO);
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

}
