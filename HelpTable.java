public class HelpTable {
    public static void printHelpTable(String[] moves) {
        int numMoves = moves.length;
        int cellWidth = 10;

        // Print top row with moves
        System.out.print("+-------------");
        for (String move : moves) {
            System.out.print("+");
            System.out.print("-".repeat(cellWidth));
        }
        System.out.println("+");

        // Print header row with move names
        System.out.print("| v PC\\User > ");
        for (String move : moves) {
            System.out.printf("| %-"+cellWidth+"s", move);
        }
        System.out.println("|");

        // Print the separator line
        System.out.print("+-------------");
        for (int i = 0; i < numMoves; i++) {
            System.out.print("+");
            System.out.print("-".repeat(cellWidth));
        }
        System.out.println("+");

        // Print the table rows with Win/Lose/Draw
        for (int i = 0; i < numMoves; i++) {
            System.out.printf("| %-11s ", moves[i]);
            for (int j = 0; j < numMoves; j++) {
                if (i == j) {
                    System.out.printf("| %-"+cellWidth+"s", "Draw");
                } else {
                    int half = numMoves / 2;
                    if ((i > j && i - j <= half) || (i < j && j - i > half)) {
                        System.out.printf("| %-"+cellWidth+"s", "Win");
                    } else {
                        System.out.printf("| %-"+cellWidth+"s", "Lose");
                    }
                }
            }
            System.out.println("|");

            // Print the separator line
            System.out.print("+-------------");
            for (int k = 0; k < numMoves; k++) {
                System.out.print("+");
                System.out.print("-".repeat(cellWidth));
            }
            System.out.println("+");
        }
    }
}
