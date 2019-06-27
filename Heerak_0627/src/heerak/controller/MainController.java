package heerak.controller;

import java.util.ArrayList;

import heerak.model.vo.Manager;

/**
 * @author 정식햄
 * @Date 2019. 6. 27.
 * <pre>
 * 1.처리내용
 * </pre>
 */
public class MainController { //이벤트관리 이외, 로그인 등등 기능 구현
	//회원가입
	//로그인
	//(로그인후)관리자별 이벤트리스트 호출
	//입력한 코드 일치여부 확인
	EventController ec = new EventController();
	ArrayList<Manager> managerAccount = new ArrayList<>(); // 메니저 객체 배열이다.
	ArrayList inviteCodeCompare = new ArrayList(); 
	public int accessAccount(String id, String pwd) { // 관리자 로그인 메서드
		// 로그인에서 일어나는 조건,
		// 로그인 성공시 Id와 Pwd가 기존 맴버 객채와 일치할 경우
		// 로그인 실패시 Id나 Pwd가 기본 맴버 객채와 상이할 경우
		// 로그인 아이디가 없을 경우

		// 로그인 성공시 return 3;
		// 로그인 아이디는 맞지만 비밀번호가 틀릴 경우 return 2;
		// 로그인 실패시 return 1;
		int check = 0; // 리턴값을 주는 변수이다.

		for (int i = 0; i < managerAccount.size(); i++) {
			if (id.equals(managerAccount.get(i).getId()) &&pwd.equals(managerAccount.get(i).getPwd())) {
				return check = 3;// 아이디와 비밀번호가 일치하는 경우
			} else if (id.equals(managerAccount.get(i).getId()) && !pwd.equals(managerAccount.get(i).getPwd())){
				return check = 2;// 아이디는 일치하나 비밀번호가 일치하지 않는 경우
			} else if (!id.equals(managerAccount.get(i).getId()) && !pwd.equals(managerAccount.get(i).getPwd())) {
				return check = 1;// 아이디는 와 비밀번호가 일치하지 않는 경우
			}
		}
		return check;
	}

	public void makeAccount(String name, String id, String pwd, String email, String phone) { // 객체 회원 생성 메서드
		// 기존 아이디와 비교해서 겹치는 아이디가 있는지 확인한다. (중복 제거)
		for (int i = 0; i < managerAccount.size(); i++) {
			if (id.equals(managerAccount.get(i)) && pwd.equals(managerAccount.get(i))) {
				System.out.println("아이디가 중복됩니다.");
				continue;
			}
		}
		managerAccount.add(new Manager(name, id, pwd, email, phone)); // ArrayList에 회원 객체 추가
		System.out.println("controller : 회원 가입 성공!");
	}

	public int compareInviteCode(String joinEventCode) { // 매개변수는 사용자가 입력하는 이벤트 코드 값이다.

		// 메소드 목적 사용자가 입력한 이벤트 코드를 이벤트 객체가 가지고 있는 초대 코드 값과 비교후 조건에 맞는 int리턴값을 준다.

		int eventCondition = 0; // event 3조건 상태의 값을 int값으로 표시해주는 이벤트 상태 변수
		// 이벤트 객체의 배열 // 추후에 상욱이 형이 이벤트 객체를 만들면 연결 할 예정이다. 

		// 조건 1. 사용자 입력 초대코드와 이벤트 초대코드가 같고 이벤트가 마감 하지 않을 경우 	return 1;
		// 조건 2. 사용자 입력 초대코드와 이벤트 초대코드가 같지만 이벤트가 마감 했을 경우 		return 2;
		// 조건 3. 사용자 입력 초대코드와 이벤트 초대코드가 같지 않는 경우. 					return 3;
		inviteCodeCompare = ec.getAllEvent();
		for (int i = 0; i < inviteCodeCompare.size(); i++) {
			if (joinEventCode.equals(inviteCodeCompare.get(i)) && inviteCodeCompare.get(i).equals(false)) {
				eventCondition = 1;
			} else if (joinEventCode.equals(inviteCodeCompare.get(i)) && inviteCodeCompare.get(i).equals(true)) { 
				eventCondition = 2;
			} else if (!joinEventCode.equals(inviteCodeCompare.get(i))) {
				eventCondition = 3;
			}
		}
		return eventCondition;
	}

	public Manager getManager(String id, String pwd) { // 로그인 성공시 객체 리턴
		Manager mg = null; // 리턴 객체를 담을 객체 변수이다.
		for (int i = 0; i < managerAccount.size(); i++) { // 아이디 비번 타겟에 맞는 객체를 리턴하는 로직이다.
			if (id.equals(managerAccount.get(i).getId()) && pwd.equals(managerAccount.get(i).getPwd())) {
				mg = managerAccount.get(i);
			}
		}
		return mg; 
	}
	public ArrayList getList() {
		return managerAccount;
	}
	
}
