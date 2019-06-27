package heerak.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import heerak.controller.EventController;
import heerak.controller.MainController;
import heerak.controller.ResultController;
import heerak.model.vo.Event;
import heerak.model.vo.EventDate;
import heerak.model.vo.User;

/**
 * @author 희락햄
 * @Date 2019. 6. 27.
 * <pre>
 * 1.처리내용
 * </pre>
 */
public class UserView {

	Scanner sc = new Scanner(System.in);
	ResultController rc = new ResultController();
	EventController ec = new EventController();
	MainController mc = new MainController();
	ArrayList<String> dateList = new ArrayList<>();
	ArrayList<User> userList = new ArrayList<>();
	String code = "";
	Event ev = null;
	ArrayList<User> accUser = new ArrayList();
	
	public void firstPage() {         //   1. 초기화면

		int choice;
		while(true) {
			System.out.println("1. 관리자");
			System.out.println("2. 사용자");

			System.out.print("선택 : ");
			choice = sc.nextInt();

			System.out.println();

			switch(choice) {
			case 1: managerPage();
			break;
			case 2:   checkCode();
			break;
			default: System.out.println("잘못 선택하셨습니다.");   // 콘솔 확인용
			}

		}
	}

	public void managerPage() {
		System.out.println("메니저 페이지");
		System.out.println("1. My Event");
		System.out.println("2. Create Event");

		int choice = sc.nextInt();

		switch(choice) {
		case 1: // myEventView();
			break;
		case 2: // createEventView();
		}
	}

	public void checkCode() {          // 2-1. 코드 입력/확인

		JFrame cc = new JFrame();

		cc.setTitle("코드입력");
		cc.setBounds(400,400,350,400);
		cc.getContentPane().setBackground(Color.LIGHT_GRAY);
		cc.setLayout(null);
		cc.setResizable(false);

		JLabel codel = new JLabel("코드입력: ");
		codel.setLocation(80,50);
		codel.setSize(100,50);
		codel.setFont(new Font("맑은고딕",Font.PLAIN,20));

		JTextField codef = new JTextField();

		codef.setLocation(140,90);
		codef.setSize(120,30);


		JButton codeb = new JButton();

		codeb.setLocation(170,90);
		codeb.setSize(60,50);


		cc.add(codel);
		cc.add(codef);
		cc.add(codeb);

		cc.setVisible(true);


		MainController mc = new MainController();

		while(true) {   
			System.out.println("코드입력: ");
			//mc.compareInviteCode(codef.getText());

			if(mc.compareInviteCode(codef.getText()) == 1) {   // Manager 클래스 manCode와 일치시 correctCodePage 실행
				code = codef.getText();
				showEvent();
			}else if(mc.compareInviteCode(codef.getText()) == 2){   // 마감된 이벤트
				code = codef.getText();
				System.out.println("마감된 이벤트입니다.");   //
				resultOfEvent();
			}else {
				//notMatchedCode();// 코드 불일치 시 '팝업창'
				JOptionPane.showMessageDialog(cc, "코드에 해당하는 이벤트가 없습니다.", "Error",JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}
	}

	public void resultOfEvent() {      // 2-2. 최종 마감된 날짜
		//System.out.println("최종 선택된 이벤트 날짜: ");                 //   최종 선택 날짜 출력
		//System.out.println("확인");   // "확인" 버튼 클릭시 firstPage()로 이동
		//firstPage();
		//rc.resultOfEvent(dateList, userList)
		
		/*dateList.add("2019/06/21");
		dateList.add("2019/06/22");*/
		// -->
		dateList = ev.getEventDate();
		userList = mc.getUserList();
		
		for(int i = 0; i < userList.size() ;i++) {
			if(userList.get(i).getAccCode().equals(code)) {
				accUser.add(userList.get(i));
			}
		}
		/*ArrayList<EventDate> vote1 = new ArrayList<>();
		vote1.add(new EventDate("2019/06/21",1));
		vote1.add(new EventDate("2019/06/22",1));
		User u1 = new User("user1",vote1,"반갑습니다.","abc");

		ArrayList<EventDate> vote2 = new ArrayList<>();
		vote2.add(new EventDate("2019/06/21",1));
		vote2.add(new EventDate("2019/06/22",3));
		User u2 = new User("user2",vote2,"빨리만나","abc");	*/   

		/*userList.add(u1);
		userList.add(u2);*/
		String result_d = rc.resultOfEvent(dateList, accUser);
		System.out.println(result_d);
	}

	public void notMatchedCode() {      // 3-1. 코드가 맞지 않을 경우
		System.out.println("코드를 확인해주세요");
	}

	public void showEvent() {  //투표창       // 3-2. 코드에 맞는 이벤트 있을 때 이벤트 생성화면 출력
		// 코드에 해당하는 리턴값 이벤트 객체 생성

		ev = ec.getEvent(code);
		ArrayList voteList = new ArrayList();
		System.out.println("모임명: " + ev.getEventTitle());         //   생성된 이벤트 명 가져오기
		System.out.println("설명: " + ev.getComment());            //   생성된 이벤트 설명 가져오기
		System.out.println("이름: ");                           //   이름 입력받기
		String user = sc.nextLine();
		System.out.println("선택 날짜: " + ev.getEventDate());       //   선택된 날짜 리스트 가져오기
		System.out.println("코멘트 입력: ");                     //   사용자 코멘트 받기
		String comment = sc.nextLine();

		System.out.println("제출");                           //   JButton --> 'clickOK()'
		// 제출 버튼을 누르고 나면 ActionPerformed
		User u = new User(user,voteList,comment,code);
		// o.addUser(u);
		mc.addUser(u);
		/*showResult1();            //   마지막 결과값 출력1: 날짜 + 투표 카운트 값
      showResult2(); */           //   마지막 결과값 출력2: 날짜 + 이름 + 각 투표 결과 

		//   나중에 gui 입힐때 ...

	}

	/*public void showResult1() {         // 4-1. 지금까지의 결과값 출력 [날짜, 투표결과 카운트]

      ResultController r = new ResultController();

      //r.getResult(dateList, userList);
   }

   public void showResult2() {         // 4-1. 지금까지의 결과값 출력 [사용자 이름, o,x,semo 결과]

      ResultController r = new ResultController();
      //r.nameResult(dateList, userList);

   } */

	public void showResult() {
		String[][] left = rc.getResult(dateList, accUser);
		String[] left_result = new String[dateList.size()];
		String[][] right = rc.nameResult(dateList, accUser);
		String[] right_result = new String[accUser.size()];
		JFrame result = new JFrame();

		JPanel leftp = new JPanel();
		JPanel rightp = new JPanel();
		/*left = rc.getResult(dateList, accUser);*/
		for(int i = 0; i < left_result.length ;i++) {
			String str = String.format("%s  %s  %s  %s",left[i][0],left[i][1],left[i][2],left[i][3]);
			left_result[i] = str;
		}
		
		for(int i = 0 ; i < right_result.length ;i++) {
			String str = "";
			for(int j = 0; j <= dateList.size() ; j++) {
				str += (right[i][j] +"  ");
			}
			right_result[i] = str;
		}
		
		result.add(leftp);
		result.add(rightp);

		//result.setVisible(true);
	}


}