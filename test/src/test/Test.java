package test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String today = "2020.01.01";
		String[] terms = {"Z 3", "D 5"};
		String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
		List<Integer> list = new ArrayList<Integer>();
		
		String[] todayArray = today.split("\\.");
	
        int[] todayInt = {Integer.parseInt(todayArray[0]), 
                       Integer.parseInt(todayArray[1]), 
                       Integer.parseInt(todayArray[2])};
        
        char privacie = ' ';
        int[] day = new int[3];
        
        int count = 0;
        int result = 0;
        for(int i = 0; i<privacies.length; i++) {  
            day[0] = Integer.parseInt(privacies[i].substring(0, 4));
            day[1] = Integer.parseInt(privacies[i].substring(5, 7));
            day[2] = Integer.parseInt(privacies[i].substring(8, 10));
            
            privacie = privacies[i].charAt(11);
            
            for(int j = 0; j<terms.length; j++) {
                if(terms[j].charAt(0) == privacie) {
                    day[1] += Integer.parseInt(terms[j].substring(2));
                    if(day[1] > 12) {
                        day[0] += day[1] / 12;
                        day[1] %= 12;
                    }
                    System.out.println("\n" + (i+1));
                    System.out.println(todayInt[0] + " " + todayInt[1] + " " + todayArray[2]);
                    System.out.println(day[0] + " " + day[1] + " " + day[2] + " (+ " + Integer.parseInt(terms[j].substring(2)));
                    System.out.println();
                    
                    break;
                }
            }
            
            if(todayInt[0] > day[0]) list.add(i+1);
            if(todayInt[0] == day[0]) {
            	if(todayInt[1] > day[1]) list.add(i+1);
            	if(todayInt[1] == day[1]) {
            		if(todayInt[2] >= day[2]) list.add(i+1);
            	}
            }
            
            
        }
        
        int[] answer = new int[list.size()];
        
        for(int i = 0; i<list.size(); i++) 
        	answer[i] = list.get(i);
        
        
        for(int i = 0; i<answer.length; i++)
        	System.out.print(answer[i] + " + ");
	}

}
