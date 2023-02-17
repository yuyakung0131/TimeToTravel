package com.empfunc.model;

public class EmpFuncVO implements java.io.Serializable {
	/* 利用private宣告->防止其他人使用(資料竄改) */
	private Integer emp_id;
	private Integer func_id;

	/* 宣告get/set方法->利用這些方法去資料庫進行拿取或設定動作 */
	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public Integer getFunc_id() {
		return func_id;
	}

	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}
	
	public com.emp.model.EmpVO getEmpVO(){
		com.emp.model.EmpService empSvc = new com.emp.model.EmpService();
		com.emp.model.EmpVO empVO = empSvc.getOneEmp(emp_id);
		return empVO;
	}
	
	public com.func.model.FuncVO getFuncVO(){
		com.func.model.FuncService funcSvc = new com.func.model.FuncService();
		com.func.model.FuncVO funcVO = funcSvc.getOneFunc(func_id);
		return funcVO;
	}

}
