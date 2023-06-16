package TicTacToe;

import java.util.Random;

public class IAPlayer {
    private char symbol = 'O';
    private Random random;
    private int[] move = new int[2];

    public IAPlayer ( BoardTable board ) {
        this.random = new Random();
    }
    
    public int[] play(BoardTable board) throws InvalidMoveException {
        boolean validMove = false;

        // Verifica se pode ganhar na próxima jogada
        validMove = checkVictoryPossibility(board, symbol);
        
        if (!validMove) {
            // Verifica se precisa bloquear o jogador humano
            validMove = checkVictoryPossibility(board, 'X');
        }
        
        if (!validMove) {
            // Escolhe uma posição aleatória
            move = pickRandomPlay(board);
        }

        // Realiza a jogada escolhida
        board.makeMove(move[0], move[1], 'O');

        return move;
    }

    private boolean checkVictoryPossibility(BoardTable board, char symbol) {

        if (checkPossibilityInRow(board, symbol)) return true;
        if (checkPossibilityInColumn(board, symbol)) return true;
        if (checkPossibilityInFirstDiogonal(board, symbol)) return true;
        if (checkPossibilityInSecondDiogonal(board, symbol)) return true; 
    
        return false;
    }
    
    private int[] pickRandomPlay(BoardTable board) {
        int[] move = new int[2];
        int row, column;
        int size = board.getSize();
    
        do {
            row = random.nextInt(size);
            column = random.nextInt(size);
        } while (!board.isPositionEmpty(row, column));
    
        move[0] = row;
        move[1] = column;
    
        return move;
    }

    private boolean checkPossibilityInRow(BoardTable board, char symbol) {
        for (int i = 0; i < board.getSize(); i++) {
            int count = 0;
            int emptyposition = -1;
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getPosition(i, j) == symbol) {
                    count++;
                } else if (board.getPosition(i, j) == '-') {
                    emptyposition = j;
                }
            }
            if (count == 2 && emptyposition != -1) {
                move[0] = i;
                move[1] = emptyposition;
                return true;
            }
        }
        return false;
    }

    private boolean checkPossibilityInColumn(BoardTable board, char symbol) {
        // Verifica se pode ganhar na coluna
        for (int j = 0; j < board.getSize(); j++) {
            int count = 0;
            int emptyposition = -1;
            for (int i = 0; i < board.getSize(); i++) {
                if (board.getPosition(i, j) == symbol) {
                    count++;
                } else if (board.getPosition(i, j) == '-') {
                    emptyposition = i;
                }
            }
            if (count == 2 && emptyposition != -1) {
                move[0] = emptyposition;
                move[1] = j;
                return true;
            }
        }
        return false;

    }

    private boolean checkPossibilityInFirstDiogonal(BoardTable board, char symbol) {
        // Verifica se pode ganhar na diagonal principal
        int count = 0;
        int emptyposition = -1;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getPosition(i, i) == symbol) {
                count++;
            } else if (board.getPosition(i, i) == '-') {
                emptyposition = i;
            }
        }
        if (count == 2 && emptyposition != -1) {
            move[0] = emptyposition;
            move[1] = emptyposition;
            return true;
        }
        return false;
    }

    private boolean checkPossibilityInSecondDiogonal(BoardTable board, char symbol) {
        // Verifica se pode ganhar na diagonal secundária
        int count = 0;
        int emptyposition = -1;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getPosition(i, board.getSize() - 1 - i) == symbol) {
                count++;
            } else if (board.getPosition(i, board.getSize() - 1 - i) == '-') {
                emptyposition = i;
            }
        }
        if (count == 2 && emptyposition != -1) {
            move[0] = emptyposition;
            move[1] = board.getSize() - 1 - emptyposition;
            return true;
        }
      return false;
    }
}