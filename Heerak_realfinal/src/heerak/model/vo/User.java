package heerak.model.vo;

import java.util.ArrayList;

public class User {
	//이름, 버튼(세개), 코드(입력하는 코드)
	private String name;
	/*private int oButton;
	private int xButton;
	private int semoButton;*/
	
	private String accCode;
	private String comment ;
	private ArrayList<EventDate> voteList = new ArrayList<>(); // 안에는 date객체들 ("2019.04.03",1)
	public User() {}

	public User(String name, ArrayList voteList ,String comment,String accCode) {
		this.name = name;
		this.voteList = voteList;
		this.comment = comment;
		this.accCode = accCode;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArrayList getVoteList() {
		return voteList;
	}

	public void setVoteList(ArrayList voteList) {
		this.voteList = voteList;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", comment=" + comment + ", voteList=" + voteList + "]";
	}
	
	

	
	
	
}
