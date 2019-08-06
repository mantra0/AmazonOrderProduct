package in.amazon.execution;

import in.amazon.resources.Utility;
import in.amazon.resources.WebDriverWrapper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestExecution extends WebDriverWrapper {


    @BeforeClass()
    @Parameters("browser")
    void launchBrowser(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\in\\amazon\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://www.amazon.in/");
        }
        else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\in\\amazon\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://www.amazon.in/");
        }
    }

    @Test
    void run() throws InterruptedException {
        Utility.signIn("USER EMAIL", "PASSWORD");  //PROVIDE USER EMAIL AND PASSWORD HERE
        Utility.searchProduct("Apple iPhone Xs", "256", "Space Grey");   //Can Dynamically change the product to be searched
        Utility.buyProduct();
        Utility.signOut();
    }
}
