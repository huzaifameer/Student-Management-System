import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<String[]> studentDetails = new ArrayList<>();

    public static void main(String[] args){
        do {
            printTemplate();
        } while (true);
    }
    public static void printTemplate() {
        do {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|         WELCOME TO STUDENT MARKS MANAGEMENT SYSTEM               |");
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("| %-28s | %-33s |\n", "[1] Add New Student", "[2] Add New Student With Marks");
            System.out.printf("| %-28s | %-33s |\n", "[3] Add Marks", "[4] Update Student Details");
            System.out.printf("| %-28s | %-33s |\n", "[5] Update Marks", "[6] Delete Student ");
            System.out.printf("| %-28s | %-33s |\n", "[7] Print Student Details", "[8]  Print Ranks ");
            System.out.printf("| %-28s | %-33s |\n", "[9] Best in Programming ", "[10] Best in DBMS");
            System.out.println("--------------------------------------------------------------------");
            setOption();
        } while (true);

    }

    public static void setOption() {
        int option = getOption();//This statement will assign the returning value from the getOption()
        //Calling the switch-case
        switch (option) {
            case 0:
                System.exit(0);//Exit from the program
            case 1:
                addStudent();
                break;
            case 2:
                StudentWithMarks();
                break;
            case 3:
                getStudentMarks();
                break;
            case 4:
                updateStudentDetails();
                break;
            case 5:
                UpdateMarks();
                break;
            case 6:
                deleteStudent();
                break;
            case 7:
                printStudentDetails();
                break;
            case 8:
                getStudentRanks();
                break;
            case 9:
                bestInSubject("Programming",3);
                break;
            case 10:
                bestInSubject("DBMS",2);
                break;
        }
    }
    public static int getOption() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter an Option : ");
            int option;
            while (true) {
                try {
                    option = Integer.parseInt(input.nextLine());//this line will get an integer as an option
                    break;//if the input is an integer it'll break the loop from here
                } catch (NumberFormatException e) {
                    System.out.println("**** It's not an option! Please try again..");
                    System.out.println();
                    System.out.print("Enter an Option : ");
                }
            }
            if (option > 10 | option < 0) {
                System.out.println("Wrong option selected! Please try again..");
                System.out.println();
                continue;
            }
            return option;
        } while (true);
    }

    public static void addStudent() {
        String[] studentDetailArray = new String[6];
        Scanner input = new Scanner(System.in);

        studentDetailArray[4] = "0"; // Initialize total marks to 0
        studentDetailArray[5] = "0.00"; // Initialize average to 0.00

        System.out.println("\n\t\txxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("\t\tx Adding a new student x");
        System.out.println("\t\txxxxxxxxxxxxxxxxxxxxxxxx");

        /*---code to get the student name---*/
        String name=getStudentNameDetails(studentDetailArray, input);
        /*----------------------------------*/
        /*---code to get the student id according to a valid format---*/
        while (true) {
            System.out.print("Enter student ID [Ex:SD00001]: ");
            String studentId = input.next();

            if (isValidIDFormat(studentId)) {
                if (isDuplicateID(studentId)) {
                    System.out.println("*** Student ID already exists. ***");
                } else {
                    System.out.println("*. New student " + name + " has been validated successfully!");
                    studentDetailArray[0] = studentId;
                    studentDetails.add(studentDetailArray);
                    break;
                }
            } else {
                System.out.println("*** ID format is not valid. ***");
            }

        }

    }

    //Method to check the format of the ID
    public static boolean isValidIDFormat(String id) {
        // Define the pattern for the ID format "SD00001"
        String pattern = "^[A-Z]{2}\\d{5}$";  // Two uppercase letters followed by five digits

        // Create a Pattern object and a Matcher object
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(id);

        // Return true if the ID matches the pattern, otherwise false
        return matcher.matches();
    }

    //Method to indicate the duplicated ID's
    public static boolean isDuplicateID(String id) {
        for (String[] studentDetail : studentDetails) {
            if (studentDetail[0] != null && studentDetail[0].equals(id)) {
                return true;
            }
        }
        return false;
    }

    //Method to indicate the duplicated names
    public static boolean isDuplicateName(String name) {
        for (String[] studentDetail : studentDetails) {
            if (studentDetail[1] != null && studentDetail[1].equals(name)) {
                return true;
            }
        }
        return false;
    }
    public static void updateStudentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n|---Please enter valid student details to continue the process---|\n");
        while (true) {
            System.out.print("Input the student ID : ");
            String updateID = input.next();
            System.out.print("Are you sure on updating the ID ? (y/n) ");
            String sureOption = input.next();
            if (sureOption.equals("y")) {
                for (String[] dataVal : studentDetails) {
                    if (dataVal[0].equals(updateID)) {
                        System.out.println("+---------Present Details-----------+");
                        System.out.println("Student Name : " + dataVal[1]);
                        System.out.println("Student ID   : " + dataVal[0]);
                        System.out.println("+-----------------------------------+");
                        System.out.print("Enter the new first name  : ");
                        String newFirstName = input.next();
                        System.out.print("Enter the new sur name    : ");
                        String newLastName = input.next();
                        dataVal[1] = newFirstName + " " + newLastName;
                        System.out.println("The name has been updated as " + dataVal[1] + " .");
                    }
                }
            } else if (sureOption.equals("n")) {
                break;
            } else {
                System.out.println("Input the correct option");
                return;
            }
            System.out.println("Input a valid ID");
            break;
        }

    }
    public static void getStudentMarks() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n|---Please enter valid student details to continue the process---|\n");
        while (true) {
            System.out.print("Student ID        : ");
            String id = input.next();
            for (String[] data : studentDetails) {
                if (data[0].equals(id) && (data[2]==null|data[3]==null)) {
                    System.out.println("Student Name : " + data[1]);
                    System.out.print("Is this the Student ? (y/n) ");
                    String selection = input.next();
                    if (selection.equals("y")) {
                        int dbmsMark, pfMarks;
                        do {
                            System.out.print("\nInput DBMS marks          :");
                            while (true) {
                                try {
                                    dbmsMark = Integer.parseInt(input.next());
                                    break;
                                } catch (NumberFormatException | InputMismatchException e) {
                                    System.out.println("*** Input an Integer. ***");
                                    System.out.print("\nInput DBMS marks          :");
                                }
                            }
                            if (dbmsMark < 0 | dbmsMark > 100) {
                                System.out.println("*** Invalid marks range !");
                                continue;
                            }
                            data[2] = String.valueOf(dbmsMark);
                            break;
                        } while (true);
                        do {
                            System.out.print("Input Fundamentals marks  :");
                            while (true) {
                                try {
                                    pfMarks = Integer.parseInt(input.next());
                                    break;
                                } catch (NumberFormatException | InputMismatchException e) {
                                    System.out.println("*** Input an Integer. ***");
                                    System.out.print("\nInput Fundamentals marks  :");
                                }
                            }
                            if (pfMarks < 0 | pfMarks > 100) {
                                System.out.println("*** Invalid marks range !");
                                continue;
                            }
                            data[3] = String.valueOf(pfMarks);
                            break;
                        } while (true);
                        return;
                    } else if (selection.equals("n")) {
                        break;
                    } else {
                        System.out.println("Input the correct option !");
                        return;
                    }
                }
            }
            System.out.println("Input a valid ID or marks already has been added !");
            break;
        }
    }
    /*method to print the student details*/
    public static void printStudentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("\t\txxxxxxxxxxxxxxxxxxx");
        System.out.println("\t\tx Student Details x");
        System.out.println("\t\txxxxxxxxxxxxxxxxxxx");
        System.out.println();

        System.out.print("Enter Student Id: ");
        String stId = input.nextLine();

        System.out.println("+---------------------------------+");
        for (String[] studentDetail : studentDetails) {
            if (studentDetail[0].equals(stId)) {
                System.out.println("student Id: " + studentDetail[0]);
                System.out.println("student name: " + studentDetail[1]);
                System.out.println("+---------------------------------+");

                if (studentDetail[2] != null && studentDetail[3] != null) {
                    int dbmsMarks = Integer.parseInt(studentDetail[2]);
                    int programmingMarks = Integer.parseInt(studentDetail[3]);
                    int totalMarks = dbmsMarks + programmingMarks;
                    double average = (double) totalMarks / 2.0;

                    studentDetail[4] = String.valueOf(totalMarks);
                    studentDetail[5] = String.valueOf(average);

                    for (int i = 0; i < studentDetails.size(); i++) {
                        if (studentDetails.get(i)[0].equals(stId)) {
                            studentDetails.set(i, studentDetail);
                            break;
                        }
                    }


                    System.out.println("DBMS Marks       : " + studentDetail[2]);
                    System.out.println("Programming Marks: " + studentDetail[3]);
                    System.out.println("Total Marks      : " + studentDetail[4]);
                    System.out.println("Average Marks    : " + studentDetail[5]);

                } else {
                    System.out.println("marks yet to be added ");
                }

                System.out.println("+---------------------------------+");
            }
        }

        System.out.print("need to find another student's details? (y/n): ");
        String option = input.next();

        if (option.equals("y")) {
            printStudentDetails();
        }
    }
    public static void deleteStudent() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Student Id to delete");
            String stId = input.next();
            boolean deleted = false;
            for (String[] studentDetail : studentDetails) {
                if (studentDetail[0].equals(stId)) {
                    studentDetails.remove(studentDetail);
                    deleted = true;
                    break;
                }
            }
            if (deleted) {
                System.out.println("student id " + stId + " deleted successfully");
            } else {
                System.out.println("no matching student ID you will be escape this stage !");
            }
            break;
        }

    }
    public static void StudentWithMarks() {
        String[] studentDetailArray = new String[6];
        Scanner input = new Scanner(System.in);

        System.out.println("\n\t\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("\t\tx Adding a new student with marks x");
        System.out.println("\t\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        String name=getStudentNameDetails(studentDetailArray, input);
        /*----------------------------------*/
        /*---code to get the student id according to a valid format---*/
        while (true) {
            System.out.print("Enter student ID [Ex:SD00001]: ");
            String studentId = input.next();

            if (isValidIDFormat(studentId)) {
                if (isDuplicateID(studentId)) {
                    System.out.println("*** Student ID already exists. ***");
                } else {
                    System.out.println("*. New student " + name + " has been validated successfully!");
                    studentDetailArray[0] = studentId;
                    studentDetails.add(studentDetailArray);
                    break;
                }
            } else {
                System.out.println("ID format is not valid. ");
            }

        }
        int DBMSMark, profMarks;

        do {
            System.out.print("\nInput DBMS marks: ");
            while (true) {
                try {
                    DBMSMark = Integer.parseInt(input.next());
                    break;
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println(" Input an Integer. ");
                    System.out.print("Input DBMS marks: ");
                }
            }
            if (DBMSMark < 0 || DBMSMark > 100) {
                System.out.println("*** Invalid marks range !");
                continue;
            }
            studentDetailArray[2] = String.valueOf(DBMSMark);
            break;
        } while (true);

        do {
            System.out.print("Input Fundamentals marks: ");
            while (true) {
                try {
                    profMarks = Integer.parseInt(input.next());
                    break;
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("*** Input an Integer.");
                    System.out.print("Input Fundamentals marks: ");
                }
            }
            if (profMarks < 0 || profMarks > 100) {
                System.out.println("*** Invalid marks range !");
                continue;
            }
            studentDetailArray[3] = String.valueOf(profMarks);
            break;
        } while (true);
    }
    private static String getStudentNameDetails(String[] studentDetailArray, Scanner input) {
        String firstName, lastName, name;
        while (true) {
            System.out.print("\nEnter student First Name     : ");
            firstName = input.next();
            System.out.print("Enter student surname        : ");
            lastName = input.next();
            name = firstName + " " + lastName;

            if (isDuplicateName(name)) {
                System.out.println("*** Student already exists. ***");
            } else {
                studentDetailArray[1] = name;
                break;
            }
        }
        return firstName+" "+lastName;
    }
    public static void UpdateMarks() throws NumberFormatException {
        Scanner input = new Scanner(System.in);
//        System.out.println("\n|---Please enter valid student details to continue the process---|\n");
        while (true) {
            System.out.print("Input the student ID : ");
            String updateID = input.next();
            System.out.print("Are you sure about the update  ? (y/n) ");
            String sureOption = input.next();
            if (sureOption.equals("y")) {
                for (String[] dataVal : studentDetails) {
                    if (dataVal[0].equals(updateID)) {

                        if (dataVal[3] == null && dataVal[2] == null) {
                            System.out.println("This student marks yet to be added..");
                        } else {
                            System.out.println("+--------- Present Details -----------+");

                            System.out.println("programming Fundamentals marks : " + dataVal[3]);
                            System.out.println("Database Management marks      : " + dataVal[2]);

                            System.out.println("+-------------------------------------+");
                        }
                        int profMarks;
                        int DBMSMark;
                        do {
                            System.out.print("\nInput new DBMS marks: ");
                            while (true) {
                                try {
                                    DBMSMark = Integer.parseInt(input.next());
                                    break;
                                } catch (NumberFormatException | InputMismatchException e) {
                                    System.out.println(" Input an Integer. ");
                                    System.out.print("Input new DBMS marks: ");
                                }
                            }
                            if (DBMSMark < 0 || DBMSMark > 100) {
                                System.out.println("*** Invalid marks range !");
                                continue;
                            }
                            dataVal[2] = String.valueOf(DBMSMark);
                            break;
                        } while (true);


                        do {
                            System.out.print("Input new Fundamentals marks: ");
                            while (true) {
                                try {
                                    profMarks = Integer.parseInt(input.next());
                                    break;
                                } catch (NumberFormatException | InputMismatchException e) {
                                    System.out.println("*** Input an Integer.");
                                    System.out.print("Input new Fundamentals marks: ");
                                }
                            }
                            if (profMarks < 0 || profMarks > 100) {
                                System.out.println("*** Invalid marks range !");
                                continue;
                            }
                            dataVal[3] = String.valueOf(profMarks);
                            break;
                        } while (true);


                        System.out.println("|============updated marks==========|");


                        System.out.println("+. programming Fundamentals marks : " + profMarks);
                        System.out.println("+. Database Management marks      : " + DBMSMark);

                        System.out.println("|===================================|");
                        dataVal[3] = String.valueOf(profMarks);
                        dataVal[2] = String.valueOf(DBMSMark);
//                        System.out.println("The name has been updated as " + dataVal[1] + " .");
                    }
                }
            } else if (sureOption.equals("n")) {
                break;
            } else {
                System.out.println("Input the correct option");
                return;
            }
            /*System.out.println("Input a valid ID");*/
            break;
        }
    }
    public static void getStudentRanks() {
        System.out.println("\t\txxxxxxxxxxxxxxxxx");
        System.out.println("\t\tx Student Ranks x");
        System.out.println("\t\txxxxxxxxxxxxxxxxx");
        System.out.println();

        // Calculate total marks and average marks for each student
        for (String[] studentDetail : studentDetails) {
            if (studentDetail[2] != null && studentDetail[3] != null) {
                int dbmsMarks = Integer.parseInt(studentDetail[2]);
                int programmingMarks = Integer.parseInt(studentDetail[3]);
                int totalMarks = dbmsMarks + programmingMarks;
                double averageMarks = (dbmsMarks + programmingMarks) / 2.0;
                studentDetail[4] = String.valueOf(totalMarks);
                studentDetail[5] = String.format("%.2f", averageMarks);
            }
        }

        // Sort studentDetails based on total marks in descending order
        Collections.sort(studentDetails, (a, b) -> Integer.compare(Integer.parseInt(b[4]), Integer.parseInt(a[4])));

        // Display the ranked list of students with total marks and average marks
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|     Rank   |  Student Name     | Total Marks | Average Marks |");
        System.out.println("+--------------------------------------------------------------+");
        for (int rank = 0; rank < studentDetails.size(); rank++) {
            String[] studentDetail = studentDetails.get(rank);
            System.out.printf("|   %-7d  |  %-16s | %-11s | %-13s |\n",
                    rank + 1, studentDetail[1], studentDetail[4], studentDetail[5]);
        }
        System.out.println("+--------------------------------------------------------------+");
        System.out.println();
    }
    public static void bestInSubject(String subjectName, int valueIndex) {
        int maxMarks = -1;
        String topStudent = "";
        String stId = "";

        for (String[] data : studentDetails) {
            if (data[valueIndex] != null) {
                int marks = Integer.parseInt(data[valueIndex]);
                if (marks > maxMarks) {
                    maxMarks = marks;
                    topStudent = data[1];
                    stId = data[0];
                }
            }
        }

        if (maxMarks != -1) {
            System.out.println("+--- Student with the highest " + subjectName + " marks ---+");
            System.out.println("Student Name : " + topStudent);
            System.out.println("Student ID   : " + stId);
            System.out.println(subjectName + " Marks   : " + maxMarks);
        } else {
            System.out.println("No student has " + subjectName + " marks.");
        }
    }
}