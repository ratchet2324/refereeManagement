package nefra.settings;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

public class settingsTest {

    @Test
    public void testStoring()
    {
        RandomStringGenerator rsg = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        for (int i = 0; i < 50; i++)
        {
            Settings.writeSetting(rsg.generate(10), rsg.generate(10), "random generated: " + i);
        }
        for (int i = 0; i < 50; i++)
        {
            Settings.writeSetting(rsg.generate(10), rsg.generate(10));
        }
    }
}
