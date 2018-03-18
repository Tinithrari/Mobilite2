package test;

import org.junit.Test;

import fr.ua.heugue_ydee.utils.Time;
import fr.ua.heugue_ydee.model.SprintScore;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Utilisateur on 27/02/2018.
 */

public class SprintScoreTest {

    @Test
    public void testConstructorDefault() {
        SprintScore sprintScore = new SprintScore();
        Time time = new Time(1,2,3,4);

        sprintScore.setId(Long.valueOf(1));
        sprintScore.setName("Pedro");
        sprintScore.setDuration(time);

        assertEquals(Long.valueOf(1), sprintScore.getId());
        assertEquals("Pedro", sprintScore.getName());
        assertEquals(1, sprintScore.getDuration().getHours());
        assertEquals(2, sprintScore.getDuration().getMinutes());
        assertEquals(3, sprintScore.getDuration().getSeconds());
        assertEquals(4, sprintScore.getDuration().getMilliseconds());
        assertEquals(time, sprintScore.getDuration());
    }

    @Test
    public void testConstructorWithoutId() {
        Time time = new Time(3,4,5,6);
        SprintScore sprintScore = new SprintScore("Pedro",time);

        assertEquals(Long.valueOf(0), sprintScore.getId());

        sprintScore.setId(Long.valueOf(2));
        assertEquals(Long.valueOf(2), sprintScore.getId());
        assertEquals("Pedro", sprintScore.getName());
        assertEquals(3, sprintScore.getDuration().getHours());
        assertEquals(4, sprintScore.getDuration().getMinutes());
        assertEquals(5, sprintScore.getDuration().getSeconds());
        assertEquals(6, sprintScore.getDuration().getMilliseconds());
        assertEquals(time, sprintScore.getDuration());
    }

    @Test
    public void testConstructorComplete() {
        SprintScore sprintScore = new SprintScore(Long.valueOf(3),"Cookie",7,8,9);

        assertEquals(Long.valueOf(3), sprintScore.getId());
        assertEquals("Cookie", sprintScore.getName());
        assertEquals(0, sprintScore.getDuration().getHours());
        assertEquals(7, sprintScore.getDuration().getMinutes());
        assertEquals(8, sprintScore.getDuration().getSeconds());
        assertEquals(9, sprintScore.getDuration().getMilliseconds());
    }
}
