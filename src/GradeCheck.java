import java.util.Scanner;
import java.util.ArrayList;

public class GradeCheck {
    public static void main(String[] args) {
        /* 
         * The goal of this program is to calculate the users final letter grade based on the user entered inputs, the program accomplishes this in 3 main parts
         * 1: Gather up to ten grades from the user, make sure the input is valid
         * 2: Calculate the grade point average based on the users grades
         * 3: Assign a letter grade based on the grade point average
         */

        printInstructions();
        // Initiliaze scanner for user input and ArrayList to store entered grades
        Scanner userInput = new Scanner(System.in);
        ArrayList <Integer> gradesList = new ArrayList<Integer>();

        // Part 1: Accept up to 10 grades from the user, check for valid inputs
        primaryLoop:
        for (int gradesCount = 0; gradesCount < 10; gradesCount++) {
            // Initialize validInput to false, this will keep the while loop below checking input until input is validated and set to true
            boolean validInput = false;
            // Check for valid inputs, reprompt until valid input or quit statement "999" is entered providing instructions as needed
            while (!validInput) {
                try {
                    System.out.printf("Enter grade %d: ", (gradesCount + 1));
                    // Use temporary variable to ensure only valid input enters the list, protects average calculation
                    int temp = Integer.valueOf(userInput.nextLine());
                    // Break both loops if quit statement "999" is entered and continue
                    if (temp == 999) {
                        break primaryLoop;
                    }
                    // Make sure grade is between 0% and 100% otherwise throw error and reprompt
                    checkRange(temp);
                    gradesList.add(Integer.valueOf(temp));
                    // Set validInput to true after input is checked, breaking the while loop and continuing to next input
                    validInput = true;
                } catch (NumberFormatException notAnInt) {
                    // Handles invalid input data type
                    System.out.println("Error: Value was not an integer, please enter an integer as a whole number (no decimals!)");
                } catch (RangeException outOfRange){
                    // Handles inputs outside of possible grade range, 0% - 100%
                    System.out.println(outOfRange);
                }
            }
        }
        // Close scanner to prevent resource leak, initialize variables for average calculations
        userInput.close();
        int numberOfGrades = gradesList.size();
        double gradePointAverage = 0;
        System.out.println();
        
        // If no grades were entered, inform the user and exit the program before doing any calculations
        if (numberOfGrades == 0) {
            System.out.println("No grades entered, can not calculate GPA");
            return;
        }
        
        // Part 2: Calculate Grade point average of input grades
        // Using for loop instead of for each so that each grade is printed with the corresponding number using (i + 1)
        int sum = 0;
        for (int gradeNumber = 0; gradeNumber < numberOfGrades; gradeNumber++) {
            System.out.printf("Grade %d: %d | ", (gradeNumber + 1), (gradesList.get(gradeNumber)));
            sum += gradesList.get(gradeNumber);
        }
        // Calculate average as a float for increased accuracy with rounding
        gradePointAverage = (float)sum / (float)(numberOfGrades);
        System.out.printf("Average score is: %.2f", gradePointAverage);
        System.out.println();

        // Part 3: print the letter grade corresponding to the average
        convertToLetter(gradePointAverage);
    }


// Helper functions in alphabetical order

    static void checkRange(int userGivenInt) throws RangeException {
        // Checks if the user provided input was in proper range
        if (userGivenInt < 0 || userGivenInt > 100) {
            throw new RangeException("Grade given not in possible range \n" + "Try giving a grade between 0 and 100");
        }
    }

    static void convertToLetter(double gradePointAverage) {
        // Takes the average as an argument and prints the corresponding letter grade
        // if statement chosen instead of switch statement in order to use double data type, leads to more accurate letter grade assignment without rounding errors
        if (gradePointAverage >= 90) {
            System.out.println("Congrats! You earned an A!");
        }
        else if (gradePointAverage >= 80) {
            System.out.println("GPA: B");
        }
        else if (gradePointAverage >= 70) {
            System.out.println("GPA: C");
        }
        else if (gradePointAverage >= 60) {
            System.out.println("GPA: D");
        }
        else {
            System.out.println("GPA: F");
        }
    }

    static void printInstructions() {
        // Provides helpful functions to the user to successfully run the program
        System.out.println("\n" +  "Welcome to Kyle's GPA calculator! This program will take up to 10 grades as input from you, and tell you the letter grade (A,B,C,D,F) corresponding to your grade point average.");
        System.out.println("To use, please enter grades when prompted. The grades must be integers between 0 and 100.");
        System.out.println("If you want to enter less than 10 grades, or quit for any reason enter 999 as input." + "\n");
    }
}
