package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customException.InvalidChoiceException;
import customException.StudentNotFoundException;
import customsorting.SortStudentByAge;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByName;
import customsorting.SortstudentById;

public class StudentManagementSystemImp implements StudentManagementSystem {
	Scanner scan = new Scanner(System.in);
	Map<String, Student> db = new LinkedHashMap<String, Student>();

	@Override
	public void addStudent() {
		System.out.println("Enter Student Age:");
		int age = scan.nextInt();
		System.out.println("Enter Student Name:");
		String name = scan.next();
		System.out.println("Enter Student Marks:");
		int marks = scan.nextInt();

		Student std = new Student(age, name, marks);

		db.put(std.getId(), std);

		System.out.println("Student Record Inserted Successfully");

		System.out.println("Student Id is " + std.getId());

	}

	@Override
	public void displayStudent() {
		// Accept Student Id -> jsp101 or jsp102 or jsp103
		System.out.println("Enter Student Id:");
		String id = scan.next();// String name = scan.next().toUpperCase()
		id = id.toUpperCase();

		if (db.containsKey(id) == true) {// checking if id is present or not
			Student std = db.get(id);// getting the student object
			System.out.println("Id:" + std.getId());
			System.out.println("Age:" + std.getAge());
			System.out.println("Name:" + std.getName());
			System.out.println("Marks:" + std.getMarks());
			// System.out.println(std); as toString() is Overridden
		} else {
			try {
				String message = "Student with the Id" + id + "is not Found!";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	@Override
	public void displayAllStudent() {
		if (db.size() != 0) {
			System.out.println("Students details are as follows");
			System.out.println("_______________________________");
			Set<String> keys = db.keySet();// Jsp101 Jsp102
			for (String key : keys) {
				Student std = db.get(key);
				System.out.println(std);
				// System.out.println(db.get(key));
			}
		} else {
			try {
				throw new StudentNotFoundException("Student DB is Empty nothing to display");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() {
		System.out.println("Enter Student id");
		String id = scan.next();
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			System.out.println("Student Record Found!!");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student Record Deleted Successfully!");
		} else {
			try {
				String message = "Student with the Id " + id + " is not Found!";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudent() {
		if (db.size() != 0) {
			System.out.println("student records availble " + db.size());
			db.clear();
			System.out.println("All Student records deleted successfully");
			System.out.println("student records availble " + db.size());

		} else {

			try {
				String message = "Student DB is Empty nothing to delete!!";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

	@Override
	public void updateStudent() {
		// Accept Student Id -> jsp101 or jsp101 or jsp101
		System.out.println("Enter Student Id:");
		String id = scan.next();// String name = scan.next().toUpperCase()
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			Student std = db.get(id);

			System.out.println("1:Update Age\n2:Update Name\n3: Update Marks");
			System.out.println("Enter Choice:");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Present age is " + std.getAge());
				System.out.println("Enter Age to be Update:");
				int age = scan.nextInt();
				std.setAge(age);// std.setAge(scan.nextInt());
				System.out.println("Updated age is " + std.getAge());

				break;

			case 2:
				System.out.println("Enter Name:");
				String name = scan.next();
				std.setName(name);// std.setName(scan.next());
				break;

			case 3:
				System.out.println("Enter Marks:");
				int marks = scan.nextInt();
				std.setMarks(marks);// std.setMarks(scan.nextInt());
				break;

			default:
				try {
					String message = "Invalid choice, Kindly enter valid choice!";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		} else {

			try {
				String message = "Student with the Id " + id + " is not Found!";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

	@Override
	public void countStudent() {

		System.out.println("The number of Students in DB are " + db.size());
	}

	@Override
	public void sortStudent() {
		Set<String> keys = db.keySet();
		List<Student> list = new ArrayList<Student>();
		for (String key : keys) {
			list.add(db.get(key));
			// System.out.println(keys);
		}
		System.out.println("1:Sort By Id\n2:Sort By Age");
		System.out.println("3:Sort By Name\n4:Sort By Marks");
		System.out.println("Enter Choice");
		int choice = scan.nextInt();

		switch (choice) {
		case 1:
			Collections.sort(list, new SortstudentById());
			display(list);

			break;

		case 2:
			Collections.sort(list, new SortStudentByAge());
			display(list);

			break;

		case 3:
			Collections.sort(list, new SortStudentByName());
			display(list);

			break;

		case 4:
			Collections.sort(list, new SortStudentByMarks());
			display(list);
			break;

		default:
			try {
				String message = "Invalid Enter , Kindly enter valid choice";
				throw new InvalidChoiceException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private static void display(List<Student> list) {
		for (Student s : list) {
			System.out.println(s);
		}
	}

	@Override
	public void getStudentWithHigestMarks() {

		if (db.size() >= 2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(list.size() - 1));// getting student object
		} else {

			try {
				String message = "No sufficient Students to compare";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

	@Override
	public void getStudentWithLowestMarks() {
		if (db.size() >= 2) {

			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(0));// getting student object
		} else {

			try {
				String message = "No sufficient Students to compare";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
