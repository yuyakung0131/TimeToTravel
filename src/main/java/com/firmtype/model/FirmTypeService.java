package com.firmtype.model;

import java.util.List;

public class FirmTypeService {
	
	FirmType_interface dao;
	
	public FirmTypeService() {
		dao = new FirmTypeDAO();
	}
	
	public FirmTypeVO getOneFirmTypeVO(Integer firmtype_id) {
		return dao.findByPrimaryKey(firmtype_id);
	}
	
	public List<FirmTypeVO> getAll(){
		return dao.getAll();
	}
}
