package tests;

import pages.MainPage;
import pages.MySelfPage;
import wdFactory.BrowsersName;
import data.MyselfData;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static data.MyselfData.NAMELATIN;

public class TestAboutMyself extends BaseTest {

    @Test
    public void checkAboutMyself() {

        initDriver();
        System.out.println(BrowsersName.CHROME.toString());

        MainPage addMyselfData = new MainPage(driver);
        addMyselfData.open(cfg.urlOtus());
        addMyselfData
                .auth()
                .enterToSelfArea()
                .enterToAboutMyself()
                .addMyselfData();

        driver.close();

        MainPage checkMySelf = new MainPage(driver);
        checkMySelf.open(cfg.urlOtus());
        checkMySelf
                .auth()
                .enterToSelfArea()
                .enterToAboutMyself();

        // check entered data
        Assert.assertEquals("fname field error", MyselfData.NAME.toString(), driver.findElement(By.cssSelector("input[name='fname']")).getAttribute("value"));
        Assert.assertEquals("lname field error", MyselfData.LNAME.toString(), driver.findElement(By.cssSelector("input[name='lname']")).getAttribute("value"));
        Assert.assertEquals("lname_latin field error", MyselfData.LNAMELATIN.toString(), driver.findElement(By.cssSelector("input[name='lname_latin']")).getAttribute("value"));
        Assert.assertEquals("fname_latin field error", NAMELATIN.toString(), driver.findElement(By.cssSelector("input[name='fname_latin']")).getAttribute("value"));
        Assert.assertEquals("blog_name field error", MyselfData.BLOGNAME.toString(), driver.findElement(By.cssSelector("input[name='blog_name']")).getAttribute("value"));
        Assert.assertEquals("date_of_birth field error", MyselfData.BITHDATE.toString(), driver.findElement(By.cssSelector("input[name='date_of_birth']")).getAttribute("value"));
        Assert.assertEquals("Россия field error", "Россия", driver.findElement(By.xpath("//div[contains(text(), 'Россия')]")).getText().trim());
        Assert.assertEquals("Абакан field error", "Абакан", driver.findElement(By.xpath("//div[contains(text(), 'Абакан')]")).getText().trim());
        Assert.assertEquals("Intermediate field error", "Средний (Intermediate)", driver.findElement(By.xpath("//div[contains(text(), 'Средний (Intermediate)')]")).getText().trim());
        Assert.assertEquals("relocate field error", true, driver.findElement(By.id("id_ready_to_relocate_1")).isSelected());
        Assert.assertEquals("Удаленно field error", true, driver.findElement(By.xpath("//input[@title='Удаленно']")).isSelected());
        Assert.assertEquals("Полный день field error", true, driver.findElement(By.xpath("//input[@title='Полный день']")).isSelected());
        Assert.assertEquals("Гибкий график field error", false, driver.findElement(By.xpath("//input[@title='Гибкий график']")).isSelected());
        Assert.assertEquals("Тelegram field error", "Тelegram", driver.findElement(By.xpath("//div[//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation'] and contains(text(),  'Тelegram')]")).getText().trim());
        Assert.assertEquals("@testtelega field error", MyselfData.TELEGA.toString(), driver.findElement(By.xpath("//input[@name='contact-0-value']")).getAttribute("value"));
        Assert.assertEquals("sex field error", true, driver.findElement(By.xpath("//option[@value='m']")).isSelected());
        Assert.assertEquals("id_company field error", MyselfData.COMPANY.toString(), driver.findElement(By.id("id_company")).getAttribute("value"));
        Assert.assertEquals("id_work field error", MyselfData.POSITION.toString(), driver.findElement(By.id("id_work")).getAttribute("value"));
    }

}
