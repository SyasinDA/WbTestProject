import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;

public class FirstTest {
    private static final String url = "https://www.wildberries.ru/catalog/264220770/detail.aspx";
    private WebDriver driver;
    private ProductPage productPage;
    private BasketPage basketPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testAddProduct() throws InterruptedException {
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
        //Step 1 Откроем страницу
        driver.get(url);
        //Проверим что страница отображается
        assertTrue(productPage.pageIsOpen(),"Страница товара не открыта");
        //Нажимаем на кнопку добавить товар
        productPage.addProductBasket();
        assertTrue(productPage.isButtonPressed(),"Кнопка не была нажата");
        //Нажимаем на кнопку перейти в корзину
        productPage.clickGoToBasket();
        //Проверяем что открыта страница Корзина
        assertTrue(basketPage.checkPageBasket(),"Страница не доступна");
        //Проверяем что в корзине содрежится добавленный продукт
        assertTrue(basketPage.checkProductBasket());
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
}
