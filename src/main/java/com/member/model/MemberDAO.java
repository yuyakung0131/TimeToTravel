package com.member.model;

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

import oracle.net.aso.f;

public class MemberDAO implements MemberDAO_interface {

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
	private static final String INSERT_STMT = "insert into MEMBER(MEMBER_EMAIL,MEMBER_PWD,MEMBER_NAME\r\n"
			+ ",MEMBER_STATE)\r\n" + "values(?,?,?,?)";
	private static final String GET_ALL_STMT = "select MEMBER_ID,MEMBER_EMAIL,MEMBER_PWD,MEMBER_NAME,MEMBER_NAMEENG,\r\n"
			+ "MEMBER_IDCARD,MEMBER_GENDER,MEMBER_GENDER,MEMBER_IMG,MEMBER_ADD,MEMBER_PHONE,MEMBER_STATE,MEMBER_DATE\r\n"
			+ "from MEMBER order by MEMBER_ID";
	private static final String Get_ONE_BYNAME_STMT = "select*from MEMBER where MEMBER_NAME = ?";
	private static final String Get_ONE_BYID_STMT = "select*from MEMBER where MEMBER_ID = ?";
	private static final String GET_LIST_BYSTATE_STMT = "select*from MEMBER where MEMBER_STATE = ?";
	private static final String UPDATE_STATE_STMT = "update MEMBER set MEMBER_STATE = ? where MEMBER_ID = ?";
	private static final String Get_ONE_BYEMAIL_STMT = "select * from MEMBER where MEMBER_EMAIL = ?";
	private static final String UPDATE_MEMBER_INFO_STMT = "update MEMBER set MEMBER_NAME=?,MEMBER_NAMEENG=?,\r\n"
			+ "MEMBER_IDCARD=?,MEMBER_GENDER=?,MEMBER_ADD=?,MEMBER_PHONE=? \r\n" + "where MEMBER_ID =?";
	private static final String UPDATE_MEMBER_IMG_STMT = "update MEMBER set MEMBER_IMG=? where MEMBER_ID=?";
	private static final String GET_PIC_BYID_STMT = "select MEMBER_IMG from MEMBER where MEMBER_ID=?";
	private static final String UPDTAE_MEMBER_ACCOUNT_STMT = "update MEMBER set MEMBER_EMAIL =?,MEMBER_PWD=? where MEMBER_ID =?";
	private static final String UPDATE_MEMBER_PWD_STMT = "update MEMBER set MEMBER_PWD=? where MEMBER_EMAIL=?";
	/* Ticket member sql */
	private static final String GET_ONE_STMT_TICKET = "select * from MEMBER where MEMBER_ID = ?";
	/* Room member sql */
	private static final String Get_ONE_ROOMBYID_LIN = "select*from MEMBER where MEMBER_ID = ?";
	private static final String GET_ONE_NAME_Sheng = "SELECT * FROM  member where  member_id = ?";
	/* Itr member sql */
	private static final String GET_ONE_STMT_ITR = "SELECT * FROM  member where  member_id = ?";

