public class GameRules {
    public static String determineWinner(int userMoveIndex, int computerMoveIndex, int numMoves) {
        int half = numMoves / 2;
        if (userMoveIndex == computerMoveIndex) {
            return "It's a tie!";
        } else if ((userMoveIndex > computerMoveIndex && userMoveIndex - computerMoveIndex <= half) ||
                   (userMoveIndex < computerMoveIndex && computerMoveIndex - userMoveIndex > half)) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}
