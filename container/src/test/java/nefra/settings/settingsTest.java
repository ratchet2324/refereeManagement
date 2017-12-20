package nefra.settings;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.Assert;
import org.junit.Test;

public class settingsTest {

    @Test
    public void testStoring()
    {
        RandomStringGenerator rsg = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        for (int i = 0; i < 50; i++)
        {
            String a = rsg.generate(10);
            String b = rsg.generate(10);
            Settings.writeSetting(a, b, "random generated: " + i);
            Assert.assertEquals("not set properly on: " + i + " a = " + a + " b = " + b, true, Settings.containsSetting(a));
            Assert.assertEquals("unable to get properly", b, Settings.getSetting(a));
        }
        for (int i = 0; i < 50; i++)
        {
            Settings.writeSetting(rsg.generate(10), rsg.generate(10));
        }
    }
}
