package heerak.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import heerak.controller.EventController;
import heerak.controller.MainController;
import heerak.model.vo.Event;
import heerak.model.vo.Manager;


/**
 * @author 수진
 * @Date 2019. 6. 27.
 * <pre>
 * 1.처리내용
 * </pre>
 */
public class ManagerView {
	//관리자창 
	
	//1. 로그인 - mc.login() - 
	//			실패하면((아이디만 확인후)팝업보여주고 로그인화면) - mc.join()
	//  		성공하면 마이이벤트, 이벤트생성 버튼(창 띄어줌)
	//2. 마이이벤트 클릭시 
	//			이벤트없으면 팝업창 - 이벤트창으로..
	//			이벤트있으면 이벤트리스트 출력
	//2-1. 이벤트리스트
	//			이벤트이름 클릭하면 상세내용출력하고 삭제,마감가능
	//			삭제누르면 삭제(deleteEvent()) - 삭제하시겠습니까?
	//				y누르면 (삭제되었습니다.), 이벤트리스트창 호출(n도 똑같)
	//			마감누르면 마감(closeEvent()) - 마감하시겠습니까?
	//				위와 같고, event의 close를 true
	//3. 이벤트 생성 클릭스
	//			System.out.println("이벤트명") 등등 출력하고 
	//			값 입력받고 생성누르면 Event객체 생성(new)
	//			날짜(시작날짜, 끝날짜 입력받고) 날짜 차이 일 수
	
	private Scanner sc = new Scanner(System.in);
	private Manager m ;
	private MainController mc = new MainController();
	private EventController ec = new EventController();
	private JFrame def = new JFrame();
	private JFrame log = new JFrame();
	private JFrame join = new JFrame();
	private JFrame main = new JFrame();
	private JFrame add = new JFrame();
	
