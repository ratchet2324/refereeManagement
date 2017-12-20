package nefra.referee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import nefra.exceptions.DelLog;


public class RefereeTest {
    private Referee ex1, ex2, ex3, ex4;

    @Before
    public void setUp()
    {
        ex1 = new Referee("ABC", "DEF", null, null);
        ex2 = new Referee("MNO", "PQR", null,"0303030303");
        ex3 = new Referee("GHI", "JKL", "abc@def.com", null);
        ex4 = new Referee("STU", "VWX","abc@def.com", "0404040404");

        DelLog.getInstance().Log(ex1.toString());
        DelLog.getInstance().Log(ex2.toString());
        DelLog.getInstance().Log(ex3.toString());
        DelLog.getInstance().Log(ex4.toString());
    }

    @Test
    public void setupReferees()
    {
        assertEquals("ex1 first name not ABC", "ABC", ex1.getFirstName());
        assertEquals("ex1 last name not DEF", "DEF", ex1.getLastName());

        assertEquals("ex2 email not abc@def.com", "abc@def.com", ex3.getEmail());
        assertEquals("ex3 phone not 0303030303", "0303030303", ex2.getPhone());

        assertEquals("ex4 first name not STU", "STU", ex4.getFirstName());
        assertEquals("ex4 last name not VWX", "VWX", ex4.getLastName());
        assertEquals("ex4 email not abc@def.com", "abc@def.com", ex4.getEmail());
        assertEquals("ex4 phone not 0404040404", "0404040404", ex4.getPhone());
    }

    @Test
    public void feeTest()
    {
        ex1.addToWeeklyFee(100);
        ex2.addToWeeklyFee(500);

        DelLog.getInstance().Log(ex1.toString() + "\n");
        DelLog.getInstance().Log(ex2.toString() + "\n");

        assertEquals(100.00, ex1.getWeeklyFee(), 0.001);
        assertEquals(500.00, ex2.getWeeklyFee(),0.001);

        ex1.resetWeeklyFee();
        ex2.resetWeeklyFee();

        DelLog.getInstance().Log(ex1.toString() + "\n");
        DelLog.getInstance().Log(ex2.toString() + "\n");

        assertEquals(0.00, ex1.getWeeklyFee(), 0.001);
        assertEquals(0.00, ex2.getWeeklyFee(),0.001);
        assertEquals(100.00, ex1.getTotalFee(), 0.001);
        assertEquals(500.00, ex2.getTotalFee(),0.001);

        ex1.resetTotalFee();
        ex2.resetTotalFee();
        DelLog.getInstance().Log(ex1.toString() + "\n");
        DelLog.getInstance().Log(ex2.toString() + "\n");
        assertEquals(0.00, ex1.getTotalFee(), 0.001);
        assertEquals(0.00, ex2.getTotalFee(),0.001);
    }

    @Test
    public void usingSet()
    {
        ex1.setFirstName("ACE");
        ex1.setLastName("BDF");
        ex1.setEmail("ace@bdf.com");
        ex1.setPhone("0808080808");

        assertEquals("Not set to ACE", "ACE", ex1.getFirstName());
        assertEquals("Not set to BDF", "BDF", ex1.getLastName());
        assertEquals("Not set to ace@bdf.com", "ace@bdf.com", ex1.getEmail());
        assertEquals("Not set to 0808080808", "0808080808", ex1.getPhone());
    }
}
