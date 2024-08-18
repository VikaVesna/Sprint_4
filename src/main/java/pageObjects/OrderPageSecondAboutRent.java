package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageSecondAboutRent {
    private WebDriver driver;

    private By dateField = By.className("react-datepicker__input-container");
    private By dateForOrder = By.cssSelector(".react-datepicker__day--021");
    private By rentalPeriodField = By.cssSelector(".Dropdown-placeholder");
    private By rentalPeriodForOrder = By.cssSelector(".Dropdown-option:nth-child(1)");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By finalOrderButton = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPageSecondAboutRent(WebDriver driver){
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


    //     Выбраем цвет самоката
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

}