	public void defaultView() {
		
		//JFrame def = new JFrame();
		
		def.setTitle("희희락락");
		def.setBounds(350,350,300,450);
		def.getContentPane().setBackground(Color.GRAY);
		def.setLayout(null);
		def.setResizable(false);
		
		JButton man = new JButton("관리자");
		man.setFont(new Font("맑은 고딕",Font.BOLD,20));
		man.setBackground(Color.WHITE);
		man.setLocation(70, 70);
		man.setSize(150,100);
		JButton user = new JButton("사용자");
		user.setFont(new Font("맑은 고딕",Font.BOLD,20));
		user.setBackground(Color.WHITE);
		user.setLocation(70,250);
		user.setSize(150,100);
		
		
		man.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == man) {
					loginView();
				}
			}
		});
		
		user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == user) {
					System.out.println("사용자 메뉴는 나한테 없엉ㅠ_ㅠ 바보야");
				}
				
			}
		});
		
		
		def.add(man);
		def.add(user);
		
		def.setVisible(true);
		def.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void loginView() {
		//def.setVisible(false);
		
		//Test
		mc.makeAccount("이수진", "user1", "pass123", "esuhyj@gmail.com","010-1234-5678");
		m = new Manager("이수진","user1","pass123","esuhyj@gmail.com","010-1234-5678"); 
		
		log.setTitle("L O G I N");
		log.setBounds(400,400,470,280);
		log.getContentPane().setBackground(Color.LIGHT_GRAY);
		log.setLayout(null);
		log.setResizable(false);
		
		JLabel idl = new JLabel("        ID   ");
		JLabel passl = new JLabel("PASSWORD");
		
		idl.setLocation(50,40);
		idl.setSize(100,50);
		idl.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		passl.setLocation(50,100);
		passl.setSize(100,50);
		passl.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField idf = new JTextField(20);
		JTextField psf = new JTextField(20);
		
		idf.setLocation(170,50);
		idf.setSize(120,30);
		
		psf.setLocation(170,110);
		psf.setSize(120,30);
		
		JButton logB = new JButton("LOGIN");
		logB.setLocation(320,57);
		logB.setSize(100,80);
		logB.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JLabel jlabel = new JLabel("회원가입 하시겠습니까?  |");
		jlabel.setLocation(100,180);
		jlabel.setSize(200,50);
		jlabel.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JButton joinB = new JButton("JOIN");
		joinB.setLocation(290,193);
		joinB.setSize(70,30);
		joinB.setFont(new Font("맑은 고딕",Font.BOLD,13));
		
		log.add(idl);
		log.add(passl);
		log.add(idf);
		log.add(psf);
		log.add(logB);
		log.add(jlabel);
		log.add(joinB);
		
		/*String id = idf.getText();
		String pwd = psf.getText();*/
		System.out.println(mc.getList());
		logB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == logB) {
					String id = idf.getText();
					String pwd = psf.getText();
					int log_check = mc.accessAccount(id, pwd);	// MainController의 accessAccount(id,pwd)를 호출해서 리턴받은 값 
					// ex) 1 : 해당하는 아이디가 없으므로 회원가입권장 팝업창
					//	   2 : 아이디는 맞지만  패스워드가 맞지 않으면 패스워드를 확인하라는 팝업창
					//     3 : 로그인 성공 --> 관리자의 메인메뉴로(마이이벤트, 이벤트생성)
					//     4 : [JOIN] 버튼을 눌렀을 때
					System.out.println(log_check);
					switch(log_check) {
					case 1 :
						// 팝업창
						JOptionPane.showMessageDialog(log, "해당하는 아이디가 없습니다.\n회원가입 해주세요!", "Error",JOptionPane.INFORMATION_MESSAGE);
						System.out.println("해당하는 아이디가 없습니다.회원가입해주세요^_^ 힝!");
						break;
					case 2 :
						// 팝업창
						JOptionPane.showMessageDialog(log, "  비밀번호가 틀립니다!", "Error",JOptionPane.ERROR_MESSAGE);
						System.out.println("패스워드 확인 후 다시 로그인 해주세요!");
						break;
					case 3 :
						//m = mc.getManager(id,pwd); //서 로그인 정보에 해당하는 Manager 객체를 리턴받아 사용
						System.out.println(m);
						managerMainView();
						break;
					default :
						JOptionPane.showMessageDialog(log, "해당하는 아이디가 없습니다.\n회원가입 해주세요!", "Error",JOptionPane.INFORMATION_MESSAGE);
						break;
						
					}
				}
			}
		});
		
		joinB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				joinView();
			}
		});
		
		
				
		log.setVisible(true);
		//log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void joinView() {
		
	
		join.setTitle("J O I N");
		join.setBounds(400,400,300,500);
		join.getContentPane().setBackground(Color.LIGHT_GRAY);
		join.setLayout(null);
		join.setResizable(false);
		
		JLabel idl = new JLabel("      ID");
		idl.setLocation(30,70);
		idl.setSize(100,50);
		idl.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JLabel passl = new JLabel("PASSWORD");
		passl.setLocation(30,120);
		passl.setSize(100,50);
		passl.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JLabel namel = new JLabel("	    NAME");
		namel.setLocation(30,170);
		namel.setSize(100,50);
		namel.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JLabel emal = new JLabel("   EMAIL");
		emal.setLocation(30,220);
		emal.setSize(100,50);
		emal.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JLabel phl = new JLabel("   PHONE");
		phl.setLocation(30,270);
		phl.setSize(100,50);
		phl.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField idtf = new JTextField(20);
		idtf.setLocation(135,80);
		idtf.setSize(120,30);
		
		JTextField pwtf = new JTextField(20);
		pwtf.setLocation(135,130);
		pwtf.setSize(120,30);
		
		JTextField nametf = new JTextField(20);
		nametf.setLocation(135,180);
		nametf.setSize(120,30);
		
		JTextField etf = new JTextField(20);
		etf.setLocation(135,230);
		etf.setSize(120,30);
		
		JTextField phtf = new JTextField(20);
		phtf.setLocation(135,280);
		phtf.setSize(120,30);
		
		JButton ok = new JButton("O   K");
		ok.setLocation(60,350);
		ok.setSize(180,30);
		ok.setFont(new Font("맑은 고딕",Font.BOLD,15));
		//ok.setBackground(Color.WHITE);
				
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == ok) {
					String name = nametf.getText();
					String id = idtf.getText();
					String pwd = pwtf.getText();
					String email = etf.getText();
					String phone = phtf.getText();
					mc.makeAccount(name, id, pwd, email, phone);
					join.setVisible(false);
				}
				
			}
		});
		
		join.add(idl);
		join.add(passl);
		join.add(namel);
		join.add(emal);
		join.add(phl);
		
		join.add(idtf);
		join.add(pwtf);
		join.add(nametf);
		join.add(etf);
		join.add(phtf);
		
		join.add(ok);
		
		join.setVisible(true);
		//join.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.out.println("회원가입이 완료되었습니다.");
		/*System.out.println(id + " " + pwd + " " + name + " " + email + " " + phone);*/
		
	}
	
	public void managerMainView() {
	
		log.setVisible(false);
		
		main.setTitle("M A I N");
		main.setBounds(400,400,400,250);
		main.getContentPane().setBackground(Color.WHITE);
		main.setLayout(null);
		main.setResizable(false);
		
		ImageIcon my = new ImageIcon("my.gif");
		JButton myB = new JButton(my);
		myB.setBackground(Color.WHITE);
		myB.setBorderPainted(false);
		myB.setLocation(80, 50);
		myB.setSize(100, 100);
		
		ImageIcon make = new ImageIcon("make.gif");
		JButton makeB = new JButton(make);
		makeB.setBackground(Color.WHITE);
		makeB.setBorderPainted(false);
		makeB.setLocation(220, 50);
		makeB.setSize(100, 100);
		
		myB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == myB) {
					myEventView();
				}
				
			}
		});
		
		makeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == makeB) {
					addEventView();
				}
				
			}
		});
		main.add(myB);
		main.add(makeB);
		
		
		main.setVisible(true);
		
		
	}
	
	public void myEventView() {
		System.out.println(m);
		
		
	}
	
	public void addEventView() {
		
		
		add.setTitle("ADD EVENT");
		add.setBounds(350,350,500,580);
		add.getContentPane().setBackground(Color.LIGHT_GRAY);
		add.setLayout(null);
		add.setResizable(false);
		
		JLabel title = new JLabel(" Event Name");
		title.setLocation(30,100);
		title.setSize(100,50);
		title.setFont(new Font("맑은 고딕",Font.BOLD,15));

			
		JLabel detail = new JLabel("  Detail");
		detail.setLocation(30,150);
		detail.setSize(100,50);
		detail.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JLabel date = new JLabel("   	Date");
		date.setLocation(30,260);
		date.setSize(100,50);
		date.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField ttf = new JTextField(20);
		ttf.setLocation(150,110);
		ttf.setSize(200,30);
		
		JTextField dtf = new JTextField(100);
		dtf.setLocation(150, 165);
		dtf.setSize(200,90);
		
		JTextField start_y = new JTextField(100);
		start_y.setLocation(150, 275);
		start_y.setSize(60,30);
		
		JLabel y1 = new JLabel("년");
		y1.setLocation(215,262);
		y1.setSize(100,50);
		y1.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField start_m = new JTextField(100);
		start_m.setLocation(245, 275);
		start_m.setSize(60,30);
		
		JLabel m1 = new JLabel("월");
		m1.setLocation(310,262);
		m1.setSize(100,50);
		m1.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField start_d = new JTextField(100);
		start_d.setLocation(340, 275);
		start_d.setSize(60,30);
		
		JLabel d1 = new JLabel("일");
		d1.setLocation(405,262);
		d1.setSize(100,50);
		d1.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		
		JLabel and = new JLabel("~");
		and.setLocation(152,317);
		and.setSize(100,50);
		and.setFont(new Font("맑은 고딕",Font.BOLD,18));
		
		JTextField end_y = new JTextField(100);
		end_y.setLocation(180, 330);
		end_y.setSize(60,30);
		
		JLabel y2 = new JLabel("년");
		y2.setLocation(245,317);
		y2.setSize(100,50);
		y2.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField end_m = new JTextField(100);
		end_m.setLocation(275, 330);
		end_m.setSize(60,30);
		
		JLabel m2 = new JLabel("월");
		m2.setLocation(340,317);
		m2.setSize(100,50);
		m2.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JTextField end_d = new JTextField(100);
		end_d.setLocation(370, 330);
		end_d.setSize(60,30);
		
		JLabel d2 = new JLabel("일");
		d2.setLocation(435,317);
		d2.setSize(100,50);
		d2.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
		JButton addok = new JButton("Add Event");
		addok.setLocation(167, 410);
		addok.setSize(150, 70);
		
		addok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == addok) {				
					String title = ttf.getText();
					String detail = dtf.getText();
					String startm = "";
					String startd = "";
					String endm = "";
					String endd = "";
					if(start_m.getText().length() == 1) {
						startm = "0" + start_m.getText();
					}
					else {
						startm = start_m.getText();
					}
					if(start_d.getText().length() == 1) {
						startd = "0" + start_d.getText();
					}
					else {
						startd = start_d.getText();
					}
					if(end_m.getText().length() == 1) {
						endm = "0" + end_m.getText();
					}
					else {
						endm = end_m.getText();
					}
					if(end_d.getText().length() == 1) {
						endd = "0" + end_d.getText();
					}
					else {
						endd = end_d.getText();
					}
		
					String start_day = start_y.getText() + "/" + startm + "/" + startd;
					String end_day = end_y.getText() + "/" + endm  + "/" + endd;
				
					
					ArrayList eDate = ec.getEDate(start_day, end_day);
					String accCode = ec.createAccCode();
					String accShow = String.format("해당 이벤트의 참여코드는 \'%s\' 입니다!",accCode);
					ec.createEvent(m, new Event(title,detail,eDate,false,accCode));
					//System.out.println(m);
					add.setVisible(false);
					JOptionPane.showMessageDialog(main, accShow, "ACCESS CODE",JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		
		add.add(title);
		add.add(detail);
		add.add(ttf);
		add.add(dtf);
		add.add(date);
		add.add(start_y);
		add.add(start_m);
		add.add(start_d);
		add.add(end_y);
		add.add(end_m);
		add.add(end_d);
		add.add(y1);
		add.add(y2);
		add.add(m1);
		add.add(m2);
		add.add(d1);
		add.add(d2);
		add.add(and);
		add.add(addok);
		
		add.setVisible(true);
		
	
		
	}
	
	public void showEventList() {
		// eventList = o.getEvetnList(m1)을 한번더 호출 --> 출력하기위해
		//JFrame을 이용해서 리스트를 출력한다.
		System.out.println("\tMY EVENT LIST");
		ArrayList eventList = new ArrayList();
		for(int i = 0 ; i < eventList.size() ;i++) {
			System.out.println(eventList.get(i)); // Title과 마감여부를 같이 출력 ( Title  [마감] )
		}										// Title을 눌렀을때 이벤트에 대한 정보를 알수있도록.
												// 그러면 상세창에서 [삭제]버튼을 만들어야쥐
		
	}
	
	public void showEventInfo() {
		
	}
	
	
	
}
