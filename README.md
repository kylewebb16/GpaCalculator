# Important!
GradeCheck.java has a required dependency RangeException.java please make sure these are located in the same directory for proper function.

# Program function
The GradeCheck program takes user inputted grades, and returns a calculated GPA along with the corresponding letter grade.

# Instructions
1. Run the program GradeCheck
2. When prompted, enter valid grades as an integer (no decimals), the program will reprompt you and discard your input if it is not valid
3. To quit at any time, enter "999" as input and the program will calculate GPA and letter grades based on the grades you have input so far. If no grades entered program will terminate.

# Design choices
ArrayList vs Array: I originally used an array to store values for faster execution, but with a maximum of 10 grades, utility benefits of an arraylist outweighed the negligable speed benefits of an array. If this program was to be scaled up an array of user defined size may be a better approach.

Use of temp: to validate user input, I used an int temp variable. This choice was made to ensure only validated input entered the Arraylist, which was important for average calculation at little cost to readability, memory, or speed.

If statement instead of switch: while a switch statement would be faster, I opted to go with a series of if-else statments to convert the GPA to a letter grade so that I could enter the GPA as a double for increased accuracy and to reduce rounding errors. Double data types are not allowed in switch statments, and while switch statments are faster, the speed difference would be negligable with only 5 if statements.
