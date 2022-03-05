import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {
    /////////////////// list of messages //////////////////
    public static List<String> messagesList = Arrays.asList("a","b","c","d","e");
    ///////////////////////////////////////////////////////
    public static  Random rand = new Random();
    public static String randomMessage(){
        return messagesList.get(rand.nextInt(messagesList.size()));
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("*opening telegram page");
        driver.get("https://web.telegram.org/z/");
        System.out.println("*please log in, open any chat, and open list of members.");
        WebDriverWait wait = new WebDriverWait(driver,55555);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));
        System.out.println("*members list detected");
        WebElement membersListElement;
        List<WebElement> membersList;
        WebElement temp;
        int i=1;
        do{
            membersListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));
            membersList = membersListElement.findElements(By.className("ListItem"));
            membersList.remove(0);
            System.out.println("opening chat with member");
            try {
                membersList.get(i).click(); // possible exception here
                System.out.println("waiting for phone icon");
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("icon-phone")));
                temp = driver.findElements(By.id("editable-message-text")).get(driver.findElements(By.id("editable-message-text")).size() - 1);
                temp.sendKeys(randomMessage());
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("send"))).click();
                //driver.findElements(By.className("icon-send")).get(driver.findElements(By.className("icon-send")).size()-1).click();
                System.out.println("sent!");
                driver.findElement(By.className("back-button")).click();
            }
            catch(StaleElementReferenceException e){
                System.out.println("exception was caught, moving to the next member");
            }
            i++;
        }
        while(i<membersList.size());
    }
}
