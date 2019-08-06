package in.amazon.resources;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility extends WebDriverWrapper{

    static Actions action = new Actions(driver);
    static WebDriverWait wait = new WebDriverWait(driver, 20);

    public static void signIn(String userName, String password){
        driver.findElement(By.cssSelector("a[id*= 'accountList']")).click();
        driver.findElement(By.cssSelector("[type= 'email']")).sendKeys(userName);
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys(password);
        driver.findElement(By.id("signInSubmit")).click();
    }

    public static void searchProduct(String productName, String rom, String color){
        try{
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys(productName);
            driver.findElement(By.id("twotabsearchtextbox")).submit();
            Thread.sleep(1500);
            int count = driver.findElements(By.cssSelector("[class*= 'a-size-medium']")).size();
            System.out.println(count);
            for(int i = 0; i < count; i++){
                String name = driver.findElements(By.cssSelector("[class*= 'a-size-medium']")).get(i).getText();
                if(name.contains(productName) && name.contains(rom) && name.contains(color)){
                    System.out.println(name);
                    driver.findElements(By.cssSelector("[class*= 'a-size-medium']")).get(i).click();
                    break;
                }
            }
            String currentWindow = driver.getWindowHandle();
            System.out.println(currentWindow);
            for(String handle : driver.getWindowHandles()){
                driver.switchTo().window(handle);
                System.out.println(handle);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void buyProduct(){
        try{
            driver.findElement(By.id("add-to-cart-button")).click();
            driver.findElement(By.id("nav-cart")).click();
            driver.findElement((By.cssSelector("[class*='gift-option'] input"))).click();
            Thread.sleep(1500);
            Select select = new Select(driver.findElement(By.cssSelector("select[name='quantity']")));
            select.selectByVisibleText("1");
            driver.findElement(By.cssSelector("[name='proceedToCheckout']")).click();
            driver.findElement(By.id("enterAddressFullName")).sendKeys("AGROSTAR");
            driver.findElement(By.id("enterAddressPhoneNumber")).sendKeys("9000000000");
            driver.findElement(By.id("enterAddressPostalCode")).sendKeys("411014");
            Thread.sleep(1500);
            driver.findElement(By.id("enterAddressAddressLine1")).sendKeys("S.No: 46/1, A1 Building, First Floor");
            driver.findElement(By.id("enterAddressAddressLine2")).sendKeys("E-Space IT Park, 101");
            Thread.sleep(1500);
            driver.findElement(By.cssSelector(".enter-address-form:nth-child(1) [type='submit']")).click();
            driver.findElement(By.xpath("(//*[contains(@value,'Save gift options')])[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("(//*[@value= 'Continue'])[1]")).click();
            driver.findElement(By.xpath("//*[contains(@name,'bankSelection')]/following-sibling::span")).click();
            driver.findElement(By.xpath("//a[text()='ICICI Bank']")).click();
            Thread.sleep(1000);
            driver.findElement((By.xpath("(//*[contains(@name,'SetPaymentPlanSelectContinueEvent')])[1]"))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Amazon.in')]")).click();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    static public void signOut() throws InterruptedException {
        try{
            WebElement element = driver.findElement(By.xpath("//*[text()= 'Sign Out']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1500);
            action.moveToElement(driver.findElement(By.cssSelector("a[id*= 'accountList']")))
                    .moveToElement(element).perform();
            action.click(element).perform();
        }
        catch (Exception e){
            e.printStackTrace();
            WebElement element = driver.findElement(By.xpath("//*[text()= 'Sign Out']"));
            Thread.sleep(2000);
            action.moveToElement(driver.findElement(By.cssSelector("a[id*= 'accountList']")))
                    .moveToElement(element).perform();
            Thread.sleep(2000);
            action.click(element).perform();
        }
    }
}
