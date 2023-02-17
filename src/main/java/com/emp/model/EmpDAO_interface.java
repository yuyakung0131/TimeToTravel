package com.emp.model;

import java.util.List;

public interface EmpDAO_interface {
	public void insert(EmpVO empVO);
	public EmpVO findByPrimaryKey(Integer empno);
	public List<EmpVO> getAll();
	public void update(EmpVO empvo);
	public EmpVO findByAccount(String emp_account);
}
