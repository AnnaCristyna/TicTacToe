package TicTacToe;
import java.util.Scanner;

public class HumanPlayer {
    private String name;
    private char symbol;
    private Scanner scanner;
    
    public HumanPlayer(char symbol) {
        this.scanner = new Scanner(System.in);
        this.name = askName();
        this.symbol = symbol;
    }

    public String askName() {
        System.out.println("Digite o nome do jogador: ");
        String name = scanner.nextLine();
        setName(name);
        return name;
    }
    
    public int[] requestPlay(Scanner scanner, BoardTable boardTable, char symbol) {
        int row, column;
        int size = boardTable.getSize();
        do {
            System.out.println("Digite a linha (1-" + size + ") e coluna (1-" + size + ") da move (símbolo: " + symbol + "): ");
            row = scanner.nextInt() - 1;
            column = scanner.nextInt() - 1;
        } while (!boardTable.isValidMove(row, column));
        return new int[]{row, column};
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public boolean askPlayAgain() {
        System.out.println("Você quer jogar nomavemente? (s/n)");
        String playAgain =  scanner.nextLine().toLowerCase();

        return playAgain.equals("s");
    }
} 
