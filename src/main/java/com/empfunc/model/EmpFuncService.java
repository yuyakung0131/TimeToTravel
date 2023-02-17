package com.empfunc.model;

import java.util.List;

public class EmpFuncService {
	
	private EmpFunc_interface dao;
	
	public EmpFuncService() {
		dao = new EmpFuncDAO();
	}
	
	public void addEmpFunc(Integer emp_id, Integer func_id) {
		EmpFuncVO empFunc = new EmpFuncVO();
		empFunc.setEmp_id(emp_id);
		empFunc.setFunc_id(func_id);
		
		dao.insert(empFunc);
	}
	
	public List<EmpFuncVO> getAll(){
		return dao.getAll();
	}
	
	public void updateEmpFunc(Integer emp_id, Integer func_id_original, Integer func_id) {
		EmpFuncVO empFunc = new EmpFuncVO();
		empFunc.setEmp_id(emp_id);
		empFunc.setFunc_id(func_id_original);
		
		dao.update(empFunc, func_id);
	}
	
	public void deleteEmpFunc(Integer emp_id, Integer func_id) {
		EmpFuncVO empFunc = new EmpFuncVO();
		empFunc.setEmp_id(emp_id);
		empFunc.setFunc_id(func_id);
		
		dao.delete(empFunc);
	}
	
	public List<EmpFuncVO> getByEmpID(Integer emp_id){
		return dao.findByEmpID(emp_id);
	}
	
	public List<EmpFuncVO> getByFuncID(Integer func_id){
		return dao.findByFuncID(func_id);
	}
	
}
