import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTest {
    private double converttovoltage(long analogvals) {
        return analogvals * (5.0 / 1023.0);
    }
    @Test
    public void testingvoltageconversion() {
        assertEquals(0.0, converttovoltage(0), 0.0001);


        assertEquals(2.5024, converttovoltage(512), 0.0001);

        assertEquals(5.0, converttovoltage(1023), 0.0001);

        assertEquals( 1.2512, converttovoltage(256), 0.0001);
    }
}