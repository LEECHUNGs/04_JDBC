package edu.kh.emp.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Properties prop = new Properties();
		
		try {
			
			System.out.print("생성할 파일명 입력 : ");
			String fileName = sc.nextLine();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName);
			
			System.out.println(fileName + ".xml 파일 생성 완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
