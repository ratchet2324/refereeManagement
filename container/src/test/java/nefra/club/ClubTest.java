package nefra.club;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClubTest {
    private Club ex1, ex2, ex3, ex4, ex5;


    @Before
    public void setUp()
    {
        ex1 = new Club("Westside", "XYZ ABC", "0101010101");
        ex2 = new Club("Eastside", "DEF GHI", "0202020202");
        ex3 = new Club("Norths", "Phil Weaton Oval", "Armidale", "NSW", "2350");
        ex4 = new Club("Norths", "Phil Weaton Oval", "Armidale", "NSW", "2350",
                "JKL MNO", "0303030303");
        ex5 = new Club("Ace FC");

        System.out.println(ex1.toString() + "\n");
        System.out.println(ex2.toString() + "\n");
        System.out.println(ex3.toString() + "\n");
        System.out.println(ex4.toString() + "\n");
        System.out.println(ex5.toString() + "\n");
    }

    @Test
    public void setupClubs ()
    {
        assertEquals("Not Westside","Westside", ex1.getClubName());
        assertEquals("Not Eastside","Eastside", ex2.getClubName());
        assertEquals("Not Norths", "Norths", ex3.getClubName());
        assertEquals("Not Norths", "Norths", ex4.getClubName());
        assertEquals("Not Ace FC", "Ace FC", ex5.getClubName());
    }

    @Test
    public void feeTest() {
        ex1.addToWeeklyFee(100);
        ex2.addToWeeklyFee(500);

        System.out.println(ex1.toString() + "\n");
        System.out.println(ex2.toString() + "\n");

        assertEquals(100.00, ex1.getWeeklyFee(), 0.001);
        assertEquals(500.00, ex2.getWeeklyFee(),0.001);

        ex1.resetWeeklyFee();
        ex2.resetWeeklyFee();

        System.out.println(ex1.toString() + "\n");
        System.out.println(ex2.toString() + "\n");

        assertEquals(0.00, ex1.getWeeklyFee(), 0.001);
        assertEquals(0.00, ex2.getWeeklyFee(),0.001);
        assertEquals(100.00, ex1.getTotalFee(), 0.001);
        assertEquals(500.00, ex2.getTotalFee(),0.001);

        ex1.resetTotalFee();
        ex2.resetTotalFee();

        assertEquals(0.00, ex1.getTotalFee(), 0.001);
        assertEquals(0.00, ex2.getTotalFee(),0.001);
    }

    @Test
    public void usingSet()
    {
        ex1.setClubName("ACE Football Club");
        ex1.setPresidentName("BDF");
        ex1.setPresidentContact("ace@bdf.com");
        ex1.setStreet("Hallelujah Street");
        ex1.setSuburb("Jamestown");
        ex1.setState("QLD");
        ex1.setPostcode("9857");

        assertEquals("Not set to ACE Football Club", "ACE Football Club", ex1.getClubName());
        assertEquals("Not set to BDF", "BDF", ex1.getPresidentName());
        assertEquals("Not set to ace@bdf.com", "ace@bdf.com", ex1.getPresidentContact());
        assertEquals("Not set to Hallelujah Street", "Hallelujah Street", ex1.getStreet());
        assertEquals("Not set to Jamestown", "Jamestown", ex1.getSuburb());
        assertEquals("Not set to QLD", "QLD", ex1.getState());
        assertEquals("Not set to 9857", "9857", ex1.getPostcode());
    }
}
