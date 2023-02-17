package com.member.model;

import java.util.List;

public interface MemberDAO_interface {

	public void insert(MemberVO memberVO);

	public List<MemberVO> getAll();

	public List<MemberVO> findByName(String member_name);

	public List<MemberVO> findByID(Integer member_id);

	public List<MemberVO> findByState(Byte member_state);

	public void stateUpdate(MemberVO memberVO);

	public MemberVO getByID(Integer memebr_id);

	public MemberVO findByEmail(String member_email);

	public void updateMemberInfo(MemberVO memberVO);

	public void updateMemberImg(MemberVO memberVO);

	public MemberVO findPicByID(Integer member_id);

	public void updateMemberAccount(MemberVO memberVO);

	public void updateMemberPwd(MemberVO memberVO);

	/* Ticket member interface */
	public MemberVO findByPrimaryKey_Ticket(Integer member_id);

	/* Room member interface */
	public MemberVO getRoomByIDLin(Integer member_id);

	public MemberVO roomfindByPrimaryKey(Integer member_id);

	/* Itr member interface */
	public MemberVO findByPrimaryKeyITR(Integer member_id);

}
