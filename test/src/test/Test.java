package test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		String a = "12345";
		
		try {
			a.substring(3,6);
		} catch (Exception e) {
			System.out.println("초과");
		}
		
	}

}
