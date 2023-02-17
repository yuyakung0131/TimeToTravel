package com.empfunc.model;

import java.util.List;

import com.func.model.FuncVO;

public interface EmpFunc_interface {
	public void insert(EmpFuncVO empFuncVO); //插值
    public void update(EmpFuncVO empFuncVO, Integer func_id);
	public void delete(EmpFuncVO empFuncVO);
	public List<EmpFuncVO> getAll(); //取得最新資料
    public List<EmpFuncVO> findByEmpID(Integer emp_id);
    public List<EmpFuncVO> findByFuncID(Integer func_id);
}
