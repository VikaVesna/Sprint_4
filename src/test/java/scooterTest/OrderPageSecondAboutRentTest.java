package scooterTest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderPageOneWhoIsTheScooterFor;
import pageObjects.OrderPageSecondAboutRent;

@RunWith(Parameterized.class)
public class OrderPageSecondAboutRentTest {

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

    public OrderPageSecondAboutRentTest(String name, String surname, String address, int metroId, String phone, String scooterColor, String comment, boolean isOrdered) {
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
        var orderPageAboutRent = new OrderPageSecondAboutRent(driver);

        //заполняем страницу Для кого самокат
        orderPageWhoIsTheScooterFor.fillWhoIsTheScooterFor(name, surname, address, metroId, phone);

        //заполняем страницу Про аренду
        orderPageAboutRent.clickOnOrderDateField();
        orderPageAboutRent.chooseDate();
        orderPageAboutRent.clickOnRentalPeriodField();
        orderPageAboutRent.chooseRentalPeriod();
        orderPageAboutRent.chooseColor(scooterColor);
        orderPageAboutRent.writeComment(comment);
        orderPageAboutRent.clickFinalOrderButton();

        By finalQuestionToOrder = By.cssSelector(".Order_Buttons__1xGrp:nth-child(2) > button:nth-child(2)");
        By buttonCheckStatus = By.cssSelector(".Order_NextButton__1_rCA > button:nth-child(1)");
        if (isOrdered) {
            // Если данные валидны, форма переходит на следующий шаг
            driver.findElement(finalQuestionToOrder).click();
            Assert.assertTrue("Происходит переход на страницу Хотите оформить заказ?",
                    driver.findElements(buttonCheckStatus).size() > 0);

        } else {
            //  Если данные валидны, форма не переходит на следующий шаг
            Assert.assertFalse("Не происходит переход на страницу Хотите оформить заказ?",
                    driver.findElements(buttonCheckStatus).size() == 0);
        }

    }
}