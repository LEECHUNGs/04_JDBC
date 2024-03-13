package edu.kh.jdc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdc1.model.vo.Emp;

public class JDBCExample3 {
	
	public static void main(String[] args) {
		
		// 부서명을 입력받아 같은 부서에 있는 사원의
		// 사원명, 부서병, 급여 조회
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("부서명 입력 : "); // 총무부
			String input = sc.nextLine();
			
			// JDBC 참조변수에 알맞은 객체 대입
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; 
			String ip = "localhost"; 
			String port = ":1522"; 
			String sid = ":XE"; 
			String user = "kh_lcs"; 
			String pw = "kh1234"; 
			
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			
			
			String sql = "SELECT EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY"
					   + " FROM EMPLOYEE"
					   + " LEFR JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)"
					   + " WHERE NVL(DEPT_TITLE, '부서없음') = '" + input + "'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			// 조회 결과(rs) 를 List 옯겨담기
			List<Emp> list = new ArrayList<Emp>();
			
			while(rs.next()) {
				
				// 현재 행에 존재하는 컬럼값 얻어오기
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				int salary = rs.getInt("SALARY");
				
				list.add(new Emp(empName, deptTitle, salary));
				
			}
			
			// 만약에 List에 추가되면 Emp 객체가 없다면 "조회 결과 없음"
			// 있다면 순차적으로 출력
			
			if(list.isEmpty()) { // List가 비어있을 경우
				System.out.println("조회 결과 없음");
				
			} else {
				
				// 향상된 for문
				for(Emp e : list) {
					System.out.println(e);
				}
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}
}
