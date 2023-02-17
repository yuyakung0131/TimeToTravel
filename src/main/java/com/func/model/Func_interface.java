package com.func.model;

import java.util.List;

public interface Func_interface {
	
	public void insert(FuncVO funcVO);//如果後續要insert新功能的話可以利用,目前為預設不會提前去做測試)
	public List<FuncVO> getAll(); 
	public FuncVO findByPrimaryKey(Integer func_id);
	
}
