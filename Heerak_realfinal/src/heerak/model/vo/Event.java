package heerak.model.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Event {
	//이벤트명, 이벤트설명, 날짜(배열), 마감여부, 이벤트존재여부(count 이용)
	private String eventTitle;
	private String comment;
	/*private Calendar[] date;*/
	private ArrayList eventDate = new ArrayList();
	private boolean close = false; //마감하면 true로 바꾸기
	/*private static int count; //이벤트존재여부 */
	private String accCode ;
	
	{// event객체가 생성될때 마다 count++됌.
		// manager의 이벤트니까 manager로 가야되는거같다.
		// 해당 관리자의 event가 있는지 확인하는 거니까
		// 해당 관리자의 count++ 해주는 기능이 EventController로 들어가야되는 것 같음
		// 만약에 event객체를 선언할 때 마다 count++ 를 하게되면
		// 다른 관리자1이 event를 생성해서 event =1 이된건데
		// 관리자2가 "myevent"를 확인할때 이벤트가 없지만 event = 1 이라 
		// 팝업창이 나타나지 않는다.
		/*count++;*/
	}
	public Event() {}

	
	
	public Event(String eventTitle, String comment, ArrayList eventDate, boolean close, String accCode) {
		super();
		this.eventTitle = eventTitle;
		this.comment = comment;
		this.eventDate = eventDate;
		this.close = close;
		this.accCode = accCode;
	}



	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArrayList getEventDate() {
		return eventDate;
	}

	public void setEventDate(ArrayList eventDate) {
		this.eventDate = eventDate;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		
		this.accCode = accCode;
	}

	@Override
	public String toString() {
		return "Event [eventTitle=" + eventTitle + ", comment=" + comment + ", eventDate=" + eventDate + ", close="
				+ close + ", accCode=" + accCode + "]";
	}


	
}
