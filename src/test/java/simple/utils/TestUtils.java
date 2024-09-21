package simple.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestUtils {
    public static String getProperty(String key) {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("base.properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(key);
    }
}
