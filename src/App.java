// import TicTacToe.BoardTable;
import TicTacToe.BoardTable;
import TicTacToe.HumanPlayer;   
import TicTacToe.IAPlayer;
import TicTacToe.InvalidMoveException;

import java.util.Scanner;

public class App {
    static HumanPlayer humanPlayer;
    static BoardTable boardTable ;
    static IAPlayer iaPlayer;
    static Scanner scanner;

    public static void main(String[] args) throws Exception {
        humanPlayer = new HumanPlayer('x');
        boardTable = new BoardTable();
        iaPlayer = new IAPlayer(boardTable);

        scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha! " + humanPlayer.getName());
        boolean playAgain = false;

        do {
            initGame();
            playAgain = humanPlayer.askPlayAgain();
        } while (playAgain);
    }
    
    public static void initGame() throws InvalidMoveException {
        int movesCount = 0;

        while (!boardTable.isGameOver(movesCount)) {
            // exibe o estado atual do boardTable
            
            int[] move = humanPlayer.requestPlay(scanner, boardTable, 'X');
            boardTable.makeMove(move[0], move[1], 'X');
            
            movesCount ++;
            
            if (boardTable.isGameOver(movesCount)) return;
            
            move = iaPlayer.play(boardTable);
            movesCount ++;
            boardTable.printGameInConsole();
        }

        boardTable.printGameInConsole();
    }
}

