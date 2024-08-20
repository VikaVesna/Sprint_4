package ru.yandex.praktikum.scooter;


import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;


public class MainPage {
    private WebDriver driver;

    private By headerOrderButton = By.cssSelector(".Header_Nav__AGCXC .Button_Button__ra12g");
    private By lowerOrderButton = By.cssSelector(".Home_FinishButton__1_cWm > button");
    private By acceptCookieButton = By.id("rcc-confirm-button");
    private By faqQuestion = By.className("accordion__button");
    private By faqAnswer = By.className("accordion__panel");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }


    // Открыть сайт
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }


    // Принять куки
    public void acceptCookie() {
        driver.findElement(acceptCookieButton).click();
    }


    // Нажать на вопрос
    public void clickOnQuestion(int index) {
        driver.findElements(faqQuestion).get(index).click();
    }

    public void waitForAnswer(int index){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[contains(@class, 'accordion__panel')])[" + (index + 1) + "]")));

        assertTrue(driver.findElements(faqAnswer).get(index).isDisplayed());
    }


    // Получить текст вопроса
    public String getQuestionText(int index){
        return driver.findElements(faqQuestion).get(index).getText();
    }


    // Сравнить текст вопроса с ожидаемым
    public void checkQuestionText(String questionText, int index) {
        String actualQuestionText = getQuestionText(index);
        MatcherAssert.assertThat(actualQuestionText, is(questionText));
    }


    // Получить текст ответа
    public String getAnswerText(int index){
        return driver.findElements(faqAnswer).get(index).getText();
    }


    // Сравнить текст ответа с ожидаемым
    public void checkAnswerText(String answerText, int index) {
        waitForAnswer(index);
        String actualAnswerText = getAnswerText(index);
        MatcherAssert.assertThat(actualAnswerText, is(answerText));
    }

    // Нажать на кнопку Заказать в шапке
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }


    // Видимость кнопки Заказать внизу страницы
    public void waitLowerOrderButton() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Home_FinishButton__1_cWm > button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    // Нажать на кнопку Заказать внизу страницы
    public void clickLowerOrderButton() {
        driver.findElement(lowerOrderButton).click();
    }
}
