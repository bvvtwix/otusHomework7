import config.ConfigServer;
import data.MyselfData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.MyselfData.NAMELATIN;

public class TestAboutMyself {

    private Logger logger = LogManager.getLogger(TestAboutMyself.class);
    private ConfigServer cfg = ConfigFactory.create(ConfigServer.class);
    private WebDriver driver;

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void initDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    private void auth(){
        driver.findElement(By.cssSelector("button[data-modal-id=\"new-log-reg\"]")).click();
        driver.findElement(By.cssSelector("input.new-input[name='email'][type='text']")).sendKeys(cfg.email());
        driver.findElement(By.cssSelector("input.new-input[name='password'][type='password']")).sendKeys(cfg.pass());
        driver.findElement(By.cssSelector("button[type='submit'][class='new-button new-button_full new-button_blue new-button_md']")).click();
        logger.info("Auth completed");
    }

    private void enterToAboutMyself(){
        driver.findElement(By.cssSelector("p[class='header2-menu__item-text header2-menu__item-text__username']")).click();
        driver.findElement(By.cssSelector("a[title='Личный кабинет']")).click();
        driver.findElement(By.xpath("//div[@class='nav nav_mobile-fix no-print js-overflow-scroll']//a[@title='О себе']")).click();
        logger.info("about myself entered");
    }

    @Test
    public void checkAboutMyself() {

        initDriver();
        // open otus.ru
        driver.get(cfg.urlOtus());
        // Авторизоваться
        auth();
        // check auth
        Assert.assertEquals("Test", driver.findElement(By.xpath("//p[@class='header2-menu__item-text header2-menu__item-text__username']")).getText());
        // enter to about my myself
        enterToAboutMyself();
        // add data
        addMyselfData();
        // close
        driver.quit();
        // open new browser
        initDriver();
        // open otus.ru
        driver.get(cfg.urlOtus());
        // Авторизоваться
        auth();
        // check auth
        Assert.assertEquals("Test", driver.findElement(By.xpath("//p[@class='header2-menu__item-text header2-menu__item-text__username']")).getText());
        // enter to about my myself
        enterToAboutMyself();
        // check entered data
        Assert.assertEquals(MyselfData.NAME.toString(), driver.findElement(By.cssSelector("input[name='fname']")).getAttribute("value"));
        logger.info("name success checked");
        Assert.assertEquals(MyselfData.LNAME.toString(), driver.findElement(By.cssSelector("input[name='lname']")).getAttribute("value"));
        logger.info("lname success checked");
        Assert.assertEquals(MyselfData.LNAMELATIN.toString(), driver.findElement(By.cssSelector("input[name='lname_latin']")).getAttribute("value"));
        logger.info("lname_latin success checked");
        Assert.assertEquals(NAMELATIN.toString(), driver.findElement(By.cssSelector("input[name='fname_latin']")).getAttribute("value"));
        logger.info("fname_latin success checked");
        Assert.assertEquals(MyselfData.BLOGNAME.toString(), driver.findElement(By.cssSelector("input[name='blog_name']")).getAttribute("value"));
        logger.info("blog_name success checked");
        Assert.assertEquals(MyselfData.BITHDATE.toString(), driver.findElement(By.cssSelector("input[name='date_of_birth']")).getAttribute("value"));
        logger.info("date_of_birth success checked");
        Assert.assertEquals("Россия", driver.findElement(By.xpath("//div[contains(text(), 'Россия')]")).getText().trim());
        logger.info("Россия success checked");
        Assert.assertEquals("Абакан", driver.findElement(By.xpath("//div[contains(text(), 'Абакан')]")).getText().trim());
        logger.info("Абакан success checked");
        Assert.assertEquals("Средний (Intermediate)", driver.findElement(By.xpath("//div[contains(text(), 'Средний (Intermediate)')]")).getText().trim());
        logger.info("Средний (Intermediate) success checked");
        Assert.assertEquals(true, driver.findElement(By.id("id_ready_to_relocate_1")).isSelected());
        logger.info("is relocate success checked");
        Assert.assertEquals(true, driver.findElement(By.xpath("//input[@title='Удаленно']")).isSelected());
        logger.info("Удаленно success checked");
        Assert.assertEquals(true, driver.findElement(By.xpath("//input[@title='Полный день']")).isSelected());
        logger.info("Полный день success checked");
        Assert.assertEquals(false, driver.findElement(By.xpath("//input[@title='Гибкий график']")).isSelected());
        logger.info("Гибкий график success checked");
        Assert.assertEquals("Тelegram", driver.findElement(By.xpath("//div[//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation'] and contains(text(),  'Тelegram')]")).getText().trim());
        logger.info("Тelegram success checked");
        Assert.assertEquals(MyselfData.TELEGA.toString(), driver.findElement(By.xpath("//input[@name='contact-0-value']")).getAttribute("value"));
        logger.info("@testtelega success checked");
        Assert.assertEquals(true, driver.findElement(By.xpath("//option[@value='m']")).isSelected());
        logger.info("sex success checked");
        Assert.assertEquals(MyselfData.COMPANY.toString(), driver.findElement(By.id("id_company")).getAttribute("value"));
        logger.info("id_company success checked");
        Assert.assertEquals(MyselfData.POSITION.toString(), driver.findElement(By.id("id_work")).getAttribute("value"));
        logger.info("id_work success checked");
        logger.info("all field success checked");

    }

