package heerak.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import heerak.model.vo.Event;
import heerak.model.vo.Manager;

/**
 * @author 상욱햄
 * @Date 2019. 6. 27.
 * <pre>
 * 1.처리내용
 * </pre>
 */
public class EventController implements EventInterface{
	
	//이벤트 관리
		//이벤트 생성 - 이벤트별 코드 생성
		//이벤트 삭제
		//이벤트 마감
		//진행, 마감 확인(코드)
		//이벤트 리스트 넘겨주기(printAll)
		//
	
	
	
	/**
	 * 
	 */
	private ArrayList eventList = new ArrayList();
	@Override
	public void createEvent(Manager m , Event e) {
		// 해당 관리자의 evnetList에 생성한 event를 하나씩 넣어줘야하지 않을까?
		m.getEventList().add(e);
		
		eventList.add(e); 
		
		//여기서 m의 event가 하나씩 추가되는거니까 m의 event count변수를 ++
		m.setCount(m.getCount()+1);
	}

	// 관리자에 해당하는 event리스트를 반환해주는 메소드
	public ArrayList getEventList(Manager m) {
		return m.getEventList();
	} 
	
	public ArrayList getAllEvent() {
		return eventList;
	}
	public String createAccCode() {
		Random rnd =new Random(); // 랜덤 클래스 사용
		StringBuffer buf =new StringBuffer();
		for(int i=0;i<6;i++){
		    // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를
			// false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97)); // 알파벳(소문자) 랜덤값 추가
		    }else{
		        buf.append((rnd.nextInt(10))); // 0<=~<n 의 난수를 리턴
		    }
		}
		String accCode = buf.toString();
//		System.out.println(eventList.toString());
//		System.out.println(accCode);
		return accCode; // 팝업??
	}
	
	public Event getEvent(String code) {
		Event e = null;
		for(int i=0; i<eventList.size(); i++) {
			
			if(((Event) eventList.get(i)).getAccCode().equals(code)) {
				e = (Event) eventList.get(i);
			}
		}
		return e;
	}
	
	public void closeEvent(Event e) {
		e.setClose(true);
	}
	
	public void deleteEvent(Manager m,Event e) {
		eventList.remove(e);
		//eventList.remove(m);
		m.getEventList().remove(e);
		m.setCount(m.getCount()-1); // count 또한 하나 줄어들음
	}
	
	// 날짜 범위를 ArrayList에 넣어주는 메소드 (시작날짜, 끝날짜)
	public ArrayList getEDate(String start , String end) {
        List<Date> dates = new ArrayList<Date>();
        ArrayList eDate = new ArrayList();
		String str_date = start;
		String end_date = end;
		
		DateFormat formatter ; 
		
		formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date  startDate = (Date)formatter.parse(str_date); 
			Date  endDate = (Date)formatter.parse(end_date);
			long interval = 24*1000 * 60 * 60; // 1 hour in millis
			long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
			long curTime = startDate.getTime();
			while (curTime <= endTime) {
			    dates.add(new Date(curTime));
			    curTime += interval;
			}
			for(int i=0;i<dates.size();i++){
			    Date lDate =(Date)dates.get(i);
			    String ds = formatter.format(lDate);
			    eDate.add(ds);
			    //System.out.println(" Date is ..." + ds);
			}
		}
		catch (Exception e) {
		
		}
		
		return eDate;
	}

	
	
	
	
	
	


	
	
}
