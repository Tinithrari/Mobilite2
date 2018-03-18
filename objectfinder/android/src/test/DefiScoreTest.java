package test;

import org.junit.Test;

import fr.ua.heugue_ydee.model.DefiScore;

import static junit.framework.TestCase.assertEquals;

/**
 * Classe effectuant des tests unitaires pour la classe DefiScore
 */
public class DefiScoreTest {

    @Test
    public void testConstructorDefault() {
        DefiScore defiScore = new DefiScore();

        defiScore.setId(Long.valueOf(1));
        defiScore.setName("Pedro");
        defiScore.setScore(7);

        assertEquals(Long.valueOf(1), defiScore.getId());
        assertEquals("Pedro", defiScore.getName());
        assertEquals(7, defiScore.getScore());
    }

    @Test
    public void testConstructorWithoutId() {
        DefiScore defiScore = new DefiScore("Jacky",8);

        assertEquals(Long.valueOf(0), defiScore.getId());
        assertEquals("Jacky", defiScore.getName());
        assertEquals(8, defiScore.getScore());
    }

    @Test
    public void testConstructorComplete() {
        DefiScore defiScore = new DefiScore(Long.valueOf(3),"Cookie",42);

        assertEquals(Long.valueOf(3), defiScore.getId());
        assertEquals("Cookie", defiScore.getName());
        assertEquals(42, defiScore.getScore());
    }
}