    private void addMyselfData() {
        JavascriptExecutor jsc = (JavascriptExecutor)driver;

        // Имя
        WebElement fname = driver.findElement(By.cssSelector("input[name='fname']"));
        fname.clear();
        fname.sendKeys(MyselfData.NAME.toString());
        logger.info("fname added");
        // Имя (латиницей)
        WebElement fnameLatin = driver.findElement(By.cssSelector("input[name='fname_latin']"));
        fnameLatin.clear();
        fnameLatin.sendKeys(MyselfData.NAMELATIN.toString());
        logger.info("fnameLatin added");
        // Фамилия
        WebElement lname = driver.findElement(By.cssSelector("input[name='lname']"));
        lname.clear();
        lname.sendKeys(MyselfData.LNAME.toString());
        logger.info("lname added");
        // Фамилия (латиницей)
        WebElement lnameLatin = driver.findElement(By.cssSelector("input[name='lname_latin']"));
        lnameLatin.clear();
        lnameLatin.sendKeys(MyselfData.LNAMELATIN.toString());
        logger.info("lnameLatin added");
        // Имя (в блоге)
        WebElement blogName = driver.findElement(By.cssSelector("input[name='blog_name']"));
        blogName.clear();
        blogName.sendKeys(MyselfData.BLOGNAME.toString());
        logger.info("blogName added");
        // Дата рождения
        WebElement birthDate = driver.findElement(By.cssSelector("input[name='date_of_birth']"));
        birthDate.clear();
        birthDate.sendKeys(MyselfData.BITHDATE.toString());
        logger.info("birthDate added");
        // Страна
        driver.findElement(By.cssSelector("div[data-slave-selector='.js-lk-cv-dependent-slave-city']")).click();
        driver.findElement(By.cssSelector("button[data-value='1'][title='Россия']")).click();
        logger.info("russia added");
        // Город
        jsc.executeScript("arguments[0].setAttribute('value', 1);", driver.findElement(By.xpath("//input[@name='city']")));
        logger.info("city added");
        // Уровень знания английского языка
        driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']")).click();
        driver.findElement(By.cssSelector("button[data-value='4'][title='Средний (Intermediate)']")).click();
        logger.info("intermediate added");
        // Готовность к переезду
        driver.findElement(By.xpath("//span[contains(text(), 'Да')]")).click();
        logger.info("readyToRelocate added");
        // Формат работы
        if (!driver.findElement(By.xpath("//input[@title='Удаленно']")).isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'Удаленно')]")).click();
            logger.info("workFormatRemote added");
        } else {
            logger.info("workFormatRemote already added");
        }
        // Connection type
        driver.findElement(By.xpath("//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']")).click();
        driver.findElement(By.xpath("//button[@data-value='telegram']")).click();
        WebElement telega = driver.findElement(By.cssSelector("#id_contact-0-value"));
        telega.clear();
        telega.sendKeys(MyselfData.TELEGA.toString());
        logger.info("telega added");
        // gender
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.xpath("//option[@value='m']")).click();
        logger.info("gender added");
        // company
        WebElement company = driver.findElement(By.id("id_company"));
        company.clear();
        company.sendKeys(MyselfData.COMPANY.toString());
        logger.info("company added");
        // position
        WebElement position = driver.findElement(By.id("id_work"));
        position.clear();
        position.sendKeys(MyselfData.POSITION.toString());
        logger.info("position added");
        // 5. save and continue
        driver.findElement(By.xpath("//button[@title='Сохранить и продолжить']")).click();
        logger.info("save and continue");
        logger.info("all information added");

    }

}
