package com.firmtype.model;

import java.util.List;

public interface FirmType_interface {
	public void insert(FirmTypeVO firmTypeVO); 
	public List<FirmTypeVO> getAll();
	public FirmTypeVO findByPrimaryKey(Integer func_id);
}
