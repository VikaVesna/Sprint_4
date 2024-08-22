package ru.yandex.praktikum.scooter;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageOneWhoIsTheScooterFor {
    private WebDriver driver;

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By furtherButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private By aboutRentPage = By.xpath(".//div[@class='Order_Header__BZXOb']");

    public OrderPageOneWhoIsTheScooterFor(WebDriver driver) {
        this.driver = driver;
    }


    // Заполнить поле имя
    public void fillNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    // Заполнить поле фамилия
    public void fillSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    // Заполнить поле адрес
    public void fillAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // Нажать на поле метро
    public void clickMetroStationField() {
        driver.findElement(metroStationField).click();

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("select-search__select")));
    }

    // Выбор станции метро
    public void fillMetroStation(int metroId) {
        driver.findElement(By.cssSelector("li[data-index=\"" + metroId + "\"]")).click();
    }

    // Заполнить поле телефон
    public void fillPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    // Нажать на кнопку Далее
    public void clickFurtherButton() {
        driver.findElement(furtherButton).click();
    }

    //     Метод заполнения всей формы Для кого самокат
    public void fillWhoIsTheScooterFor(String name, String surname, String address, int metroId, String phone) {
        fillNameField(name);
        fillSurnameField(surname);
        fillAddressField(address);
        clickMetroStationField();
        fillMetroStation(metroId);
        fillPhoneField(phone);
        clickFurtherButton();
    }

    // Проверка перехода на следующую страницу при валидных данных
    public void checkInfoOrderPageOne (boolean isCorrect) {
        if (isCorrect) {
            // Если данные валидны, форма переходит на следующий шаг
            Assert.assertTrue("Происходит переход на страницу Про аренду",
                    driver.findElements(aboutRentPage).size() > 0);

        } else {
            //  Если данные не валидны, форма не переходит на следующий шаг
            Assert.assertFalse("Не происходит переход на страницу Про аренду",
                    driver.findElements(aboutRentPage).size() == 0);
        }
    }
}