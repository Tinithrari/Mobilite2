package test;

import org.junit.Test;

import fr.ua.heugue_ydee.utils.Time;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by xavierheugue on 26/02/2018.
 */

public class TimeTest {

    @Test
    public void testConstructorNormalCase() {
        Time zero = new Time();
        Time dummyTime = new Time(1, 1, 1, 1);

        assertEquals(0, zero.getMilliseconds());
        assertEquals(0, zero.getSeconds());
        assertEquals(0, zero.getMinutes());
        assertEquals(0, zero.getHours());

        assertEquals(1, dummyTime.getMilliseconds());
        assertEquals(1, dummyTime.getSeconds());
        assertEquals(1, dummyTime.getMinutes());
        assertEquals(1, dummyTime.getHours());
    }

    @Test
    public void testConstructorOverTimed() {
        Time overMillis = new Time(0, 0, 0, 1000);
        Time overSecs = new Time(0, 0, 60, 0);
        Time overMins = new Time(0, 60, 0, 0);
        Time overMillisWithMod = new Time(0, 0, 0, 1030);
        Time overSecsWithMod = new Time(0, 0, 90, 0);
        Time overMinsWithMod = new Time(0, 90, 0, 0);

        assertEquals(0, overMillis.getMilliseconds());
        assertEquals(1, overMillis.getSeconds());

        assertEquals(0, overSecs.getSeconds());
        assertEquals(1, overSecs.getMinutes());

        assertEquals(0, overMins.getMinutes());
        assertEquals(1, overMins.getHours());

        assertEquals(30, overMillisWithMod.getMilliseconds());
        assertEquals(1, overMillisWithMod.getSeconds());

        assertEquals(30, overSecsWithMod.getSeconds());
        assertEquals(1, overSecsWithMod.getMinutes());

        assertEquals(30, overMinsWithMod.getMinutes());
        assertEquals(1, overMinsWithMod.getHours());
    }

    @Test
    public void testConstructorUnderTimedNormalCase() {
        Time underMillis = new Time(0, 0, 1, -1);
        Time underSeconds = new Time(0, 1, -1, 0);
        Time underMins = new Time(1, -1, 0, 0);
        Time underHours = new Time(-1, 0,0, 0);

        assertEquals(999, underMillis.getMilliseconds());
        assertEquals(0, underMillis.getSeconds());

        assertEquals(0, underSeconds.getMinutes());
        assertEquals(59, underSeconds.getSeconds());

        assertEquals(0, underMins.getHours());
        assertEquals(59, underMins.getMinutes());

        assertEquals(0, underHours.getHours());
        assertEquals(0, underHours.getMinutes());
        assertEquals(0, underHours.getSeconds());
        assertEquals(0, underHours.getMilliseconds());
    }

    @Test
    public void testConstructorUnderTimeExtremeCase() {
        Time underMillis = new Time(0, 0, 0, -1);
        Time underSeconds = new Time(0, 0, -1, 0);
        Time underMins = new Time(0, -1, 0, 0);

        assertEquals(0, underMins.getHours());
        assertEquals(0, underMins.getMinutes());
        assertEquals(0, underMins.getSeconds());
        assertEquals(0, underMins.getMilliseconds());

        assertEquals(0, underSeconds.getHours());
        assertEquals(0, underSeconds.getMinutes());
        assertEquals(0, underSeconds.getSeconds());
        assertEquals(0, underSeconds.getMilliseconds());

        assertEquals(0, underMillis.getHours());
        assertEquals(0, underMillis.getMinutes());
        assertEquals(0, underMillis.getSeconds());
        assertEquals(0, underMillis.getMilliseconds());
    }

    @Test
    public void testAddTime() {
        Time zero = new Time();
        Time expected1 = new Time(0, 0, 0, 20);
        Time hourExpectation = new Time(1, 0, 0, 0);

        zero.addTime(0 ,0, 0, 20);
        assertEquals(expected1, zero);

        hourExpectation.addTime(-1, 0, 0, 0);
        assertEquals(new Time(), hourExpectation);
    }

    @Test
    public void testAddTimeOverMillis() {
        Time closeToASecond = new Time(0, 0, 0, 999);
        Time aSecond = new Time(0, 0, 1, 0);

        closeToASecond.addTime(0, 0, 0, 1);

        assertEquals(aSecond, closeToASecond);
    }

    @Test
    public void testAddTimeOverSeconds() {
        Time closeToAMinute = new Time(0, 0, 59, 0);
        Time aMinute = new Time(0, 1, 0, 0);

        closeToAMinute.addTime(0, 0, 1, 0);

        assertEquals(aMinute, closeToAMinute);
    }

    @Test
    public void testAddTimeOverMinutes() {
        Time closeToAHour = new Time(0, 59, 0, 0);
        Time aHour = new Time(1, 0, 0, 0);

        closeToAHour.addTime(0, 1, 0, 0);

        assertEquals(aHour, closeToAHour);
    }

    @Test
    public void testAddTimeUnderMillis() {
        Time closeToASecond = new Time(0, 0, 0, 999);
        Time aSecond = new Time(0, 0, 1, 0);

        aSecond.addTime(0, 0, 0, -1);

        assertEquals(closeToASecond, aSecond);
    }

    @Test
    public void testAddTimeUnderSeconds() {
        Time closeToAMinute = new Time(0, 0, 59, 0);
        Time aMinute = new Time(0, 1, 0, 0);

        aMinute.addTime(0, 0, -1, 0);

        assertEquals(closeToAMinute, aMinute);
    }

    @Test
    public void testAddTimeUnderMinutes() {
        Time closeToAHour = new Time(0, 59, 0, 0);
        Time aHour = new Time(1, 0, 0, 0);

        aHour.addTime(0, -1, 0, 0);

        assertEquals(closeToAHour, aHour);
    }

    @Test
    public void testAddTimeUnderTimeExtremeCase() {
        Time underMillis = new Time();
        Time underSeconds = new Time();
        Time underMins = new Time();

        underMillis.addTime(0, 0, 0, -1);
        underSeconds.addTime(0, 0, -1, 0);
        underMins.addTime(0, -1, 0, 0);

        assertEquals(0, underMins.getHours());
        assertEquals(0, underMins.getMinutes());
        assertEquals(0, underMins.getSeconds());
        assertEquals(0, underMins.getMilliseconds());

        assertEquals(0, underSeconds.getHours());
        assertEquals(0, underSeconds.getMinutes());
        assertEquals(0, underSeconds.getSeconds());
        assertEquals(0, underSeconds.getMilliseconds());

        assertEquals(0, underMillis.getHours());
        assertEquals(0, underMillis.getMinutes());
        assertEquals(0, underMillis.getSeconds());
        assertEquals(0, underMillis.getMilliseconds());
    }
}
