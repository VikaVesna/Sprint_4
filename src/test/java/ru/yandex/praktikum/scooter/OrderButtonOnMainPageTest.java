package ru.yandex.praktikum.scooter;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class OrderButtonOnMainPageTest {


    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }


    @Test
    public void clickOnHeaderOrderButton() {
        mainPage.clickHeaderOrderButton();
    }


    @Test
    public void clickOnLowerOrderButton() {
        mainPage.waitLowerOrderButton();
        mainPage.clickLowerOrderButton();
    }
}