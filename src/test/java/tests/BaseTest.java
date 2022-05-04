package tests;

import wdFactory.WebDriverFactory;
import config.ConfigServer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {

    protected ConfigServer cfg = ConfigFactory.create(ConfigServer.class);
    protected WebDriver driver;

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void initDriver(){
        String browserName = System.getProperty("br");
        String optionName = System.getProperty("opt");

        WebDriverManager.chromedriver().setup();

        driver = WebDriverFactory.create(browserName, optionName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

}
