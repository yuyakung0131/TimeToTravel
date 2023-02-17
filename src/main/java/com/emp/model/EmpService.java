package com.emp.model;

import java.sql.Date;
import java.util.List;

public class EmpService {
	
	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	
	public EmpVO getOneEmp(Integer emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	
	public EmpVO addEmp(String emp_account, String emp_pwd,  String emp_name, String emp_nameeng, 
			byte[] emp_img, Byte emp_state, java.sql.Date emp_date) {

		EmpVO empVO = new EmpVO();

		empVO.setEmp_account(emp_account);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_nameeng(emp_nameeng);
		empVO.setEmp_img(emp_img);
		empVO.setEmp_state(emp_state);
		empVO.setEmp_date(emp_date);
		dao.insert(empVO);

		return empVO; //也可以設定成不回傳東西啦，沒用到其實。
	}
	
	public EmpVO updateEmp(Integer emp_id, String emp_account, String emp_pwd,
			String emp_name, String emp_nameeng, byte[] emp_img, Byte emp_state, Date emp_date) {

		EmpVO empVO = new EmpVO();

		empVO.setEmp_id(emp_id);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_nameeng(emp_nameeng);
		empVO.setEmp_img(emp_img);
		empVO.setEmp_state(emp_state);
		empVO.setEmp_date(emp_date);
		dao.update(empVO);

		return empVO;
	}
	
	public EmpVO getOneEmpByAccount(String emp_account) {
		return dao.findByAccount(emp_account);
	}
}