	@Override
	public void insert(MemberVO memberVO) {
		// TODO Auto-generated method stub
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			/* Insert value into database */
			pstmt.setString(1, memberVO.getMember_email());
			pstmt.setString(2, memberVO.getMember_pwd());
			pstmt.setString(3, memberVO.getMember_name());
			pstmt.setByte(4, memberVO.getMember_state());
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

	public MemberVO getByID(Integer member_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ONE_BYID_STMT);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));

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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		/* Default */
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
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
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));
				list.add(memberVO);
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
	public List<MemberVO> findByName(String member_name) {
		/* Default */
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ONE_BYNAME_STMT);
			pstmt.setString(1, member_name);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));
				list.add(memberVO);
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
	public List<MemberVO> findByID(Integer member_id) {
		/* Default */
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ONE_BYID_STMT);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));
				list.add(memberVO);
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
	public List<MemberVO> findByState(Byte member_state) {
		// TODO Auto-generated method stub
		/* Default */
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BYSTATE_STMT);
			pstmt.setInt(1, member_state);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));
				list.add(memberVO);
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
	public void stateUpdate(MemberVO memberVO) {
		// TODO Auto-generated method stub
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATE_STMT);
			/* Insert value into database */
			pstmt.setByte(1, memberVO.getMember_state());
			pstmt.setInt(2, memberVO.getMember_id());
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
	public MemberVO findByEmail(String member_email) {
		/* Default */
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ONE_BYEMAIL_STMT);
			pstmt.setString(1, member_email);

			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));
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
		return memberVO;
	}

	@Override
	public void updateMemberInfo(MemberVO memberVO) {
		// TODO Auto-generated method stub
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEMBER_INFO_STMT);
			/* Insert value into database */
			pstmt.setString(1, memberVO.getMember_name());
			pstmt.setString(2, memberVO.getMember_nameeng());
			pstmt.setString(3, memberVO.getMember_idcard());
			pstmt.setString(4, memberVO.getMember_gender());
			pstmt.setString(5, memberVO.getMember_add());
			pstmt.setString(6, memberVO.getMember_phone());
			pstmt.setInt(7, memberVO.getMember_id());
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
	public void updateMemberImg(MemberVO memberVO) {
		{
			// TODO Auto-generated method stub
			/* Default connection */
			Connection con = null; // Default null
			PreparedStatement pstmt = null;// Default null
			try {
				/* Start connection */
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_MEMBER_IMG_STMT);
				/* Insert value into database */
				pstmt.setBytes(1, memberVO.getMember_img());
				pstmt.setInt(2, memberVO.getMember_id());
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
	}

	@Override
	public MemberVO findPicByID(Integer member_id) {
		// TODO Auto-generated method stub
		/* Default */
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PIC_BYID_STMT);
			pstmt.setInt(1, member_id);

			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_img(rs.getBytes("MEMBER_IMG"));
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
		return memberVO;
	}

	@Override
	public void updateMemberAccount(MemberVO memberVO) {
		// TODO Auto-generated method stub
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDTAE_MEMBER_ACCOUNT_STMT);
			/* Insert value into database */
			pstmt.setString(1, memberVO.getMember_email());
			pstmt.setString(2, memberVO.getMember_pwd());
			pstmt.setInt(3, memberVO.getMember_id());
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
	public void updateMemberPwd(MemberVO memberVO) {
		// TODO Auto-generated method stub
		/* Default connection */
		Connection con = null; // Default null
		PreparedStatement pstmt = null;// Default null
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEMBER_PWD_STMT);
			/* Insert value into database */
			pstmt.setString(1, memberVO.getMember_pwd());
			pstmt.setString(2, memberVO.getMember_email());
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
	public MemberVO findByPrimaryKey_Ticket(Integer member_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_TICKET);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt(1));
				memberVO.setMember_email(rs.getString(2));
				memberVO.setMember_pwd(rs.getString(3));
				memberVO.setMember_name(rs.getString(4));
				memberVO.setMember_nameeng(rs.getString(5));
				memberVO.setMember_idcard(rs.getString(6));
				memberVO.setMember_gender(rs.getString(7));
				memberVO.setMember_img(rs.getBytes(8));
				memberVO.setMember_add(rs.getString(9));
				memberVO.setMember_phone(rs.getString(10));
				memberVO.setMember_state(rs.getByte(11));
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
		return memberVO;
	}

	@Override
	public MemberVO getRoomByIDLin(Integer member_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/* Start connection */
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ONE_ROOMBYID_LIN);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			/* Get value from result set */
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("MEMBER_ID"));
				memberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				memberVO.setMember_pwd(rs.getString("MEMBER_PWD"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_nameeng(rs.getString("MEMBER_NAMEENG"));
				memberVO.setMember_idcard(rs.getString("MEMBER_IDCARD"));
				memberVO.setMember_gender(rs.getString("MEMBER_GENDER"));
				memberVO.setMember_img(rs.getBytes("MEMBER_img"));
				memberVO.setMember_add(rs.getString("MEMBER_ADD"));
				memberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				memberVO.setMember_state(rs.getByte("MEMBER_STATE"));
				memberVO.setMember_date(rs.getTimestamp("MEMBER_DATE"));

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
		return memberVO;
	}

	@Override
	public MemberVO roomfindByPrimaryKey(Integer member_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_NAME_Sheng);

			pstmt.setInt(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("member_id"));
				memberVO.setMember_email(rs.getString("member_email"));
				memberVO.setMember_pwd(rs.getString("member_pwd"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_nameeng(rs.getString("member_name"));
				memberVO.setMember_idcard(rs.getString("member_idcard"));
				memberVO.setMember_gender(rs.getString("member_gender"));
//	    memberVO.setMember_img(rs.getBytes("member_img"));
				memberVO.setMember_add(rs.getString("member_add"));
				memberVO.setMember_phone(rs.getString("member_phone"));
//	    memberVO.setMember_state(rs.getByte("member_state"));
//	    memberVO.setMember_date(rs.getInt("member_date"));

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
		return memberVO;
	}

	@Override
	public MemberVO findByPrimaryKeyITR(Integer member_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ITR);

			pstmt.setInt(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getInt("member_id"));
				memberVO.setMember_email(rs.getString("member_email"));
				memberVO.setMember_pwd(rs.getString("member_pwd"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_nameeng(rs.getString("member_name"));
				memberVO.setMember_idcard(rs.getString("member_idcard"));
				memberVO.setMember_gender(rs.getString("member_gender"));
//				memberVO.setMember_img(rs.getBytes("member_img"));
				memberVO.setMember_add(rs.getString("member_add"));
				memberVO.setMember_phone(rs.getString("member_phone"));
//				memberVO.setMember_state(rs.getByte("member_state"));
//				memberVO.setMember_date(rs.getInt("member_date"));

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
		return memberVO;
	}

}
