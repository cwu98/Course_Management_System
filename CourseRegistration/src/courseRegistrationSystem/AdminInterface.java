package courseRegistrationSystem;

public interface AdminInterface {
	public abstract void createCourse(String name, String id, int maxStudents, int currNumStudents, String studentsEnrolled, 
			String instructor, String section, String location);
	public abstract void delCourse(String id, String section);
	public abstract void editCourse(String id, String section);
	public abstract void viewCourseInformation(String courseID);
	public abstract void registerStudent(String first, String last);
	public abstract void viewAllCourses();
	public abstract void viewFullCourses();
	public abstract void addToCoursesFull(String id, String section);
	public abstract void studentsRegisteredInCourse(String id, String section);
	public abstract void coursesStudentIsRegisteredIn(String first, String last);
	public abstract void viewAdminMenu();
	
	
	
}
