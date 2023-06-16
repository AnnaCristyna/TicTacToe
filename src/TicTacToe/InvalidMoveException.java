package TicTacToe;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(int row, int col) {
        super();
    }
    public InvalidMoveException(String message) {
        super(message);
    }
}
