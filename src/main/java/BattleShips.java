import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleShips {

    final int heightWidth = 10;
    final int shipsToHit = 13;
    final int[] ships = {5, 4, 4};
    List<List<Point>> points = new ArrayList<>();
    int[][] board = new int[heightWidth][heightWidth];
    int[][] computerBoard = new int[heightWidth][heightWidth];
    int shipsHit = 0;
    Random rand = new Random();


    public void placeShips() {
        //loop to create 3 ships
        int i = 0;
        while (i < ships.length) {
            //placed horizontally or diagonally
            int random = rand.nextInt(2);
            int randomColRow = rand.nextInt(heightWidth);
            List<Point> newPoints;
            boolean check = true;
            if (random == 0) {
                if ((heightWidth - randomColRow) > ships[i]) {
                    //check if ship exist on this square
                    if (checkIfExists(i, 1, randomColRow)) {
                        check = false;
                    }
                    //place if place is empty
                    if (check) {
                        newPoints = addPoint(i, 1, randomColRow);
                    } else {
                        continue;
                    }
                } else {
                    if (checkIfExists(i, 2, randomColRow)) {
                        check = false;
                    }
                    if (check) {
                        newPoints = addPoint(i, 2, randomColRow);
                    } else {
                        continue;
                    }
                }
            } else {
                if ((heightWidth - randomColRow) < ships[i]) {
                    if (checkIfExists(i, 1, randomColRow)) {
                        check = false;
                    }
                    if (check) {
                        newPoints = addPoint(i, 1, randomColRow);
                    } else {
                        continue;
                    }
                } else {
                    if (checkIfExists(i, 2, randomColRow)) {
                        check = false;
                    }
                    if (check) {
                        newPoints = addPoint(i, 2, randomColRow);
                    } else {
                        continue;
                    }
                }
            }
            points.add(newPoints);
            i++;
        }
    }

    public void checkIfHit(Point p) {
        int index = 0;
        //hit
        if (computerBoard[p.x][p.y] == 1) {
            board[p.x][p.y] = 1;
            for (List<Point> list : points) {
                for (Point point : list) {
                    if (point.x == p.x && point.y == p.y) {
                        shipsHit++;
                        points.get(index).remove(point);
                        checkIfSunk(points.get(index));
                        break;
                    }
                }
                index += 1;
            }
        } else {
            board[p.x][p.y] = -1;
            System.out.println("Ship missed!");
        }
    }

    public void checkIfSunk(List<Point> points) {
        if (points.size() == 0) {
            System.out.println("Ship sunk!");
            //displayBoard(board);
        } else {
            System.out.println("Ship hit!");
        }
    }

    public boolean checkIfExists(int i, int which, int randomColRow) {
        if (which == 1) {
            for (int j = 0; j < ships[i]; j++) {
                if (computerBoard[j][randomColRow] == 1) {
                    return true;
                }
            }
        } else {
            for (int j = 0; j < ships[i]; j++) {
                if (computerBoard[randomColRow][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Point> addPoint(int i, int which, int randomColRow) {
        List<Point> newPoints = new ArrayList<>();
        if (which == 1) {
            for (int j = 0; j < ships[i]; j++) {
                computerBoard[j][randomColRow] = 1;
                Point p = new Point(j, randomColRow);
                newPoints.add(p);
            }
        } else {
            for (int j = 0; j < ships[i]; j++) {
                computerBoard[randomColRow][j] = 1;
                Point p = new Point(randomColRow, j);
                newPoints.add(p);
            }
        }
        return newPoints;
    }

    public boolean isWin() {
        if (shipsHit == shipsToHit) {
            displayBoard(board);
            System.out.println("All ships are sunk. You won!");
            return true;
        } else {
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
                //unknown
                else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }
}
