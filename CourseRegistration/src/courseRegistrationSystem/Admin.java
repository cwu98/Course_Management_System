package courseRegistrationSystem;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class Admin extends User implements AdminInterface{
	public static String username = "Admin";
	public static String password = "Admin001";

	/**
	 * create a new course
	 * @param name
	 * @param id
	 * @param max students
	 * @param current number of students
	 * @param students enrolled
	 * @param instructor
	 * @param section
	 * @param location 
	 */
	public void createCourse(String name, String id, int maxStudents, int currNumStudents, String studentsEnrolled,
			String instructor, String section, String location) {
		Course newCourse = new Course(name,id,maxStudents,currNumStudents,studentsEnrolled,instructor,section,location);
		System.out.println("New course created!");
	}
	
	/**
	 * delete a course
	 * @param course ID
	 * @param course section
	 */
	public void delCourse(String id, String section) {
		for (Course course : Course.CourseList) {
			if (course.toString().contains(id) && course.toString().contains(section)) {
				Course.CourseList.remove(course);
				System.out.println("Course deleted!");
			}
		}
	}
	
	/**
	 * edit a course (change either course name, id, or instructor)
	 */
	public void editCourse(String id, String section) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to (1)change course name (2)change course ID or (3)change instructor name ?");
		int response = sc.nextInt();
		for (Course course : Course.CourseList) {
			if (course.toString().contains(id) && course.toString().contains(section)) {
				String change;
				switch (response){
					case 1:
						System.out.print("Enter new course name: ");
						change = sc.next();
						course.setCourseName(change);
						System.out.println("New course name has been set");
						break;
					case 2: 
						System.out.print("Enter new course ID: ");
						change = sc.next();
						course.setCourseID(change);
						System.out.println("New course ID has been set");
						break;
					case 3: 
						System.out.print("Enter new instructor name: ");
						change = sc.next();
						course.setCourseInstructor(change);
						System.out.println("New instructor has been set");
						break;
				}
			}
		}
		sc.close();
	}
	/**
	 * Displays information on a given course
	 * @param courseID
	 */
	public void viewCourseInformation(String courseID) {
		boolean found = false; 
		for(Course course : Course.CourseList) {
			found = true;
		}
		if(found) {
			Course.printHeadings();
			for(Course course : Course.CourseList) {
				if(course.toString().contains(courseID)) {
					System.out.println(course.toString());
		}
		
			}
		}
		else {
			System.out.println("Course not found");
		}
	}
	/**
	 * register a student
	 * @param student first name
	 * @param student last name
	 */
	public void registerStudent(String first, String last) {
		Student s = new Student(first,last);
		System.out.println("New student has been registered");
		System.out.println("The username for this student is: " + s.getUsername());
		System.out.println("The password for this student is " + s.getPassword());
	}
	/**
	 * view list of courses
	 */
	public void viewAllCourses() {
		Course.printHeadings();
		Course.printCourseChart();
	}
	
	/**
	 * view list of FULL courses
	 */
	public void viewFullCourses() {
		try {
			FileReader fr = new FileReader("src/CoursesFull.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * add to full courses list and write to full courses file
	 */
	public void addToCoursesFull(String courseID, String courseSection) {
		for (Course course : Course.CourseList) {
			if (course.toString().contains(courseID) && course.toString().contains(courseSection)) {
				Course.FullCourses.add(course);
			}
		}
		try {
			FileWriter fw = new FileWriter ("src/CoursesFull.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.format("%-50s%-20s%-20s%-20s%-30s%-50s%-20s%-20s", "Course Name", "Course ID", "Max Students", "Current Students", "List of Names", "Course Instructor", "Course Section", "Course Location"));
			bw.newLine();
			for (Course course : Course.FullCourses) {
				bw.write(course.toString());
				bw.newLine();
			}	
			bw.close();
			System.out.println("Course has been added to CoursesFull.txt file");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * View the names of the students being registered in a specific course 
	 * @param course id
	 * @param course section
	 */
	public void studentsRegisteredInCourse(String id, String section) {
		String list = "";
		for (Course course : Course.CourseList) {
			if(course.toString().contains(id) && course.toString().contains(section)) {
				System.out.println("Students registered in this course is/are: ");
				for(Student student : course.getListOfStudents()) {
					list += student.toString() + ", ";
				}
				System.out.println(list.substring(0,list.length()-2));
			}
			else {
				System.out.println("Course not found!");
			}
		}
	}
	
	/**
	 * gets the list of courses that a given student is registered in
	 * @param String firstname, String lastname
	 * @return ArrayList of enrolled courses
	 */
	public void coursesStudentIsRegisteredIn(String first, String last) {
		for(Student student : Student.registeredStudents) {
			if(student.toString().contains(first + " " + last)) {
				ArrayList<Course> enrolledIn = student.getEnrolledCourses();
				System.out.println("Student " + first + " " + last + " is enrolled in: ");
				Course.printHeadings();
				for (Course course : enrolledIn) {
					System.out.println(course.toString());
				}
			}
			else {
				System.out.println("Student " + first + " " + last + " not found.");
			}
		}
	}
	
	/**
	 * displays Admin menu
	 */
	public void viewAdminMenu() {
		System.out.println("Which action would you like to perform? Please enter a number: \n"
				+ "(1) Create a new course \n"
				+ "(2) Delete a course \n"
				+ "(3) Edit a course \n"
				+ "(4) Display information for a given course \n"
				+ "(5) Register a student \n"
				+ "(6) View all courses \n"
				+ "(7) View all FULL courses \n"
				+ "(8) Write to 'CoursesFull' file \n"
				+ "(9) View the names of the students being registered in a specific course \n"
				+ "(10) View list of courses a student is registered in \n"
				+ "(11) Sort courses based on number of students registered \n"
				+ "(12) Exit");
	}
	
	public Admin() {
	}
	
}
