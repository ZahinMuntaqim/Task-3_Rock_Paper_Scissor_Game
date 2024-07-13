import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RockPaperScissors {
    public static void main(String[] args) {
        if (!validateArgs(args)) {
            return;
        }

        String[] moves = args;
        int numMoves = moves.length;

        // Generate a cryptographically secure random key
        byte[] key = KeyGenerator.generateKey();

        // Generate computer's move
        SecureRandom random = new SecureRandom();
        int computerMoveIndex = random.nextInt(numMoves);
        String computerMove = moves[computerMoveIndex];

        // Compute HMAC
        String hmac = HMACUtil.computeHMAC(computerMove, key);
        System.out.println("HMAC: " + hmac);

        // Game loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu(moves);
            String userInput = scanner.nextLine();

            if (userInput.equals("0")) {
                System.out.println("Exiting...");
                break;
            } else if (userInput.equals("?")) {
                HelpTable.printHelpTable(moves);
            } else {
                try {
                    int userMoveIndex = Integer.parseInt(userInput) - 1;
                    if (userMoveIndex < 0 || userMoveIndex >= numMoves) {
                        System.out.println("Invalid move. Please try again.");
                        continue;
                    }
                    String userMove = moves[userMoveIndex];
                    System.out.println("Your move: " + userMove);
                    System.out.println("Computer move: " + computerMove);

                    String result = GameRules.determineWinner(userMoveIndex, computerMoveIndex, numMoves);
                    System.out.println(result);
                    System.out.println("HMAC key: " + bytesToHex(key));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        scanner.close();
    }

    private static boolean validateArgs(String[] args) {
        int numMoves = args.length;
        if (numMoves < 3 || numMoves % 2 == 0) {
            System.out.println("Error: You must provide an odd number of non-repeating strings greater than or equal to 3.");
            System.out.println("Example: java RockPaperScissors rock paper scissors");
            return false;
        }
        Set<String> uniqueMoves = new HashSet<>(Arrays.asList(args));
        if (uniqueMoves.size() != numMoves) {
            System.out.println("Error: Moves must be non-repeating.");
            System.out.println("Example: java RockPaperScissors rock paper scissors");
            return false;
        }
        return true;
    }

    private static void printMenu(String[] moves) {
        System.out.println("Available moves:");
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + " - " + moves[i]);
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
        System.out.print("Enter your move: ");
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
