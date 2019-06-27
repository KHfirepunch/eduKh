package heerak.model.vo;

import java.util.ArrayList;

public class Manager {
	//이름, 아이디, 비밀번호, 이메일, 전화번호 - 회원가입에 필요한 필드
	//코드(이벤트 생성하고 관리자가 받는 코드)
	private String name;
	private String id;
	private String pwd;
	private String email;
	private String phone;
	//관리자도 이벤트 리스트를 가지고 있어야 관리자에게 해당하는 이벤트 리스트를 보여주지 않을까?
	private ArrayList eventList = new ArrayList();
	private int count = 0 ; 
	
	/*private String manCode;*/
	
	public Manager() {}

	public Manager(String name, String id, String pwd, String email, String phone) { //회원가입 생성자
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
	}

	public Manager(String name, String id, String pwd, String email, String phone, String manCode) { //전체필드 들어간 생성자
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.email = email;

	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList eventList) {
		this.eventList = eventList;
	}

	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Manager [name=" + name + ", id=" + id + ", pwd=" + pwd + ", email=" + email + ", phone=" + phone
				+ ", eventList=" + eventList + ", count=" + count ;
	}

	

	
	
}
