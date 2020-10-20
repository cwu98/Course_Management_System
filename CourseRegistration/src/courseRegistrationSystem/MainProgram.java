package courseRegistrationSystem;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class MainProgram {
	/**
	 * read file
	 */
	public static ArrayList<String> readFile(String filename) {
		String line = null;
		ArrayList<String> CourseFile = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				CourseFile.add(line);
			}
			br.close();
		}
		
	catch (IOException ioe) {
		ioe.printStackTrace();
	}
		return CourseFile;
	}
	/**
	 * serialize course objects
	 * @param ArrayList<Course> course objects
	 */
	public static void serializeCourse(ArrayList<Course> courses) {
		try {
			FileOutputStream fos = new FileOutputStream("src/SerializedMyUniversityCourses.ser");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(courses);
			
			oos.close();
			fos.close();
			System.out.println("Serialization for courses complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	/**
	 * deserialize course objects
	 */
	public static ArrayList<Course> deSerializeCourse(){
		 try{
		      FileInputStream fis = new FileInputStream("src/SerializedMyUniversityCourses.ser");
		      
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      ArrayList<Course> courseList = (ArrayList<Course>)ois.readObject();
		      ois.close();
		      fis.close();
		      
		      return courseList;
		    }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       return null;
		    }
		 catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       return null;
		     }
	}
	/**
	 * serialize Student objects
	 * @param students
	 */
	public static void serializeStudent(ArrayList<Student> students) {
		try {
				FileOutputStream fos = new FileOutputStream("src/SerializedStudents.ser");
				
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				oos.writeObject(students);
				
				oos.close();
				fos.close();
				System.out.println("Serialization for students complete");
		} 
		catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	/**
	 * deserialize student objects
	 */
	public static ArrayList<Student> deSerializeStudent() {
			 try{
			      FileInputStream fis = new FileInputStream("src/SerializedStudents.ser");
			      
			      ObjectInputStream ois = new ObjectInputStream(fis);
			      
			      ArrayList<Student> studentList = (ArrayList<Student>)ois.readObject();
			      ois.close();
			      fis.close();
			      
			      return studentList;
			    }
			    catch(IOException ioe) {
			       ioe.printStackTrace();
			       return null;
			    }
			 catch(ClassNotFoundException cnfe) {
			       cnfe.printStackTrace();
			       return null;
			     }
		}
	/**
	 * serialize arraylist of string student usernames
	 * @param ArrayList of strings
	 */
	public static void serializeString(ArrayList<String> string){
		try {
			FileOutputStream fos = new FileOutputStream("src/String.ser");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(string);
			
			oos.close();
			fos.close();
			System.out.println("Serialization for String of usernames complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	/**
	 * because static variable count in student class is static, 
	 */
	/**
	 * deserialize arraylist of string student usernames
	 * @return
	 */
	public static ArrayList<String> deSerializeString(){
		 try{
		      FileInputStream fis = new FileInputStream("src/String.ser");
		      
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      ArrayList<String> usernameList = (ArrayList<String>)ois.readObject();
		      ois.close();
		      fis.close();
		      
		      return usernameList;
		    }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       return null;
		    }
		 catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       return null;
	}
	}
	
	/**
	 * main method where the program starts to run
	 * @param args
	 */
	public static void main(String[] args) {
	
	
		File fc = new File("src/SerializedMyUniversityCourses.ser");
		File fs = new File("src/SerializedStudents.ser");
		if(fc.exists() && fs.exists()) { 
			//deserialize if serialization file exists
			Course.CourseList = deSerializeCourse();
			Student.registeredStudents = deSerializeStudent();
			Student.listofusernames = deSerializeString();
		}
		if ((fc.exists() && fs.exists()) == false){
			/**
			 * read all courses from comma delimited file if serialization files do not exist
			 */
			System.out.println("Serialization file does not exist");
			String filename = "src/MyUniversityCourses.csv";
			ArrayList<String> CourseFile = readFile(filename);
			ArrayList<String> CourseSplit;
			for (int i = 1; i<CourseFile.size(); i++) {
				CourseSplit = new ArrayList(Arrays.asList(CourseFile.get(i).split(",")));
				Course course = new Course(CourseSplit.get(0), CourseSplit.get(1), Integer.parseInt(CourseSplit.get(2)), 
						Integer.parseInt(CourseSplit.get(3)), CourseSplit.get(4), CourseSplit.get(5), 
						CourseSplit.get(6), CourseSplit.get(7));
			}
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter (1) to login as admin or (2) to login as student");
		int user = sc.nextInt();
		boolean keepgoing = true; 
		String username;
		String password;
		String pw;
		String coursename;
		String id;
		String section;
		String first;
		String last;
		int input; //number corresponding to which option on menu that user chooses
		boolean loginsuccess;
		if(user == 1) {
			loginsuccess = false;
				while (loginsuccess == false) {
					System.out.print("Enter admin username: ");
					username = sc.next();
					System.out.print("Enter admin password: ");
					password = sc.next();
					if ((username.equals(Admin.username)) && (password.equals(Admin.password)) == true){
						loginsuccess = true;
					}
					else {
						System.out.println("Incorrect username or password. Try again.");
					}
				}
			System.out.println("You are now logged in as admin");
			Admin admin = new Admin();
			while(keepgoing) {
				admin.viewAdminMenu();
				input = sc.nextInt();
			
				switch (input) {
				case 1: //Create a new course
					System.out.println("To create a new course, please enter course name, course ID, maximum  number of students, current number of students,"
							+ "list of students enrolled, course instructor, course section, and course location as a comma separated list");
					String newcourse = sc.next();
					String[] courseElts = newcourse.split(",");
					for (String x : courseElts) {
						x = x.trim();
					}
					admin.createCourse(courseElts[0], courseElts[1], Integer.parseInt(courseElts[2]), Integer.parseInt(courseElts[3]), courseElts[4], courseElts[5], courseElts[6], courseElts[7]);
					break;
				case 2: //Delete a course
					System.out.print("To delete a course, first enter course ID: ");
					id = sc.next();
					System.out.print("Enter course section: ");
					section = sc.next();
					admin.delCourse(id, section);
					break;
				case 3: //Edit a course
					boolean flag = true;
					System.out.print("Enter the course id of the course you want to edit: ");
					id = sc.next();
					System.out.print("Enter the course section of the course you want to edit: ");
					section = sc.next();
					
					while (flag) {
						admin.editCourse(id, section);
						System.out.print("Do you want to continue editing this course? (1)Yes (2)No ");
						if (sc.next().equals("2")) {
							flag = false;
						}
						if (sc.next().equals("1")) {
							continue;
						}
						else {
							System.out.println("Error input");
							flag = false;
							}
						}
					break;
				case 4: //Display information on a given course by Course ID
					System.out.print("Enter in course ID to display information of the course: ");
					id = sc.next();
					admin.viewCourseInformation(id);
					break;
				case 5: //Register a student
					System.out.print("Enter first name of the student you want to register: ");
					first = sc.next();
					System.out.print("Enter last name: ");
					last = sc.next();
					admin.registerStudent(first, last);
					break;
				case 6: //View all courses 
					admin.viewAllCourses();
					break;
				case 7: //view all courses that are full
					admin.viewFullCourses();
					break;
				case 8: //write to a file the list of courses that are full
					System.out.print("Enter course ID of the full course: ");
					id = sc.next();
					System.out.print("Enter section number of the full course: ");
					section = sc.next();
					System.out.println();
					admin.addToCoursesFull(id, section);
					System.out.println("This full course has been added to the file");
					break;
				case 9: //view the names of the students being registered in a specific course 
					System.out.print("Enter course ID: ");
					id = sc.next();
					System.out.print("Enter section number: ");
					section = sc.next();
					admin.studentsRegisteredInCourse(id, section);
					break;
				case 10: //view list of courses a student is registered in
					System.out.print("Enter student's first name: ");
					first = sc.next();
					System.out.print("Enter student's last name: ");
					last = sc.next();
					admin.coursesStudentIsRegisteredIn(first, last);
					break;
				case 11: //sort courses based on the current number of students registered
					System.out.print("Would you like to sort from (1) most students registered to least students registered or (2) least students registered to most students registered? ");
					int sort = sc.nextInt();
					if(sort == 1) {
						Course.sortCourses1();
					}
					if(sort == 2) {
						Course.sortCourses2();
					}
					System.out.println();
					System.out.println("sorted");
					break;
				case 12: //exit
					System.out.println("Exiting the system...");
					keepgoing = false;
					break;
					
				}
				if(keepgoing != false) {
				System.out.println("Would you like to perfom another action? (1)Yes (2)No ");
				int again = sc.nextInt();
				if(again == 2) {
					System.out.println("Exiting the system..."); 
					keepgoing = false;
				}
				}
			}
			
			
		}
		if (user == 2) {
			System.out.println(Student.registeredStudents);
			System.out.println(Student.listofusernames);
			System.out.println(Student.registeredStudents.size());
			Student student = new Student();
			loginsuccess = false;
			while (loginsuccess == false) {
				System.out.print("Enter student username: ");
				username = sc.next();
				System.out.print("Enter student password: ");
				password = sc.next();
				System.out.println("List of Usernames: " + Student.listofusernames);
				student = Student.getStudentFromUsername(username);
				System.out.println(student.getPassword());
				if(password.equals(student.getPassword())) {
					System.out.println("Login success!");
				 loginsuccess = true;
				}
				else {
					System.out.println("Incorrect username or password. Try again.");
				}
			}
			while (keepgoing) {
				student.viewStudentMenu();
				input = sc.nextInt();
				switch(input) {
				case 1: //view all courses 
					student.viewAllCourses();
					break;
				case 2: //view all courses that are not full
					student.viewAvailableCourses();
					break;
				case 3: //register on a course
					System.out.print("Enter Course name: ");
					coursename = sc.next();
					System.out.print("Enter Course section: ");
					section = sc.next();
					System.out.print("Enter first name: ");
					first = sc.next();
					System.out.print("Enter last name: ");
					last = sc.next();
					student.registerForACourse(coursename, section, first, last);
					break;
				case 4: //withdraw from a course
					System.out.print("Enter Course name: ");
					coursename = sc.next();
					System.out.print("Enter Course section: ");
					section = sc.next();
					System.out.print("Enter first name: ");
					first = sc.next();
					System.out.print("Enter last name: ");
					last = sc.next();
					student.withdrawFromACourse(coursename, section, first, last);
					break;
				case 5: //view all courses that the student is registered in
					student.viewEnrolledCourses();
					break;
				case 6: //Exit
					System.out.println("Exiting the system...");
				}
				System.out.println("Would you like to perfom another action? (1)Yes (2)No ");
				int again1 = sc.nextInt();
				if(again1 == 2) {
					System.out.println("Exiting the system..."); 
					keepgoing = false;
				
			}
			
		}
		}
		serializeCourse(Course.CourseList);
		serializeStudent(Student.registeredStudents);
		serializeString(Student.listofusernames);
	}
}


