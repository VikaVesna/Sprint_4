package ru.yandex.praktikum.scooter;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderPageTwoAboutRentTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final int metroId;
    private final String phone;
    private final String scooterColor;
    private final String comment;
    private final boolean isOrdered;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    public OrderPageTwoAboutRentTest(String name, String surname, String address, int metroId, String phone, String scooterColor, String comment, boolean isOrdered) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroId = metroId;
        this.phone = phone;
        this.scooterColor = scooterColor;
        this.comment = comment;
        this.isOrdered = isOrdered;
    }


    @Before
    public void openMainPage() throws Exception {
        driver = driverRule.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickHeaderOrderButton();
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Иван", "Иванов", "Иваново, 12", 1, "89264563712", "grey", "в дверь не звоните", true},
                {"Иван", "Иванов", "Иваново, 12", 2, "89264563712", "black", "", true},
        };
    }

    @Test
    public void orderFullTest() {
        WebDriver driver = driverRule.getDriver();
        var orderPageWhoIsTheScooterFor  = new OrderPageOneWhoIsTheScooterFor(driver);
        var orderPageAboutRent = new OrderPageTwoAboutRent(driver);

        orderPageWhoIsTheScooterFor.fillWhoIsTheScooterFor(name, surname, address, metroId, phone);
        orderPageAboutRent.fillAboutRent (scooterColor, comment);
        orderPageAboutRent.checkInfoOrderPageTwo (isOrdered);
    }
}