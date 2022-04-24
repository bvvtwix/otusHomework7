package Pages;

import data.MyselfData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MySelfPage extends BasePage {

    @FindBy(css = "input[name='fname']")
    WebElement fname;
    @FindBy(css = "input[name='fname_latin']")
    WebElement fnameLatin;
    @FindBy(css = "input[name='lname']")
    WebElement lname;
    @FindBy(css = "input[name='lname_latin']")
    WebElement lnameLatin;
    @FindBy(css = "input[name='blog_name']")
    WebElement blogName;
    @FindBy(css = "input[name='date_of_birth']")
    WebElement birthDate;
    @FindBy(css = "div[data-slave-selector='.js-lk-cv-dependent-slave-city']")
    WebElement countryList;
    @FindBy(css = "button[data-value='1'][title='Россия']")
    WebElement russia;
    @FindBy(xpath = "//input[@name='city']")
    WebElement city;
    @FindBy(css = "div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']")
    WebElement englishLevel;
    @FindBy(css = "button[data-value='4'][title='Средний (Intermediate)']")
    WebElement intermediate;
    @FindBy(xpath = "//span[contains(text(), 'Да')]")
    WebElement readyToRelocate;
    @FindBy(xpath = "//input[@title='Удаленно']")
    WebElement inputRemote;
    @FindBy(xpath = "//span[contains(text(), 'Удаленно')]")
    WebElement spanRemote;
    @FindBy(xpath = "//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']")
    WebElement connectionType;
    @FindBy(xpath = "//button[@data-value='telegram']")
    WebElement telegramButton;
    @FindBy(css = "#id_contact-0-value")
    WebElement connectionTypeField;
    @FindBy(id = "id_gender")
    WebElement genderList;
    @FindBy(xpath = "//option[@value='m']")
    WebElement male;
    @FindBy(id = "id_company")
    WebElement company;
    @FindBy(id = "id_work")
    WebElement position;
    @FindBy(xpath = "//button[@title='Сохранить и продолжить']")
    WebElement saveAndContinue;

    public MySelfPage(WebDriver driver) {
        super(driver);
    }

    public MySelfPage addMyselfData() {
        JavascriptExecutor jsc = (JavascriptExecutor)driver;

        // Имя
        fname.clear();
        fname.sendKeys(MyselfData.NAME.toString());
        // Имя (латиницей)
        fnameLatin.clear();
        fnameLatin.sendKeys(MyselfData.NAMELATIN.toString());
        // Фамилия
        lname.clear();
        lname.sendKeys(MyselfData.LNAME.toString());
        // Фамилия (латиницей)
        lnameLatin.clear();
        lnameLatin.sendKeys(MyselfData.LNAMELATIN.toString());
        // Имя (в блоге)
        blogName.clear();
        blogName.sendKeys(MyselfData.BLOGNAME.toString());
        // Дата рождения
        birthDate.clear();
        birthDate.sendKeys(MyselfData.BITHDATE.toString());
        // Страна
        countryList.click();
        russia.click();
        // Город
        jsc.executeScript("arguments[0].setAttribute('value', 1);", city);
        // Уровень знания английского языка
        englishLevel.click();
        intermediate.click();
        // Готовность к переезду
        readyToRelocate.click();
        // Формат работы
        if (!inputRemote.isSelected()) {
            spanRemote.click();
        }
        // Connection type
        connectionType.click();
        telegramButton.click();
        connectionTypeField.clear();
        connectionTypeField.sendKeys(MyselfData.TELEGA.toString());
        // gender
        genderList.click();
        male.click();
        // company
        company.clear();
        company.sendKeys(MyselfData.COMPANY.toString());
        // position
        position.clear();
        position.sendKeys(MyselfData.POSITION.toString());
        // 5. save and continue
        saveAndContinue.click();
        return this;
    }

}
