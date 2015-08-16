package other;

import org.junit.Test;

import static main.other.Util.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by koxa on 02.08.2015.
 */
public class UtilTest {

    @Test
    public void testGcd_0_0() {
        assertEquals(0, gcd(0, 0));
    }

    @Test
    public void testGcd_0_1() {
        assertEquals(1, gcd(0, 1));
    }

    @Test
    public void testGcd_1_1() {
        assertEquals(1, gcd(1, 1));
    }

    @Test
    public void testGcd_7_3() {
        assertEquals(1, gcd(7, 3));
    }

    @Test
    public void testGcd_252_1080() {
        assertEquals(36, gcd(252, 1080));
    }
}
