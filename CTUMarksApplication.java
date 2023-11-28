package com.mycompany.ctumarksapplication;

public class CTUMarksApplication {
    private String name_of_Course;
    private int[][] student_Marks;

    public CTUMarksApplication(String name_Of_Course, int num_Students, int num_Tests) {
        try {
            if (num_Students <= 0 || num_Tests <= 0) {
                throw new IllegalArgumentException("Number of students and tests must be positive integers.");
            }

            this.name_of_Course = name_Of_Course;
            this.student_Marks = new int[num_Students][num_Tests];

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Getter and setter methods for courseName and marks
    public String getCourseName() {
        return name_of_Course;
    }

    public void setCourseName(String courseName) {
        this.name_of_Course = courseName;
    }

    public int[][] getStudentMarks() {
        return student_Marks;
    }

    public void setMarks(int[][] marks) {
        this.student_Marks = marks;
    }

    public int getNumOfStudents() {
        return student_Marks.length;
    }

    public int getNumOfTests() {
        return student_Marks[0].length;
    }

    public int getMarks(int Index_Student, int Index_Test) {
        return student_Marks[Index_Student][Index_Test];
    }

    public void setMarks(int Index_Student, int Index_Test, int mark) {
        try {
            if (Index_Student < 0 || Index_Student >= student_Marks.length ||
                    Index_Test < 0 || Index_Test >= student_Marks[0].length) {
                throw new IndexOutOfBoundsException("Invalid student or test index.");
            }

            student_Marks[Index_Student][Index_Test] = mark;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Get the average for the student marks
    public double getAverage(int Index_Student) {
        try {
            if (Index_Student < 0 || Index_Student >= student_Marks.length) {
                throw new IndexOutOfBoundsException("Invalid student index.");
            }
            int sum = 0;
            for (int i = 0; i < getNumOfTests(); i++) {
                sum += student_Marks[Index_Student][i];
            }
            return (double) sum / getNumOfTests();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return 0.0; // Return a default value or handle the error case accordingly
        }
    }

    // Get the lowest mark of the students
    public int getLowest() {
        int lowestMark = student_Marks[0][0];
        for (int i = 0; i < getNumOfStudents(); i++) {
            for (int j = 0; j < getNumOfTests(); j++) {
                if (student_Marks[i][j] < lowestMark) {
                    lowestMark = student_Marks[i][j];
                }
            }
        }
        return lowestMark;
    }

    // Get the highest mark of the students
    public int getHighest() {
        int highestMark = student_Marks[0][0];
        for (int i = 0; i < getNumOfStudents(); i++) {
            for (int j = 0; j < getNumOfTests(); j++) {
                if (student_Marks[i][j] > highestMark) {
                    highestMark = student_Marks[i][j];
                }
            }
        }
        return highestMark;
    }
    
// Get the distribution of all the marks
public int[] getDistribution() {
    int[] distribution = new int[11];
    for (int i = 0; i < getNumOfStudents(); i++) {
        for (int j = 0; j < getNumOfTests(); j++) {
            int mark = student_Marks[i][j];
            if (mark < 10) {
                distribution[0]++;
            } else if (mark < 20) {
                distribution[1]++;
            } else if (mark < 30) {
                distribution[2]++;
            } else if (mark < 40) {
                distribution[3]++;
            } else if (mark < 50) {
                distribution[4]++;
            } else if (mark < 60) {
                distribution[5]++;
            } else if (mark < 70) {
                distribution[6]++;
            } else if (mark < 80) {
                distribution[7]++;
            } else if (mark < 90) {
                distribution[8]++;
            } else if (mark < 100) {
                distribution[9]++;
            } else {
                distribution[10]++;
            }
        }
    }
    return distribution;
}

public void displayMarks() {
    try {
        System.out.println();
        System.out.println("Welcome to CTU Marks for ");
        System.out.println(name_of_Course);
        System.out.println();
        System.out.println("The marks are:");
        System.out.println();

        String formative1Header = "Formative1";
        String formative2Header = "Formative2";
        String formative3Header = "Formative3";
        String averageHeader = "Average";

        int marksHeaderWidth = 16;

        // Displaying the headers for each column
        System.out.printf("%-15s", ""); // Empty space for the alignment
        System.out.printf("%-" + marksHeaderWidth + "s", formative1Header);
        System.out.printf("%-" + marksHeaderWidth + "s", formative2Header);
        System.out.printf("%-" + marksHeaderWidth + "s", formative3Header);
        System.out.printf("%-" + marksHeaderWidth + "s", averageHeader);
        System.out.println();

        // Displaying the marks for each student
        for (int i = 0; i < getNumOfStudents(); i++) {
            System.out.printf("%-15s", "Student " + (i + 1));
            for (int j = 0; j < getNumOfTests(); j++) {
                System.out.printf("%-" + marksHeaderWidth + "d", student_Marks[i][j]);
            }
            System.out.printf("%-" + marksHeaderWidth + ".2f", getAverage(i));
            System.out.println();
        }

        // Displaying the lowest and highest marks
        System.out.printf("\nLowest mark in the mark application is: %d\n", getLowest());
        System.out.printf("Highest mark in the mark application is: %d\n", getHighest());

        // Displaying the mark distribution
            int[] distribution = getDistribution();
            System.out.println("\nMark distribution: ");
            System.out.println();
            for (int i = 0; i < distribution.length - 1; i++) {
                System.out.printf("%-3d-%-3d:", i * 10, (i + 1) * 10 - 1);
                for (int j = 0; j < distribution[i]; j++) {
                    System.out.print(" [*]");
                }
                System.out.println();
            }
            System.out.printf("%-6d:", 100);
            for (int i = 0; i < distribution[10]; i++) {
                System.out.print(" [*]");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Creating an instance of CTUMarksApplication
    public static void main(String[] args) {
        try {
            CTUMarksApplication CTU_marks_Application = new CTUMarksApplication("Beginner Java [JD521]", 10, 3);

            // Setting marks for each student and test
            CTU_marks_Application.setMarks(0, 0, 87);
            CTU_marks_Application.setMarks(0, 1, 96);
            CTU_marks_Application.setMarks(0, 2, 70);
            CTU_marks_Application.setMarks(1, 0, 68);
            CTU_marks_Application.setMarks(1, 1, 87);
            CTU_marks_Application.setMarks(1, 2, 90);
            CTU_marks_Application.setMarks(2, 0, 94);
            CTU_marks_Application.setMarks(2, 1, 100);
            CTU_marks_Application.setMarks(2, 2, 90);
            CTU_marks_Application.setMarks(3, 0, 100);
            CTU_marks_Application.setMarks(3, 1, 81);
            CTU_marks_Application.setMarks(3, 2, 82);
            CTU_marks_Application.setMarks(4, 0, 83);
            CTU_marks_Application.setMarks(4, 1, 25);
            CTU_marks_Application.setMarks(4, 2, 85);
            CTU_marks_Application.setMarks(5, 0, 78);
            CTU_marks_Application.setMarks(5, 1, 87);
            CTU_marks_Application.setMarks(5, 2, 65);
            CTU_marks_Application.setMarks(6, 0, 85);
            CTU_marks_Application.setMarks(6, 1, 75);
            CTU_marks_Application.setMarks(6, 2, 83);
            CTU_marks_Application.setMarks(7, 0, 91);
            CTU_marks_Application.setMarks(7, 1, 94);
            CTU_marks_Application.setMarks(7, 2, 100);
            CTU_marks_Application.setMarks(8, 0, 76);
            CTU_marks_Application.setMarks(8, 1, 72);
            CTU_marks_Application.setMarks(8, 2, 40);
            CTU_marks_Application.setMarks(9, 0, 87);
            CTU_marks_Application.setMarks(9, 1, 93);
            CTU_marks_Application.setMarks(9, 2, 73);

            // Displaying the marks and other statistics
            CTU_marks_Application.displayMarks();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
