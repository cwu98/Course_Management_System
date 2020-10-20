package courseRegistrationSystem;
import java.util.ArrayList; 

public interface StudentInterface {
	public abstract String getFirst();
	public abstract String getLast();
	public abstract ArrayList<Course> getEnrolledCourses();
	public abstract void viewEnrolledCourses();
	public abstract void viewAllCourses();
	public abstract void registerForACourse(String CourseName, String section, String first, String last);
	public abstract void withdrawFromACourse(String CourseName, String section, String first, String last);
	public abstract void viewAvailableCourses();
	public abstract void viewStudentMenu();
	
}
