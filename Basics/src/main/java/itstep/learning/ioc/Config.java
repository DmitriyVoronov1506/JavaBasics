package itstep.learning.ioc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config implements IConfig {
    @Override
    public String getParameter(String name) {

        FileInputStream fs;
        Properties property = new Properties();

        try {

            fs = new FileInputStream("Config.ini");
            property.load(fs);

            String value = property.getProperty(name);

            if(value == null || value.isEmpty()) {
                return null;
            }

            return value;

        }
        catch (IOException e) {
            System.err.println("Error while reading a file!");
            return null;
        }
    }
}
