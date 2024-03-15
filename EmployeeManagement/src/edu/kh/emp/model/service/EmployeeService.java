package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();

	/** 전체 사원 정보 조회 서비스
	 * @return list
	 */
	public List<Employee> selectAll() throws Exception{
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectAll(conn);
		
		// 2. 사용한 커넥션 반환
		close(conn);
		
		
		return list;
	}

	/** 사원 정보 추가 서비스
	 * @param emp
	 * @return result(1/0)
	 */
	public int insertEmployee(Employee emp) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertEmployee(conn, emp);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<Employee> selectEmpId(int empId) throws Exception{
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectEmpId(conn, empId);
		
		return list;
	}

	public int updateEmployee(Employee emp) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateEmployee(conn, emp);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteEmployee(int empId) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteEmployee(conn, empId);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<Employee> selectDeptEmp(String deptTitle) throws Exception{
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectDeptEmp(conn, deptTitle);
		
		close(conn);
		
		return list;
	}

	public List<Employee> selectSalaryEmp(int salary) throws Exception{
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectSalaryEmp(conn, salary);
		
		close(conn);
		
		return list;
	}

	public Map<String, Integer> selectDeptSalary() throws Exception{
		Connection conn = getConnection();
		
		Map<String, Integer> map = dao.selectDeptSalary(conn);
		
		return map;
	}

	public List<Employee> selectEmpNo(String empNo) throws Exception{
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectEmpNo(conn, empNo);
		
		close(conn);
		
		return list;
	}

	public Map<String, Double> selectJobAvgSalary() throws Exception{
		Connection conn = getConnection();
		
		Map<String, Double> map = dao.selectJobAvgSalary(conn);
		
		return map;
	}
}
