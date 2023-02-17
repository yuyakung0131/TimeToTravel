package com.firm.model;

import java.util.List;

public interface Firm_interface {

	public void insert(FirmVO firmVO);

	public List<FirmVO> getFirmByType(Integer firmtype_id);

	public FirmVO getFirmByName(String firm_name);

	public List<FirmVO> getFirmByState(byte firm_state);

	public List<FirmVO> getFirmByReviewState();

	public void updateFirm(FirmVO firmVO);

	public FirmVO getFirmByID(Integer firm_id);

	/* Ticket Firm interface */
	public FirmVO findByPrimaryKey_Ticket(Integer firm_id);

	public List<FirmVO> getAll_Ticket();

	/* Room Firm interface */
	public List<FirmVO> getAllRoomFirm();

	public List<FirmVO> getRoomFirmAddress(String firm_operate_add);

	public List<FirmVO> getRoomFirmName(String firm_name);

	public FirmVO findByPrimaryKeyLin(Integer firm_id);

	/* Itr Firm interface */
	public FirmVO getFirmByID_ITR(Integer firm_id);

	public List<FirmVO> itrGetItrFirm();
}
