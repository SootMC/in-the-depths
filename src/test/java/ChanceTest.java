import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class ChanceTest {
    private static int highestRoll = 0;
    private static int timesRolled = 0;
    private static int timesJackpotHit = 0;
    @Test
    public void testChance() {
        while (true) {
            int chance = 250;
            int rand = (int) (Math.random() * chance) + 1;
            int jackpot = 66;

            timesRolled++;

            assert(rand >= 1 && rand <= chance);

            if (rand > highestRoll) {
                highestRoll = rand;
            }

            if (rand == jackpot) {
                System.out.println("Jackpot!");
                timesJackpotHit++;
            }

            if(timesRolled > 1000000) {
                break;
            }
        }
    }

    @AfterAll
    public static void printStats() {
        System.out.println("Highest roll: " + highestRoll);
        System.out.println("Times rolled: " + timesRolled);
        System.out.println("Times jackpot hit: " + timesJackpotHit);
    }
}
