package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css = "button[data-modal-id=\"new-log-reg\"]")
    WebElement entryButton;
    @FindBy(css = "input.new-input[name='email'][type='text']")
    WebElement email;
    @FindBy(css = "input.new-input[name='password'][type='password']")
    WebElement password;
    @FindBy(css = "button[type='submit'][class='new-button new-button_full new-button_blue new-button_md']")
    WebElement enter;
    @FindBy(css = "p[class='header2-menu__item-text header2-menu__item-text__username']")
    WebElement userNameTab;
    @FindBy(css = "a[title='Личный кабинет']")
    WebElement selfArea;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage auth(){
        entryButton.click();
        email.sendKeys(cfg.email());
        password.sendKeys(cfg.pass());
        enter.click();
        return this;
    }

    public SelfAreaPage enterToSelfArea(){
        userNameTab.click();
        selfArea.click();
        return new SelfAreaPage(driver);
    }

}
