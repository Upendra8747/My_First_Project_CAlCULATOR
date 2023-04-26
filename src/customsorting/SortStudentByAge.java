package customsorting;

import java.util.Comparator;

import sdbms.Student;

public class SortStudentByAge implements Comparator<Student>{
	@Override
	public int compare(Student s1, Student s2) {
		return s1.getAge()-s2.getAge();

	}

}
