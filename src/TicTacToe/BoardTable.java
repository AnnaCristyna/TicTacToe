package TicTacToe;

public class BoardTable {
    private int size = 3;
    public char[][] board;

    public BoardTable() {
        board = new char[size][size];
        printGameInConsole();
    }

    public int getSize() {
        return size;
    }

    public char getPosition(int row, int column) {
        return board[row][column];
    }

    /**
     * @param row
     * @param col
     * @return
     */
    public boolean isPositionEmpty(int row, int col) {
        return board[row][col] == '\u0000' || board[row][col] == ' ';
    }

    public boolean isValidMove(int row, int col) {
        return isPositionEmpty(row, col) && isValidInput(row) && isValidInput(col);
    }

    public boolean isValidInput(int i) {
        boolean isValid = i >= 0 && i < size;

        if (!isValid) {
            System.out.println("valor inválido, por favor, digite números válidos e separados por espaço. Ex: 1 2");
        }

        return isValid;
    }

    public void printGameInConsole() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("  " + board[i][j] + "|");          
            }
            System.out.println("");          
        }
    }

    public void getWinner(char winnerSymbol) {
        if (winnerSymbol == 'X') {
            System.out.println("Parabéns Jogador, você ganhou!");
            return;
        }
        System.out.println("O robô venceu, vai deixar assim? Hora da revanche!");
    }
    
    public void makeMove(int row, int col, char symbol) throws InvalidMoveException {
        if(!isPositionEmpty(row, col)) {
            throw new InvalidMoveException(row, col);
        };

        board[row][col] = symbol;        
    }

    public boolean isGameOver(int movesCount) {
        if (movesCount <= 5) return false;

        Object[] result = hasWinner();
        boolean hasWinner = (boolean) result[0];

        if (hasWinner) {
            char winnerSymbol = (char) result[1];
            getWinner(winnerSymbol);
            return true;
        }

        if (isFullMarked()) {
            return true;            
        }

        return false;
    }

    public Object[] hasWinner() {
        Object[] result = hasSequenceInDiagonal();
        boolean hasWinner = (boolean) result[0];
        char winnerSymbol = (char) result[1];

        if (hasWinner) {
            return  new Object[]{hasWinner, winnerSymbol};
        }

        result = hasSequenceInRow();
        hasWinner = (boolean) result[0];

        if (hasWinner) {
            return  new Object[]{hasWinner, winnerSymbol};
        }

        result = hasSequenceInColumn();
        hasWinner = (boolean) result[0];

        if (hasWinner) {
            return  new Object[]{hasWinner, winnerSymbol};
        }

        return  new Object[]{hasWinner, ' '};
    }

    public Object[] hasSequenceInDiagonal() {
        char symbol = board[1][1];

        if (isValuesEqual(board[0][0], board[1][1], board[2][2])) {
            return new Object[]{true, symbol};
        }

        if (isValuesEqual(board[2][0], board[1][1], board[0][2])) {
            return new Object[]{true, symbol};
        }
        return new Object[]{false, ' '};
    }

    public Object[] hasSequenceInRow() {
        char symbol;
        for (int i = 0; i < size; i++) {
            if (isValuesEqual(board[i][0], board[i][1], board[i][2])) {
                symbol = board[i][0];
                return new Object[]{true, symbol};
            }
        }
        return new Object[]{false, ' '};
    }
        
    public  Object[] hasSequenceInColumn() {
        char symbol;
        for (int i = 0; i < size; i++) {
            if (isValuesEqual(board[0][i], board[1][i], board[2][i])) {
                symbol = board[i][0];
                return new Object[]{true, symbol};
            }
        }
        return new Object[]{false, ' '};
    }

    private boolean isValuesEqual(char a, char b, char c) {
        return a != ' ' && a == b && b == c;
    }

    public boolean isFullMarked() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(isPositionEmpty(row, col)) return false;
            }
        }
        System.out.println("Não há mais jogadas disponíveis.");
        return true;
    }  
}