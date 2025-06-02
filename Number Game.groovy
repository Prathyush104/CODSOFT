import java.util.Scanner;
import java.util.Random;

public class NumberGuessingChallenge {
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static final int POINTS_PER_ROUND = 100;
    
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    
    private static int totalScore = 0;
    private static int roundsPlayed = 0;
    private static int roundsWon = 0;
    
    public static void main(String[] args) {
        displayWelcomeMessage();
        
        boolean playAgain = true;
        while (playAgain) {
            playGameRound();
            playAgain = askToPlayAgain();
        }
        
        displayFinalStats();
        scanner.close();
    }
    
    private static void displayWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║       WELCOME TO NUMBER GUESSING CHALLENGE   ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Try to guess a number between 1 and " + MAX_RANGE + "     ║");
        System.out.println("║ You have " + MAX_ATTEMPTS + " attempts to guess correctly.  ║");
        System.out.println("║ The faster you guess, the more points you get!║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }
    
    private static void playGameRound() {
        int secretNumber = random.nextInt(MAX_RANGE) + 1;
        int attemptsLeft = MAX_ATTEMPTS;
        boolean hasWon = false;
        
        roundsPlayed++;
        System.out.println("\n⌛ Round " + roundsPlayed + " started! Good luck!");
        System.out.println("----------------------------------");
        
        while (attemptsLeft > 0 && !hasWon) {
            System.out.print("💡 Attempts left: " + attemptsLeft + " | Enter your guess: ");
            int guess = getValidGuess();
            
            if (guess == secretNumber) {
                hasWon = true;
                roundsWon++;
                int roundScore = calculateScore(attemptsLeft);
                totalScore += roundScore;
                
                System.out.println("\n🎉 Congratulations! You guessed it!");
                System.out.println("🔑 The number was: " + secretNumber);
                System.out.println("🏆 You earned " + roundScore + " points this round!");
                System.out.println("💰 Total score: " + totalScore);
            } else if (guess < secretNumber) {
                System.out.println("⬆️ Too low! Try a higher number.");
                attemptsLeft--;
            } else {
                System.out.println("⬇️ Too high! Try a lower number.");
                attemptsLeft--;
            }
        }
        
        if (!hasWon) {
            System.out.println("\n😢 Game over! You've used all your attempts.");
            System.out.println("🔑 The secret number was: " + secretNumber);
        }
    }
    
    private static int getValidGuess() {
        while (true) {
            try {
                int guess = Integer.parseInt(scanner.nextLine());
                if (guess < 1 || guess > MAX_RANGE) {
                    System.out.print("Please enter a number between 1 and " + MAX_RANGE + ": ");
                } else {
                    return guess;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private static int calculateScore(int attemptsLeft) {
        return (int)(POINTS_PER_ROUND * (attemptsLeft / (double)MAX_ATTEMPTS));
    }
    
    private static boolean askToPlayAgain() {
        System.out.print("\nWould you like to play again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.startsWith("y");
    }
    
    private static void displayFinalStats() {
        System.out.println("\n════════════ GAME STATISTICS ════════════");
        System.out.println("🔢 Rounds Played: " + roundsPlayed);
        System.out.println("🏅 Rounds Won: " + roundsWon);
        System.out.printf("📊 Win Rate: %.1f%%\n", (roundsWon * 100.0 / roundsPlayed));
        System.out.println("💰 Total Score: " + totalScore);
        System.out.println("══════════════════════════════════════");
        System.out.println("\nThank you for playing! Goodbye! 👋");
    }
}