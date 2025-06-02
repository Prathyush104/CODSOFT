import java.util.Scanner;

public class StudentGradeCalculator {
    
    // ANSI color codes for better console output
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(PURPLE + "🌟 STUDENT GRADE CALCULATOR 🌟" + RESET);
        System.out.println(CYAN + "=============================" + RESET);
        
        // Get number of subjects
        int numSubjects = 0;
        while (numSubjects <= 0) {
            System.out.print("Enter number of subjects: ");
            try {
                numSubjects = Integer.parseInt(scanner.nextLine());
                if (numSubjects <= 0) {
                    System.out.println(YELLOW + "Please enter a positive number!" + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(YELLOW + "Invalid input! Please enter a number." + RESET);
            }
        }
        
        // Array to store marks
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        
        // Input marks for each subject with validation
        for (int i = 0; i < numSubjects; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.printf("Enter marks for subject %d (out of 100): ", i + 1);
                try {
                    marks[i] = Integer.parseInt(scanner.nextLine());
                    if (marks[i] < 0 || marks[i] > 100) {
                        System.out.println(YELLOW + "Marks must be between 0 and 100!" + RESET);
                    } else {
                        validInput = true;
                        totalMarks += marks[i];
                    }
                } catch (NumberFormatException e) {
                    System.out.println(YELLOW + "Invalid input! Please enter a number." + RESET);
                }
            }
        }
        
        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;
        
        // Determine grade
        String grade = calculateGrade(averagePercentage);
        
        // Display results
        System.out.println(BLUE + "\n📊 RESULTS 📊" + RESET);
        System.out.println(CYAN + "============" + RESET);
        System.out.printf("Total Marks: %s%d/%d%s\n", GREEN, totalMarks, numSubjects * 100, RESET);
        System.out.printf("Average Percentage: %s%.2f%%%s\n", GREEN, averagePercentage, RESET);
        System.out.printf("Grade: %s%s%s\n", GREEN, grade, RESET);
        
        // Display subject-wise performance
        System.out.println(PURPLE + "\n📝 SUBJECT-WISE PERFORMANCE 📝" + RESET);
        System.out.println(CYAN + "============================" + RESET);
        for (int i = 0; i < numSubjects; i++) {
            String subjectGrade = calculateGrade(marks[i]);
            System.out.printf("Subject %d: %d/100 - %s%s%s\n", 
                i + 1, marks[i], getColorForGrade(subjectGrade), subjectGrade, RESET);
        }
        
        scanner.close();
    }
    
    private static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B+";
        if (percentage >= 60) return "B";
        if (percentage >= 50) return "C";
        if (percentage >= 40) return "D";
        return "F";
    }
    
    private static String getColorForGrade(String grade) {
        switch (grade) {
            case "A+": return "\u001B[32m"; // Green
            case "A":  return "\u001B[36m"; // Cyan
            case "B+": return "\u001B[34m"; // Blue
            case "B":  return "\u001B[35m"; // Purple
            case "C":  return "\u001B[33m"; // Yellow
            case "D":  return "\u001B[33m"; // Yellow
            default:   return "\u001B[31m"; // Red for F
        }
    }
}