import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {


    @Test
    void canCreateShips(){
        BattleShips battleShips = new BattleShips();
        battleShips.placeShips();
    }

    @Test
    void canShot() {
        BattleShips battleShips = new BattleShips();
        Point p = new Point(0,0);
        battleShips.checkIfHit(p);
    }

    @Test
    void canDisplayBoard() {
        BattleShips battleShips = new BattleShips();
        battleShips.displayBoard(battleShips.board);
    }

    @Test
    void canWin() {
        BattleShips battleShips = new BattleShips();
        battleShips.placeShips();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                Point p = new Point(i,j);
                battleShips.checkIfHit(p);
            }
        }
        assertTrue(battleShips.isWin());
    }

    @Test
    void amountOfShips() {
        int amount = 0;
        for (int i = 0; i< 100; i++){
            BattleShips battleShips = new BattleShips();
            battleShips.placeShips();
            for (int j = 0; j < 10; j++){
                for (int k = 0; k < 10; k++){
                    if (battleShips.computerBoard[j][k] == 1){
                        amount++;
                    }
                }
            }
        }
        System.out.println(amount);
        assertEquals(1300,amount);
    }

    @Test
    void canAddPoints() {
        BattleShips battleShips = new BattleShips();
        battleShips.addPoint(0,1,1);
        battleShips.addPoint(1,1,2);
        battleShips.addPoint(2,1,3);
        int amount = 0;
        for (int j = 0; j < 10; j++){
            for (int k = 0; k < 10; k++){
                if (battleShips.computerBoard[j][k] == 1){
                    amount++;
                }
            }
        }
        assertEquals(13,amount);
    }

    @Test
    void checksIfPlaceIsTaken() {
        BattleShips battleShips = new BattleShips();
        battleShips.addPoint(0,1,1);
        assertTrue(battleShips.checkIfExists(0,1,1));
    }

    @Test
    void checksIfPlaceIsEmpty() {
        BattleShips battleShips = new BattleShips();
        assertFalse(battleShips.checkIfExists(0,1,1));
    }
}
