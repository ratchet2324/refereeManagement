package nefra.game;

import nefra.exceptions.DelLog;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivisionTest {
    private Division ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8;

    @Before
    public void setUp()
    {
        ex1 = new Division("Division 1", 85.0, 50.0);
        ex2 = new Division("Division 2", 85.0, 50.0);
        ex3 = new Division("Division 3", 85.0, 50.0);
        ex4 = new Division("16A", 45.0, 20.0);
        ex5 = new Division("16B", 45.0, 20.0);
        ex6 = new Division("14A", 30.0, 15.0);
        ex7 = new Division("14B", 30.0, 15.0);
        ex8 = new Division("12A", 20.0, 10.0);

        DelLog.getInstance().Log(ex1.toString());
        DelLog.getInstance().Log(ex2.toString());
        DelLog.getInstance().Log(ex3.toString());
        DelLog.getInstance().Log(ex4.toString());
        DelLog.getInstance().Log(ex5.toString());
        DelLog.getInstance().Log(ex6.toString());
        DelLog.getInstance().Log(ex7.toString());
        DelLog.getInstance().Log(ex8.toString());
    }

    @Test
    public void setupDivisions()
    {
        assertEquals("Not Division 1", "Division 1", ex1.getDivisionName());
        assertEquals(85.00, ex1.getMainRefereeFee(), 0.001);
        assertEquals(50.00, ex1.getArFee(), 0.001);

        assertEquals("Not Division 2", "Division 2", ex2.getDivisionName());
        assertEquals(85.00, ex2.getMainRefereeFee(), 0.001);
        assertEquals(50.00, ex2.getArFee(), 0.001);

        assertEquals("Not Division 3", "Division 3", ex3.getDivisionName());
        assertEquals(85.00, ex3.getMainRefereeFee(), 0.001);
        assertEquals(50.00, ex3.getArFee(), 0.001);

        assertEquals("Not 16A", "16A", ex4.getDivisionName());
        assertEquals(45.00, ex4.getMainRefereeFee(), 0.001);
        assertEquals(20.00, ex4.getArFee(), 0.001);

        assertEquals("Not 16B", "16B", ex5.getDivisionName());
        assertEquals(45.00, ex5.getMainRefereeFee(), 0.001);
        assertEquals(20.00, ex5.getArFee(), 0.001);

        assertEquals("Not 14A", "14A", ex6.getDivisionName());
        assertEquals(30.00, ex6.getMainRefereeFee(), 0.001);
        assertEquals(15.00, ex6.getArFee(), 0.001);

        assertEquals("Not 14B", "14B", ex7.getDivisionName());
        assertEquals(30.00, ex7.getMainRefereeFee(), 0.001);
        assertEquals(15.00, ex7.getArFee(), 0.001);

        assertEquals("Not 12A", "12A", ex8.getDivisionName());
        assertEquals(20.00, ex8.getMainRefereeFee(), 0.001);
        assertEquals(10.00, ex8.getArFee(), 0.001);
    }

    @Test
    public void usingSet()
    {
        ex1.setDivisionName("Division 0");
        ex1.setMainRefereeFee(160);
        ex1.setArFee(80);

        assertEquals("Not set to Division 0", "Division 0", ex1.getDivisionName());
        assertEquals(160.00, ex1.getMainRefereeFee(), 0.001);
        assertEquals(80.00, ex1.getArFee(), 0.001);
    }
}
