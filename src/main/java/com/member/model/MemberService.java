package com.member.model;

import java.util.List;

public class MemberService {
	/* initial implement */
	private MemberDAO_interface dao;

	/* initial constructor */
	public MemberService() {
		dao = new MemberDAO();
	}

	/* initial method */
//get all
	public List<MemberVO> getALL() {
		return dao.getAll();
	}

	public MemberVO getMemberID(Integer member_id) {
		return dao.getByID(member_id);
	}

//get one -> will use overloading
	public List<MemberVO> getOneMember(String member_name) {
		return dao.findByName(member_name);
	}

	public List<MemberVO> getOneMember(Integer member_id) {
		return dao.findByID(member_id);
	}

//get list for state
	public List<MemberVO> getListMember(Byte member_state) {
		return dao.findByState(member_state);
	}

//insert register data service
	public MemberVO addMember(String member_name, String member_email, String member_pwd, Byte member_state) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_name(member_name);
		memberVO.setMember_email(member_email);
		memberVO.setMember_pwd(member_pwd);
		memberVO.setMember_state(member_state);
		dao.insert(memberVO);
		return memberVO;
	}

//update state data service
	public MemberVO changeState(Byte member_state, Integer member_id) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_state(member_state);
		memberVO.setMember_id(member_id);
		dao.stateUpdate(memberVO);
		return memberVO;
	}

//member login service
	public MemberVO loginMember(String member_email) {
		return dao.findByEmail(member_email);
	}

//member info update service
	public MemberVO changeMemberInfo(String member_name, String member_nameeng, String member_idcard,
			String member_gender, String member_add, String member_phone, Integer member_id) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_name(member_name);
		memberVO.setMember_nameeng(member_nameeng);
		memberVO.setMember_idcard(member_idcard);
		memberVO.setMember_gender(member_gender);
		memberVO.setMember_add(member_add);
		memberVO.setMember_phone(member_phone);
		memberVO.setMember_id(member_id);
		dao.updateMemberInfo(memberVO);
		return memberVO;
	}

//member img update service
	public MemberVO changeMemberImg(byte[] member_img, Integer member_id) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_img(member_img);
		memberVO.setMember_id(member_id);
		dao.updateMemberImg(memberVO);
		return memberVO;
	}

//get member img
	public MemberVO getMemberImg(Integer member_id) {
		return dao.findPicByID(member_id);
	}

//member account update service
	public MemberVO changeMemberAccount(String member_email, String member_pwd, Integer member_id) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_email(member_email);
		memberVO.setMember_pwd(member_pwd);
		memberVO.setMember_id(member_id);
		dao.updateMemberAccount(memberVO);
		return memberVO;
	}

//member forgive pwd service
	public MemberVO forgiveMemberPwd(String member_pwd, String member_email) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_pwd(member_pwd);
		memberVO.setMember_email(member_email);
		dao.updateMemberPwd(memberVO);
		return memberVO;
	}

	// Ticket Member Service
	public MemberVO getOneMember_Ticket(Integer member_id) {
		return dao.findByPrimaryKey_Ticket(member_id);
	}

	// Room Member Service
	public MemberVO roomGetOneMemberSheng(Integer member_id) {
		return dao.roomfindByPrimaryKey(member_id);
	}

	public MemberVO getMemberIDLin(Integer member_id) {
		return dao.getRoomByIDLin(member_id);
	}

	// Itr Member Service
	public MemberVO getOneMemberITR(Integer member_id) {
		return dao.findByPrimaryKeyITR(member_id);
	}

}
