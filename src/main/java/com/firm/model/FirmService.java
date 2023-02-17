package com.firm.model;

import java.util.List;

public class FirmService {

	Firm_interface dao;

	public FirmService() {
		dao = new FirmDAO();
	}

	public void addFirm(Integer firmtype_id, String firm_prim, String firm_name, String firm_regist_add,
			String firm_operate_add, String firm_poc, String firm_phone, String firm_email,
			byte[] firm_review_petition) {

		FirmVO firmVO = new FirmVO();
		firmVO.setFirmtype_id(firmtype_id);
		firmVO.setFirm_prim(firm_prim);
		firmVO.setFirm_name(firm_name);
		firmVO.setFirm_regist_add(firm_regist_add);
		firmVO.setFirm_operate_add(firm_operate_add);
		firmVO.setFirm_poc(firm_poc);
		firmVO.setFirm_phone(firm_phone);
		firmVO.setFirm_email(firm_email);
		firmVO.setFirm_review_petition(firm_review_petition);

		dao.insert(firmVO);
	}

	public List<FirmVO> getByType(Integer firmtype_id) {
		return dao.getFirmByType(firmtype_id);
	}

	public FirmVO getByName(String firm_name) {
		return dao.getFirmByName(firm_name);
	}

	public List<FirmVO> getByState(Byte firm_state) {
		return dao.getFirmByState(firm_state);
	}

	public List<FirmVO> getByReviewState() {
		return dao.getFirmByReviewState();
	}

	public void update(Integer emp_id, Byte firm_state, Byte firm_review_state, Integer firm_id) {

		FirmVO firmVO = new FirmVO();
		firmVO.setEmp_id(emp_id);
		firmVO.setFirm_state(firm_state);
		firmVO.setFirm_review_state(firm_review_state);
		firmVO.setFirm_id(firm_id);

		dao.updateFirm(firmVO);
	}

	public FirmVO getByID(Integer firm_id) {
		return dao.getFirmByID(firm_id);
	}

	/* Ticket Firm Service */
	public FirmVO getOneFirm_Ticket(Integer firm_id) {
		return dao.findByPrimaryKey_Ticket(firm_id);
	}

	public List<FirmVO> getAll_Ticket() {
		return dao.getAll_Ticket();
	}

	/* Room Firm Service */
	public FirmVO getOneDeptByRoomLin(Integer firm_id) {
		return dao.findByPrimaryKeyLin(firm_id);
	}

	public List<FirmVO> getAllRoomFirm() {
		return dao.getAllRoomFirm();
	}

	public List<FirmVO> getRoomFirmAddress(String firm_operate_add) {
		return dao.getRoomFirmAddress(firm_operate_add);
	}

	public List<FirmVO> getRoomFirmName(String firm_name) {
		return dao.getRoomFirmName(firm_name);
	}

	/* ITR Firm Service */
	public FirmVO getByID_ITR(Integer firm_id) {
		return dao.getFirmByID_ITR(firm_id);
	}

	public List<FirmVO> getItrGetItrFirm() {
		return dao.itrGetItrFirm();
	}
}
