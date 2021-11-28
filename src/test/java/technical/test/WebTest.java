package technical.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

    
public class WebTest {
        

    public static WebDriver driver;


        @BeforeTest
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            //to launch the chrome browser window
            driver = new ChromeDriver();
    
            //maximize the browser window
            driver.manage().window().maximize();
        }
    

        @AfterTest
        public void tearDown(){
            driver.quit();
        }


        @Test
        public void register(){

            driver.get("https://accounts.kitabisa.com/register");
            System.out.println(driver.getTitle());
    
            WebElement phoneNumberorEmail = driver.findElement(By.xpath("//*[@data-testid='register-input-email']"));
            phoneNumberorEmail.sendKeys("javamailchecking@gmail.com");
    
            WebElement name = driver.findElement(By.xpath("//*[@data-testid='register-input-name']"));
            name.sendKeys("test user");

            WebElement daftar = driver.findElement(By.xpath("//button[text()='Daftar']"));
            daftar.click();

            jReverseString manggil = new jReverseString();
            String OTP = manggil.read();
            System.out.println("OTP yang didapat " + OTP);

            driver.findElement(By.xpath("//*[text()='Pastikan kepemilikan akun ini']")).isDisplayed();


        }

    }