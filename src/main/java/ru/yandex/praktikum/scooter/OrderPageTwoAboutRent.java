package ru.yandex.praktikum.scooter;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageTwoAboutRent {
    private WebDriver driver;

    private By dateField = By.className("react-datepicker__input-container");
    private By dateForOrder = By.cssSelector(".react-datepicker__day--021");
    private By rentalPeriodField = By.cssSelector(".Dropdown-placeholder");
    private By rentalPeriodForOrder = By.cssSelector(".Dropdown-option:nth-child(1)");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By finalOrderButton = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By finalQuestionToOrderButton = By.cssSelector(".Order_Buttons__1xGrp:nth-child(2) > button:nth-child(2)");
    private By checkStatusButton = By.cssSelector(".Order_NextButton__1_rCA > button:nth-child(1)");

    public OrderPageTwoAboutRent(WebDriver driver){
        this.driver = driver;
    }

    // Кликаем на поле с выбором даты
    public void clickOnOrderDateField() {
        driver.findElement(dateField).click();
    }

    // Выбираем дату в календаре
    public void chooseDate() {
        driver.findElement(dateForOrder).click();
    }

    // Кликаем на поле срок аренды
    public void clickOnRentalPeriodField() {
        driver.findElement(rentalPeriodField).click();
    }

    // Выбираем дату в календаре
    public void chooseRentalPeriod() {
        driver.findElement(rentalPeriodForOrder).click();
    }


    // Выбраем цвет самоката
    public void chooseColor(String scooterColor) {
        By colorOption = By.xpath(String.format(".//input[@id='%s']", scooterColor));
        driver.findElement(colorOption).click();
    }


    // Пишем комментарий для курьера
    public void writeComment (String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    // Нажимаем кнопку заказать
    public void clickFinalOrderButton() {
        driver.findElement(finalOrderButton).click();
    }


    // Метод заполнения всей формы Про аренду
    public void fillAboutRent (String scooterColor, String comment) {
        clickOnOrderDateField();
        chooseDate();
        clickOnRentalPeriodField();
        chooseRentalPeriod();
        chooseColor(scooterColor);
        writeComment(comment);
        clickFinalOrderButton();
    }

    // Проверка перехода на следующую страницу при валидных данных
    public void checkInfoOrderPageTwo (boolean isOrdered) {
        if (isOrdered) {
            // Если данные валидны, форма переходит на следующий шаг
            driver.findElement(finalQuestionToOrderButton).click();
            Assert.assertTrue("Происходит переход на страницу Хотите оформить заказ?",
                    driver.findElements(checkStatusButton).size() > 0);

        } else {
            //  Если данные валидны, форма не переходит на следующий шаг
            Assert.assertFalse("Не происходит переход на страницу Хотите оформить заказ?",
                    driver.findElements(checkStatusButton).size() == 0);
        }
    }
}