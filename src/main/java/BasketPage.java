import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By titleBasket = By.xpath("//h1[@class='basket-section__header basket-section__header--main active']");
    private By productInBasket = By.xpath("//div[@class='list-item__wrap']");

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean checkPageBasket() {
        try {
           return wait.until(ExpectedConditions.visibilityOfElementLocated(titleBasket)).isDisplayed();

        }catch (TimeoutException e) {
            System.out.println("Страница 'Корзина' не доступна");
            return false;
        }
    }

    public boolean checkProductBasket() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productInBasket)).isDisplayed();
        }catch (TimeoutException e) {
            System.out.println("Продукт не содержится в корзине");
            return false;
        }
    }
}
