package heerak.controller;

import java.util.ArrayList;

import heerak.model.vo.Event;
import heerak.model.vo.EventDate;
import heerak.model.vo.User;

public class TestCount {

		public static void main(String[] args) {
			ArrayList eDate = new ArrayList();
			ArrayList<User> userList = new ArrayList<>();
			eDate.add("2019/05/01");
			eDate.add("2019/05/02");
			eDate.add("2019/05/02");
			
			Event e1 = new Event("우리 회식","회식입니다.",eDate,false,"abc");
			//System.out.println(e1);
			
			ArrayList<EventDate> vote1 = new ArrayList<>();
			vote1.add(new EventDate("2019/05/01",1));
			vote1.add(new EventDate("2019/05/02",2));
			vote1.add(new EventDate("2019/05/03",3));
			User u1 = new User("user1",vote1,"반갑습니다.");
					
			ArrayList<EventDate> vote2 = new ArrayList<>();
			vote2.add(new EventDate("2019/05/01",1));
			vote2.add(new EventDate("2019/05/02",2));
			vote2.add(new EventDate("2019/05/03",1));
			User u2 = new User("user2",vote2,"빨리만나");
			
			userList.add(u1);
			userList.add(u2);
			//System.out.println(userList);
			ArrayList resultList = new ArrayList();
			for(int i = 0; i < eDate.size() ;i++) {
				String result = "";
				int oButton = 0;
				int semoButton = 0;
				int xButton = 0;
				for(int j = 0; j < userList.size() ; j++) {
					EventDate d = (EventDate)userList.get(j).getVoteList().get(i);
					if(d.getWhat_button()==1) {
						oButton += 1;
					}
					else if(d.getWhat_button() == 2) {
						semoButton += 1;
					}
					else if(d.getWhat_button() == 3) {
						xButton += 1;
					}
					
				}
				result += (eDate.get(i) + "," + oButton + "," + semoButton + "," + xButton);
				resultList.add(result);
			}
			System.out.println(resultList);
		}
}
