package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	public List<Member> selectMemberList() throws Exception{
		Connection conn = getConnection();
		
		List<Member> list = dao.selectMemberList(conn);
		
		close(conn);
		
		return list;
	}

	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, memberName, memberGender, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 비밀번호 변경 서비스
	 * @param memberPw
	 * @param newPw1
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int updatePassword(String memberPw, String newPw1, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, memberPw, newPw1, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}

	/** 숫자 6자리 보안코드 생성 서비스
	 * @return code
	 */
	public String createSecurityCode() {
		
		StringBuffer code = new StringBuffer();
		
		Random ran = new Random(); // 난수 생성 객체
		
		for(int i = 0; i<6; i++) {
			int x = ran.nextInt(10); // 0 이상 10 미만 정수
			code.append(x);
		}
		
		return code.toString();
	}


	public int unRegisterMenu(String memberPw, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.unRegisterMenu(conn, memberPw, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
}
