package wdFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import java.util.Locale;

import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName, String option) {

        BrowsersName command = BrowsersName.valueOf(webDriverName.toUpperCase(Locale.ROOT));

        switch (command){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                if (option != null) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(option.toLowerCase(Locale.ROOT));
                    return new ChromeDriver(chromeOptions);
                } else {
                    return new ChromeDriver();
                }
            case OPERA:
                WebDriverManager.operadriver().setup();
                if (option != null) {
                    OperaOptions operaOptions = new OperaOptions();
                    operaOptions.addArguments(option.toLowerCase(Locale.ROOT));
                    return new OperaDriver(operaOptions);
                } else {
                    return new OperaDriver();
                }
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

        }
    }
}
