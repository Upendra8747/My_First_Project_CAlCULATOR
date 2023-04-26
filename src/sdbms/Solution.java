package sdbms;

import java.util.Scanner;

import customException.InvalidChoiceException;

public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student DBMS");
		System.out.println("-------------------------------");
		Scanner scan = new Scanner(System.in);
		StudentManagementSystem sms = new StudentManagementSystemImp();
		
		while(true) {
			System.out.println("1:Add Student\n2:Display Student");
			System.out.println("3:Display All Student\n4:Remove Student");
			System.out.println("5:Remove All Student\n6:Update Student");
			System.out.println("7:Count Student\n8:Sort Student");
			System.out.println("9:GetHigestMarksStudent\n10:GetLowestMarksStudent");
			System.out.println("11:Exit\nEnter Your Choice:");
			
			
			int choice = scan.nextInt();
			
			
			switch(choice) {
			case 1:
				sms.addStudent();
				break;
				
			case 2:
				sms.displayStudent();
				break;
				
			case 3:
				sms.displayAllStudent();
				break;
				
			case 4:
				sms.removeStudent();
				break;
				
			case 5:
				sms.removeAllStudent();
				break;
				
			case 6:
				sms.updateStudent();
				break;
				
			case 7:
				sms.countStudent();
				break;
				
			case 8:
				sms.sortStudent();
				break;
				
			case 9:
				sms.getStudentWithHigestMarks();
				break;
				
			case 10:
				sms.getStudentWithLowestMarks();
				break;
				
			case 11:
				System.out.println("Thanku!!");
				System.exit(0);
				
			default:
				try {
					String message="invalid choice, Kindly enter valid choice";
					
					throw new InvalidChoiceException(message);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}// end of switch
			System.out.println("-------------------");
		}//end of while
	}
}
