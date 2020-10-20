package courseRegistrationSystem;
import java.util.ArrayList;

public class Student extends User implements StudentInterface{
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private ArrayList<Course> enrolledCourses;
	public static ArrayList<String> listofusernames = new ArrayList<String>();
	public static ArrayList<Student> registeredStudents = new ArrayList<Student>();

	/**
	 * gets first name of student
	 * @return String first name
	 */
	public String getFirst() {
		return this.firstname;
	}
	/**
	 * gets last name of student
	 * @return String last name
	 */
	public String getLast() {
		return this.lastname;
	}
	/**
	 * get student username
	 * @return Strings student's username
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * get student password
	 * @return String student password
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * gets list of courses that student is registered in
	 * @return ArrayList<Course> 
	 */
	public ArrayList<Course> getEnrolledCourses(){
			return this.enrolledCourses;
	}
	/**
	 * prints the courses the student is registered in
	 */
	public void viewEnrolledCourses() {
		if(this.getEnrolledCourses().size() == 0) {
			System.out.println("You are not enrolled in any courses.");
		}
		else{
			Course.printHeadings();
			for(Course course : this.getEnrolledCourses()) {
				System.out.println(course.toString());
		}
			
		}
	}
	
	/**
	 * view all courses
	 */
	public void viewAllCourses() {
		Course.printHeadings();;
		Course.printCourseChart();
	}
	/**
	 * generates username
	 */
	public void generateUsername() {
		this.username = this.firstname + this.lastname + Integer.toString(Student.registeredStudents.size());
		Student.listofusernames.add(this.username);
	}
	/**
	 * generates password
	 */
	public void generatePassword() {
		StringBuilder a = new StringBuilder(this.firstname + this.lastname);
		this.password = a.reverse() + Integer.toString(Student.registeredStudents.size());
	}
	/**
	 * @return Student object from username
	 * @param student username
	 */
	public static Student getStudentFromUsername(String username) {
		if (Student.listofusernames.contains(username)) {
			for(Student student : Student.registeredStudents) {
				if (student.getUsername().equals(username)) {
					return student;
				}
			}
		}
		else {
			System.out.println("Student with this username not found");
			
		}
		return null;
	}
	/**
	 * registers the student into a course
	 * @param CourseName
	 * @param section
	 * @param firstname
	 * @param lastname
	 */
	public void registerForACourse(String CourseName, String section, String first, String last ) {
		for (Course course : Course.CourseList) {
			if(course.toString().contains(CourseName) && course.toString().contains(section)) {
				if(course.isFull() == false) {
				this.enrolledCourses.add(course);
				course.addStudent(this);
				System.out.println("You have enrolled in the course: " + course.getCourseName() + " Section " + course.getCourseSection());
				}
				else {
					System.out.println("Unable to register for this section of this course: course is full. ");
				}
			}
		}
	}
	/**
	 * withdraw from a course
	 * @param CourseName
	 * @param section
	 * @param first name
	 * @param last name
	 */
	public void withdrawFromACourse(String CourseName, String section, String firstname, String lastname) {
		for(Course course : Course.CourseList) {
			if(course.toString().contains(CourseName) && course.toString().contains(section)) {
				this.enrolledCourses.remove(course);
				course.delStudent(this);
				}
		}
	}
	/**
	 * prints all courses that are not full
	 */
	public void viewAvailableCourses() {
		System.out.println("Available Courses: ");
		Course.printHeadings();
		for (Course course : Course.AvailableCourses) {
			System.out.println(course);
		}
	}
	
	/**
	 * Print Student menu
	 */
	public void viewStudentMenu() {

		System.out.println("Which action would you like to perform? Please enter a number: \n"
				+ "(1) View all courses that are available \n"
				+ "(2) View all courses that are not FULL \n"
				+ "(3) Register on a course \n"
				+ "(4) Withdraw from a course \n"
				+ "(5) View all courses you're registered in");
		
	}

	/**
	 * Student constructor
	 * @param String first name
	 * @param String last name
	 */
	public Student(String first, String last) {
		this.firstname = first;
		this.lastname = last;
		Student.registeredStudents.add(this);
		this.generateUsername();
		this.generatePassword();
	}
	/**
	 * empty Student cosntructor
	 */
	public Student() {
	}
	
	/**
	 * toString method 
	 * @return String student's first and last name
	 */
	public String toString() {
		return this.getFirst() + " " + this.getLast();
	}
}
