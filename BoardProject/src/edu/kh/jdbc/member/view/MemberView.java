package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

/** 회원 전용 화면
 * 
 */
public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	
	/**
	 * 회원 기능 메뉴 View
	 */
	public void memberMenu() {
		int input = 0;
		
		do {
			try {
				System.out.println("\n===== 회원 기능 =====\n");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
				System.out.println("3. 내 정보 수정(이름, 성별)");
				System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인");
				System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE");
				
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행 문자 제거
				
				switch(input) {
				case 1 : selectMyInfo(); break;
				case 2 : selectMemberList(); break;
				case 3 : updateMember(); break;
				case 4 : updatePassword(); break;
				case 5 : if(unRegisterMenu()) return; break; 
				case 9 : System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n"); break;
				case 0 : 
					System.out.println("\n===== 프로그램 종료 =====\n");
					
					// JVM 강제 종료 구문
					// 매개변수는 기본 0, 다른 숫자는 오류를 의미
					System.exit(0);
				default : System.out.println("\n*** 메뉴 번호만 입력 해주세요 ***\n");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine();
				input = -1;
			}
			
		} while (input != 9);
	}
	
	public void selectMyInfo() {
		System.out.println("\n===== 내 정보 조회 =====\n");
		
		// 회원 번호, 아이디, 이름, 성별(남/여), 가입일
		// Session.loginMember 이용
		
		System.out.println("회원 번호 : " + Session.loginMember.getMemberNo());
		System.out.println("아이디 : " + Session.loginMember.getMemberId());
		System.out.println("이름 : " + Session.loginMember.getMemberName());
		
		if(Session.loginMember.getMemberGender().equals("M")) 
			System.out.println("성별 : 남");
		else
			System.out.println("성별 : 여");
		
		System.out.println("가입일 : " + Session.loginMember.getEnrollDate());
	}
	
	public void selectMemberList() {
		System.out.println("\n===== 회원 목록 조회 =====\n");
		
		try {
			List<Member> list = service.selectMemberList();
			
			if(list.isEmpty()) {
				System.out.println("\n===== 조회 결과가 없습니다 =====\n");
				return;
			}
			
			for(int i = 0; i<list.size(); i++) {
				System.out.printf("%d\t\t%s\t\t%s\t\t%s\n", 
						i + 1, 
						list.get(i).getMemberId(), 
						list.get(i).getMemberName(),
						list.get(i).getMemberGender());
			}
			
		} catch (Exception e) {
			System.out.println("\n*** 회원 목록 조회중 예외 발생 ***\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * 내 정보 수정
	 */
	public void updateMember() {
		System.out.println("\n===== 내 정보 수정 =====\n");
		
		try {
			System.out.print("수정할 이름 : ");
			String memberName = sc.next();
			
			String memberGender = null;
			while(true) {
				System.out.print("수정할 성별(M/F) : ");
				memberGender = sc.next().toUpperCase();
				
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				} else {
					System.out.println("\n*** F 또는 M만 입력해 주세요 ***\n");
				}
			}
			
			// 회원 정보 수정 및 서비스 호출 및 결과 반환받기
			int result = service.updateMember(memberName, memberGender, Session.loginMember.getMemberNo());
			// Session.loginMember.getMemberNo() : 현재 로그인한 회원의 번호
			
			if(result > 0) {
				System.out.println("\n===== 내 정보가 수정되었습니다 =====\n");
				
				// Service를 호출해서 DB만 추성
				// -> DB와 Java프로그램 데이터 동기화
				Session.loginMember.setMemberName(memberName);
				Session.loginMember.setMemberGender(memberGender);
			}
			else System.out.println("\n*** 수정 실패!!! ***\n");
			
			
		} catch (Exception e) {
			System.out.println("내 정보 수정 중 예외 발생");
			e.printStackTrace();
		}
		
	}
	
	public void updatePassword() {
		System.out.println("\n===== 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인 =====\n");
		
		// 현재 비밀번호 입력
		System.out.print("현재비밀번호 : ");
		String memberPw = sc.next();
		
		String newPw1 = null;
		
		while(true) {
			// 새 비밀번호 입력
			System.out.print("새 비밀번호 : ");
			newPw1 = sc.next();
			
			System.out.print("새 비밀번호 확인 : ");
			String newPw2 = sc.next();
			
			if(newPw1.equals(newPw2)) 
				break;
			
			// 아닐때
			System.out.println("\n*** 새 비밀번호가 일치하지 않습니다. ***\n");
		}
		
		
		try {
			// 서비스 호출 (현재 비밀번호, 새 비밀번호, 로그인한 회원 번호)
			// int 형

			int result = service.updatePassword(memberPw, newPw1, Session.loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n===== 비밀번호가 변경되었습니다 =====\n");
				
				Session.loginMember.setMemberPw(newPw1);
			} else {
				System.out.println("\n*** 수정 실패! ***\n");
			}

		} catch (Exception e) {
			System.out.println("\n*** 비밀번호 변경 예외 발생 ***\n");
			e.printStackTrace();
		}
		
		
	}

	public boolean unRegisterMenu() {
		System.out.println("\n===== 회원 탈퇴 =====\n");
		
		System.out.print("현재 비밀번호 : ");
		String memberPw = sc.next();
		
		String code = service.createSecurityCode();
		System.out.printf("보안문자 입력 [%s] : ", code);
		String input = sc.next();
		
		// 보안 문자 일치여부 확인
		if(!code.equals(input)) { // 일치하지 않으면
			System.out.println("\n*** 보안 문자가 일치하지 않습니다 ***\n");
			return false;
		}
		
		while(true) {
			System.out.print("정말 탈퇴 하시겠습니까? (Y/N) : ");
			char check = sc.next().toUpperCase().charAt(0);
			
			if(check == 'N') {
				System.out.println("\n=====탈퇴 취소 =====\n");
				return false;
			}
			
			if(check == 'Y') {
				break;
			}
			
			// Y나 N이 아닌 경우
			System.out.println("\n*** 잘못 입력하셨습니다 ***\n");
		}
		
		try {
			int result = service.unRegisterMenu(memberPw, Session.loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n=== 탈퇴 되었습니다 ===\n");
				Session.loginMember = null;
				return true;
			}
			else {
				System.out.println("\n*** 현재 비밀번호가 일치하지 않습니다 ***\n");
				
				return false;
			}
			
			
		} catch (Exception e) {
			System.out.println("\n*** 회원 탈퇴 중 예외 발생 ***\n");
			e.printStackTrace();
		}
		
		return false;
		
	}
	
}
