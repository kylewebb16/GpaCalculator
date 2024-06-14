import java.util.Scanner;
import java.util.ArrayList;

public class GradeCheck {
    public static void main(String[] args) {
        /** This program is dependent on the attached RangeException.java file
         *  This program
         */
        // Prints instructions
        printInstructions();

        // Open user input and list to store inputed grades
        Scanner userInput = new Scanner(System.in);
        // Using ArrayList when only 10 inputs because speed difference is negligable, if list size was a concern would use array[] for faster execution
        ArrayList <Integer> grades = new ArrayList<Integer>();

        // Accept up to 10 grades from the user, check for valid inputs
        primaryLoop:
        for (int i = 0; i < 10; i++) {
            boolean validInput = false;
            // Check for valid inputs, reprompt until valid input or quit statement "999" is entered
            while (!validInput) {
                try {
                    System.out.printf("Enter grade %d: ", (i + 1));
                    // Use temp to ensure only valid input enters the list
                    // This is important for average calculation
                    int temp = Integer.valueOf(userInput.nextLine());
                    if (temp == 999) {
                        break primaryLoop;
                    }
                    checkRange(temp);
                    grades.add(Integer.valueOf(temp));
                    validInput = true;

                } catch (NumberFormatException notAnInt) {
                    // Handles invalid input type
                    System.out.println("Error: Value was not an integer, please enter an integer");
                } catch (RangeException outOfRange){
                    // Handles inputs outside of possible grade range
                    System.out.println(outOfRange);
                }
            }
        }
        userInput.close();

        // Calculate the average of user defined grades, print message if no grades were entered don't calculate GPA
        int gradesEntered = grades.size();
        if (gradesEntered == 0) {
            System.out.println("No grades entered, can not calculate GPA");
            return;
        }
        double gradesAverage = 0;
        int sum = 0;
        // Using for loop instead of for each so that each grade is printed with the corresponding number using (i + 1)
        for (int i = 0; i < gradesEntered; i++) {
            System.out.printf("Grade %d: %d | ", (i + 1), (grades.get(i)));
            sum += grades.get(i);
        }
        // Calculate average as a float for increased accuracy with rounding
        gradesAverage = (float)sum / (float)(gradesEntered);
        System.out.printf("Average score is: %.2f", gradesAverage);
        System.out.println();
        convertToLetter(gradesAverage);
    }

    static void convertToLetter(double gradePointAverage) {
        // if chosen over switch in order to use double data type
        if (gradePointAverage >= 90) {
            System.out.println("Congrats! You earned an A!");
        }
        else if (gradePointAverage >= 80) {
            System.out.println("GPA: B");
        }
        else if (gradePointAverage >= 70) {
            System.out.println("GPA: B");
        }
        else if (gradePointAverage >= 60) {
            System.out.println("GPA: B");
        }
        else {
            System.out.println("GPA: F");
        }
    }

    static void checkRange(int userGivenInt) throws RangeException {
        if (userGivenInt < 0 || userGivenInt > 100) {
            throw new RangeException("Grade given not in possible range \n" + "Try giving a grade between 0 and 100");
        }
    }


    static void printInstructions() {
        System.out.println("Welcome to Kyle's GPA calculator! This program will take up to 10 grades as input from you, and tell you the letter grade (A,B,C,D,F) corresponding to your grade point average.");
        System.out.println("To use, please enter grades when prompted. The grades must be integers between 0 and 100.");
        System.out.println("If you want to enter less than 10 grades, or quit for any reason enter 999 as input");
    }
}
