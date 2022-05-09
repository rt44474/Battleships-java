import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleShips {

    //public List<Point> points;
    List<List<Point>> points = new ArrayList<>();
    int[][] board = new int[10][10];
    int[][] computerBoard = new int[10][10];
    int[] ships = {5,4,4};
    int shipsToHit = 13;
    int shipsHit = 0;
    Random rand = new Random();


    public void checkIfHit(Point p){
        //hit
        if (computerBoard[p.x][p.y] == 1){
            board[p.x][p.y] = 1;
        }
    }

    public void placeShips() {
        //loop to create 3 ships
        int i = 0;
        while (i < 3) {
            int random = rand.nextInt(2);
            int randomColRow = rand.nextInt(10);
            List<Point> newPoints = new ArrayList<>();
            boolean check = true;
            if (random == 0){
                if ((10 - randomColRow) > ships[i]){
                    for (int j = 0; j < ships[i]; j++) {
                        if (computerBoard[j][randomColRow] == 1){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        for (int j = 0; j < ships[i]; j++) {
                            computerBoard[j][randomColRow] = 1;
                            Point p = new Point(j,randomColRow);
                            newPoints.add(p);
                        }
                    }
                    else {
                        continue;
                    }
                }
                else {
                    for (int j = 0; j < ships[i]; j++) {
                        if (computerBoard[randomColRow][j] == 1){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        for (int j = 0; j < ships[i]; j++) {
                            computerBoard[randomColRow][j] = 1;
                            Point p = new Point(randomColRow,j);
                            newPoints.add(p);
                        }
                    }
                    else {
                        continue;
                    }
                }
            }
            else {
                if ((10 - randomColRow) < ships[i]){
                    for (int j = 0; j < ships[i]; j++) {
                        if (computerBoard[j][randomColRow] == 1){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        for (int j = 0; j < ships[i]; j++) {
                            computerBoard[j][randomColRow] = 1;
                            Point p = new Point(j,randomColRow);
                            newPoints.add(p);
                        }
                    }
                    else {
                        continue;
                    }
                }
                else {
                    for (int j = 0; j < ships[i]; j++)  {
                        if (computerBoard[randomColRow][j] == 1){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        for (int j = 0; j < ships[i]; j++) {
                            computerBoard[randomColRow][j] = 1;
                            Point p = new Point(randomColRow,j);
                            newPoints.add(p);
                        }
                    }
                    else {
                        continue;
                    }
                }
            }
            points.add(newPoints);
            i++;
        }
        points.forEach(System.out::println);
        displayBoard(computerBoard);
    }



    public boolean isWin() {
        if (shipsHit == 13){
            return true;
        }
        else {
            return false;
        }
    }

    public void displayBoard(int[][] board) {
        String col = "   A B C D E F G H I J";
        int[] row = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //columns
        System.out.println(col);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //rows
                if (j == 0) {
                    if (i != 9) {
                        System.out.print(row[i] + "  ");
                    } else {
                        System.out.print(row[i] + " ");
                    }
                }
                //miss
                if (board[i][j] == -1) {
                    System.out.print("O" + " ");
                }
                //hit
                else if (board[i][j] == 1) {
                    System.out.print("X" + " ");
                }
                //sinked ship
                else if (board[i][j] == 2) {
                    System.out.print("+" + " ");
                }
                //unknown
                else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }
}
