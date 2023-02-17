package com.func.model;

import java.util.List;

public class FuncService {
	
	Func_interface dao ;
	
	
	public FuncService() {
		dao = new FuncDAO();
	}
	
	public FuncVO getOneFunc(Integer Func_id) {
		return dao.findByPrimaryKey(Func_id);
	}
	
	public List<FuncVO> getAll(){
		return dao.getAll();
	}
}
