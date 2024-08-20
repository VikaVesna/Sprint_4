package ru.yandex.praktikum.scooter;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderPageOneWhoIsTheScooterForTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final int metroId;
    private final String phone;
    private final boolean isCorrect;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    public OrderPageOneWhoIsTheScooterForTest(String name, String surname, String address, int metroId, String phone, boolean isCorrect) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroId = metroId;
        this.phone = phone;
        this.isCorrect = isCorrect;
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
                //Позитивные проверки
                {"Иван", "Иванов", "Иваново, 12", 1, "89264563712", true},
                {"Семен", "Смирнов", "Москва, Вернадского, 11", 2, "89467892732", true},
                {"Кирилл", "Кириллов", "Балашиха, Юбилейная, 23", 3, "+79467892732", true},
                //Негативные проверки
                {"Ivan", "Иванов", "Иваново, 12", 4, "89264563712", false},
                {"Иван", "Ivanov123", "Иваново, 12", 5, "89264563712", false},
                {"Иван", "Иванов", "Ulica 14-28-34", 6, "89264563712", false},
                {"Иван", "Иванов", "Иваново, 12", 7, "текст", false},
        };
    }

    @Test
    public void orderTest() {
        WebDriver driver = driverRule.getDriver();
        var orderFirstPage = new OrderPageOneWhoIsTheScooterFor(driver);

        orderFirstPage.fillWhoIsTheScooterFor(name, surname, address, metroId, phone);
        orderFirstPage.checkInfoOrderPageOne(isCorrect);
    }
}