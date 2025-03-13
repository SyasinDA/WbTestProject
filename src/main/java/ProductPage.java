import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    //локатор для добавления товара в корзину
    private By addToBasket = By.xpath("//div[@class='product-page__order-buttons']//button[@aria-label='Добавить в корзину']");
    //локатор для проверки что страница карточка товара открылась
    private By titleProduct = By.xpath("//h1[contains(text(),'Щелкунчик по балету Чайковского. Сказка для детей')]");
    //локатор для перехода в корзину
    private By moveToBasket = By.xpath("//div[@class='product-page__order-buttons']//a[@class='order__button btn-base j-go-to-basket']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean pageIsOpen() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(titleProduct)).isDisplayed();
        } catch (TimeoutException e ) {
            System.out.println("Страница продукта не открылась");
            return false;
        }
    }

     public void addProductBasket() {
         try {
             wait.until(ExpectedConditions.elementToBeClickable(addToBasket)).click();
         } catch (TimeoutException e) {
             System.out.println("Кнопка добавления продукта не отображается");
         }
     }

     public boolean isButtonPressed() {
         try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(addToBasket));
         } catch (TimeoutException e) {
             System.out.println("Кнопка 'Добавить в коризну' не нажата ");
             return false;
         }
     }

     public void clickGoToBasket(){
        try {
        wait.until(ExpectedConditions.elementToBeClickable(moveToBasket)).click();
     } catch (TimeoutException e ) {
            System.out.println("Кнопка 'Перейти в корзину' не нажата ");
        }
    }
}