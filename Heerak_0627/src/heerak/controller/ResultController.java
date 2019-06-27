package heerak.controller;



import java.util.ArrayList;

import heerak.model.vo.EventDate;
import heerak.model.vo.User;

/**
 * @author 이경희
 * @Date 2019. 6. 27.
 * <pre>
 * 1. 처리내용 ---- > 투표한 유저리스트, 데이트리스받아서 날짜별 버튼 카운트 새는 getResult
 *                  유저별 데이트리스트에 투표한 값 보여주는 nameResult
 *                  투표결과 선정된 날짜 출력해주는 finalResult                 
 * </pre>
 */

public class ResultController {
   
   
   public String[][] getResult(ArrayList<String> dateList, ArrayList<User> userList){
      ArrayList<String> resultList = new ArrayList<>(); //결과값 담을 리스트
      
      for(int i = 0; i < dateList.size(); i++) {
         String result = "";
         int oBtn = 0;
         int semoBtn = 0;
         int xBtn = 0;
         
         
         for(int j = 0; j < userList.size(); j++) {
            EventDate d = (EventDate)userList.get(j).getVoteList().get(i);
            if(d.getWhat_button() == 1) { //i번째 투표리스트중 버튼의 값(int형)을 가지고와서 비교
               oBtn += 1;
            } else if(d.getWhat_button() == 2) {
               semoBtn += 1;
            } else if(d.getWhat_button() == 3) {
               xBtn += 1;
            }
         }
         result += dateList.get(i) + "," + oBtn + "," + semoBtn + "," + xBtn;
         resultList.add(result); //데이트리스트의 각 날짜마다 투표값을 뽑아내고 resultList에 대입
      }
      
      String[][] resultArr = new String[resultList.size()][4]; //j는 날짜, 오, 세모, 엑스 (4개의 요소만 들어감)
      
      for(int i = 0; i < resultArr.length; i++) {
         for(int j = 0; j < resultArr[i].length; j++) {
            resultArr[i][j] = resultList.get(i).split(",")[j];
         }
      }
      
      return resultArr; //날짜별 투표값이 담긴 이차원 배열 반환
   }
   
   public String[][] nameResult(ArrayList<String> dateList, ArrayList<User> userList){
      String[][] userInfo = new String[userList.size()][dateList.size()+1]; //날짜별 투표자 이름,오,세모,엑스가 들어가기때문에 +1
      
      for(int i = 0; i < userInfo.length; i++) {
         
         for(int j = 0; j < userInfo[i].length; j++) {
            
            if(j == 0) {
               //유저리스트를 i번 반복할때 이름가져오기(1번째 열은 이름만 가져옴)
               userInfo[i][j] = userList.get(i).getName(); 
               
            } else if(j != 0) {
               //이름을 제외한 나머지 열은 유저리스트(i번반복할동안)에서
               //보트리스트 가져오고, 해당 투표날을 가져오는데 -1해서 가져와야(j = 0 은 이름이 가져갔기 때문) 
               //date의 첫번째 날짜부터 가져올수 있음
               userInfo[i][j] = (" " + ((EventDate) userList.get(i).getVoteList().get(j-1)).getWhat_button()); //
            
            }
         }
      }
      
      return userInfo;
   }
   
   public String resultOfEvent(ArrayList<String> dateList, ArrayList<User> userList) {
      ResultController rc = new ResultController();
      
      int oMax = 0; //각버튼별 개수의 최대값 구하는데 이용할 변수
      int semoMax = 0;
      int xMax = 0;
      int index = 0;
      String result = ""; //결과에 해당하는 날짜가 입력될 변수
      
      String[][] resultArr = rc.getResult(dateList, userList);
      
      for(int i = 0; i < resultArr.length; i++) {
         int oCount = Integer.parseInt(resultArr[i][1]); //스트링값으로 들어가있는 각 버튼의 개수를 인트로 변환
         int semoCount = Integer.parseInt(resultArr[i][2]);
         int xCount = Integer.parseInt(resultArr[i][3]);
               
         if(oMax < oCount) {
            oMax = oCount;
            result = resultArr[i][0]; //인덱스번째 배열의 0번째값은 날짜
         }
         else if(oMax == oCount) { //최댓값과 그 다음 날짜의 oCount값이 같다면(o카운트가 같은 날짜가 중복이라면)
            if(semoMax < semoCount) { //세모카운트 최댓값 비교
               semoMax = semoCount;
               result += (" " + resultArr[i][0]);  //세모카운트가 같지 않다면 오 카운트가 같은 날짜까지만 포함
            }
            else if(semoMax == semoCount) { //오 카운트가 같고 세모 카운트도 같다면
               if(xMax < xCount) { // 엑스 카운트 비교
                  xMax = xCount;
                  result += (" " + resultArr[i][0]); //엑스 카운트가 같지 않으면 세모카운트가 같은 날짜까지만 포함
               }
               else if(xMax == xCount) { //엑스 카운트 값 같은 날짜도 중복이라면
                  result += (" " + resultArr[i][0]); // 이날짜도 결과값에 포함
               }
            }
         }
      }
      return result;
      
   }
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      ArrayList<String> dateList = new ArrayList<>();
      ArrayList<User> userList = new ArrayList<>();
      
      ArrayList<EventDate> vote1 = new ArrayList<>();
      vote1.add(new EventDate("2019/06/21",1));
      vote1.add(new EventDate("2019/06/22",1));
      vote1.add(new EventDate("2019/06/23",3));
      User u1 = new User("user1",vote1,"반갑습니다.");
            
      ArrayList<EventDate> vote2 = new ArrayList<>();
      vote2.add(new EventDate("2019/06/21",1));
      vote2.add(new EventDate("2019/06/22",1));
      vote2.add(new EventDate("2019/06/23",3));
      User u2 = new User("user2",vote2,"빨리만나");
      
      ArrayList<EventDate> vote3 = new ArrayList<>();
      vote3.add(new EventDate("2019/06/21",2));
      vote3.add(new EventDate("2019/06/22",2));
      vote3.add(new EventDate("2019/06/23",1));
      User u3 = new User("user3",vote3,"빨리만나");
      
      ArrayList<EventDate> vote4 = new ArrayList<>();
      vote4.add(new EventDate("2019/06/21",1));
      vote4.add(new EventDate("2019/06/22",1));
      vote4.add(new EventDate("2019/06/23",1));
      User u4 = new User("user4",vote4,"빨리만나 ");
      
      userList.add(u1);
      userList.add(u2);
      userList.add(u3);
      userList.add(u4);
      
      dateList.add("2019/06/21"); //일단 임의로 넣은 날짜 
      dateList.add("2019/06/22");
      dateList.add("2019/06/23");
      
      ResultController rc = new ResultController();
      
      String[][] result = new String[dateList.size()][4];
      result = rc.getResult(dateList, userList);
      
      for(int i = 0; i < dateList.size(); i++) {
         for(int j = 0; j < 4; j++) {
            System.out.print(result[i][j] + " ");
         }
         System.out.println();
      }
      
      System.out.println();
      
      String[][] result1 = rc.nameResult(dateList, userList);
      for(int i = 0; i < result1.length; i++) {
         for(int j = 0; j < result1[i].length; j++) {
            System.out.print(result1[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println();
      
      System.out.println(rc.resultOfEvent(dateList, userList));
   }

   
}
