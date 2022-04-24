package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelfAreaPage extends BasePage {

    @FindBy(xpath = "//div[@class='nav nav_mobile-fix no-print js-overflow-scroll']//a[@title='О себе']")
    WebElement aboutMySelf;

    public SelfAreaPage(WebDriver driver) {
        super(driver);
    }

    public MySelfPage enterToAboutMyself() {
        aboutMySelf.click();
        return new MySelfPage(driver);
    }

}
