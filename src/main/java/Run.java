import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        //display info & rules
        startInfo();
        Scanner s = new Scanner(System.in);
        s.nextLine();
        BattleShips battleShips = new BattleShips();
        //computer places ships on grid
        battleShips.placeShips();
        String square = "";
        int row = 0;
        int col = 0;
        //play till game ends
        while (!battleShips.isWin()) {
            battleShips.displayBoard(battleShips.board);
            System.out.println("Type your square to shoot: ");
            square = s.nextLine();
            int tryInput = tryInput(square);
            //check if user want to stop playing
            if (tryInput == -1) {
                break;
            }
            //check if input length is correct
            else if (tryInput == 0) {
                System.out.println("Wrong input, try again!");
            }
            //check if the coordinates are good
            else {
                row = Integer.parseInt(square.substring(square.length() / 2));
                col = square.substring(0, (square.length() / 2)).charAt(0) - 'A' + 1;
                if (row > 10 || row < 1 || col > 10 || col < 1) {
                    System.out.println("Wrong input, try again!");
                    continue;
                }
                Point p = new Point(row - 1, col - 1);
                battleShips.checkIfHit(p);
            }

        }


    }


    public static int tryInput(String square) {
        if (square.equalsIgnoreCase("X")) {
            return -1;
        } else if (square.length() != 2 && !square.substring(square.length() / 2).equalsIgnoreCase("10")) {
            return 0;
        } else {
            return 1;
        }


    }

    public static void startInfo() {
        System.out.println("Hello, this is a simplified version of battleships game.");
        System.out.println("You are given 10x10 grid, where computer placed ");
        System.out.println("one battleship with length of 5 squares and ");
        System.out.println("two battleships with length of 4 squares each.");
        System.out.println("Each round you have to guess where computer placed ships.");
        System.out.println("After shooting you computer will display if your bullet ");
        System.out.println("hits, misses or sinks the ship.");
        System.out.println("Additionally after every round computer will display grid with your shots.");
        System.out.println("'0' - You missed shot");
        System.out.println("'X' - You hit shot");
        System.out.println("'-' - Square is unknown");
        System.out.println("To shoot type coordinates of the form 'A5' where");
        System.out.println("'A' is the column and '5' is the row.");
        System.out.println("Game ends when all ships are sunk,");
        System.out.println("but if you dont want to play anymore type 'X' to end game.");
        System.out.println();
        System.out.println("If u want to start press Enter key...");
    }

}
