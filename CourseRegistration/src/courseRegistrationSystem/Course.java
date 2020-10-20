package courseRegistrationSystem;
import java.util.ArrayList;

public class Course implements java.io.Serializable {
	private boolean full = false;
	private int maxStudents;
	private int currNumOfStudents;
	private String Course_Name;
	private String Course_Id;
	private int Max_Students;
	private ArrayList<Student> List_of_Students = new ArrayList<Student>();
	private String Course_Instructor;
	private String Course_Section;
	private String Course_Location;
	public static ArrayList<Course> CourseList = new ArrayList<Course>();
	public static ArrayList<Course> FullCourses = new ArrayList<Course>();
	public static ArrayList<Course> AvailableCourses = new ArrayList<Course>();

	
	/**
	 * sets course instructor
	 * @param String new instructor name
	 */
	public void setCourseInstructor(String newinstructor){
		this.Course_Instructor = newinstructor;
	}
	/**
	 * getter for Course name
	 * @return String Course name
	 */
	public String getCourseName() {
		return this.Course_Name;
	}
	/** 
	 * getter for Course section
	 * @return String Course section
	 */
	public String getCourseSection() {
		return this.Course_Section;
	}
	/**
	 * sets Course name
	 * @param String new course name
	 */
	public void setCourseName(String newname) {
		this.Course_Name = newname;
	}
	/**
	 * Sets new Course id
	 * @param String new course id
	 */
	public void setCourseID(String newid) {
		this.Course_Id = newid;
	}
	/**
	 * getter for list of students currently enrolled in course
	 * @return ArrayList<Student>
	 */
	public ArrayList<Student> getListOfStudents() {
		return this.List_of_Students;
	}
	/**
	 * getter for current number of students in course
	 * @return int current number of students
	 */
	public int getcurrNumOfStudents() {
		return this.currNumOfStudents;
	}
	/**
	 * checks if a course is full
	 * @return boolean true if course is full, else returns false
	 */
	public boolean isFull() {
		if(this.full != false) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * add student to course
	 * @param student object
	 */
	public void addStudent(Student student) {
		this.List_of_Students.add(student);
		this.currNumOfStudents++;
	}
	/**
	 * delete student from a course
	 * @param student object
	 */
	public void delStudent(Student student) {
		this.List_of_Students.remove(student);
		this.currNumOfStudents--;
	}
	/**
	 * Course instructor
	 * @param Course_Name
	 * @param Course_Id
	 * @param Max_Students
	 * @param currNumOfStudents
	 * @param List_of_Names
	 * @param Course_Instructor
	 * @param Course_Section
	 * @param Course_Location
	 */
	public Course(String Course_Name, String Course_Id, int Max_Students, int currNumOfStudents, 
			String List_of_Names, String Course_Instructor, String Course_Section, String Course_Location) { 
		Course.CourseList.add(this);
		this.Course_Name = Course_Name;
		this.Course_Id = Course_Id;
		this.Max_Students = Max_Students;
		this.currNumOfStudents = currNumOfStudents;
		this.Course_Instructor = Course_Instructor;
		this.Course_Section = Course_Section;
		this.Course_Location = Course_Location;
		String[] listofstudents = List_of_Names.split("/");
		if (List_of_Names.equals("NULL") == false) {
		for (String names : listofstudents) {
			String[] name = names.split(" ");
			Student student = new Student(name[0],name[1]);
			this.addStudent(student);
		}
		}
		
	}
	/**
	 * Prints headings for course information
	 */
	public static void printHeadings() {
		System.out.printf("%-50s%-20s%-20s%-20s%-30s%-50s%-20s%-20s", "Course Name", "Course ID", "Max Students", "Current Students", "List of Names", "Course Instructor", "Course Section", "Course Location");
		System.out.println();
	}
	/**
	 * toString() method for Course objects
	 * @return String information on Course
	 */
	public String toString() {
		return String.format("%-50s%-20s%-20s%-20s%-30s%-50s%-20s%-20s", this.Course_Name, this.Course_Id, this.Max_Students, this.currNumOfStudents,this.List_of_Students,this.Course_Instructor, this.Course_Section, this.Course_Location);
	}
	/**
	 * prints all courses 
	 */
	public static void printCourseChart() {
		for (Course el: CourseList) {
			System.out.println(el.toString());
		}
	}
	/**
	 * sort courses on increasing availability (most full --> most empty)
	 */
	public static void sortCourses1() {
		for (int i = 0; i < Course.CourseList.size(); i++) {
			int currMax = Course.CourseList.get(i).getcurrNumOfStudents();
			int currMaxIndex = i;
			for (int j = i+1; j <  Course.CourseList.size(); j++) {
				if(Course.CourseList.get(j).getcurrNumOfStudents() > currMax) {
					currMax = Course.CourseList.get(j).getcurrNumOfStudents();
					currMaxIndex = j;
				}
			}
			if (currMaxIndex != i) {
				 Course holder = Course.CourseList.get(currMaxIndex);
				 Course.CourseList.set(currMaxIndex,Course.CourseList.get(i));
				 Course.CourseList.set(i, holder);
				 
			}
		}
		
	}
	/**
	 * sort courses on decreasing availability(most empty --> most full)
	 */
	public static void sortCourses2() {
		for (int i = 0; i < Course.CourseList.size(); i++) {
			int currMin = Course.CourseList.get(i).getcurrNumOfStudents();
			int currMinIndex = i;
			for (int j = i+1; j <  Course.CourseList.size(); j++) {
				if(Course.CourseList.get(j).getcurrNumOfStudents() < currMin) {
					currMin = Course.CourseList.get(j).getcurrNumOfStudents();
					currMinIndex = j;
				}
			}
			if (currMinIndex != i) {
				 Course holder = Course.CourseList.get(currMinIndex);
				 Course.CourseList.set(currMinIndex,Course.CourseList.get(i));
				 Course.CourseList.set(i, holder);
				 
			}
		}
		
	}
}
