package technical.test;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class WebTest {
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @Test
    public void register(){
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        driver.get().get("https://accounts.kitabisa.com/register");
    }

}
