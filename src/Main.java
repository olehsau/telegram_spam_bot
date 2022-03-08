import org.openqa.selenium.*;
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
    public static List<String> messagesList = new ArrayList<String>();
    ///////////////////////////////////////////////////////

    public static  Random rand = new Random();
    public static InstructionWindow instructionWindow = new InstructionWindow();
    public static StartWindow startWindow = new StartWindow();

    public static String randomMessage(){
        return messagesList.get(rand.nextInt(messagesList.size()));
    }

    public static String distortString(String text){
        String result = text;
        int n = rand.nextInt(4); // number of removed chars in string
        int pos;
        for(int i=0; i<n; i++){
            pos = rand.nextInt(text.length()-3)+1;
            result = result.substring(0, pos) + result.substring(pos + 1);
        }

        int numberOfExclamations = rand.nextInt(3);
        for(int i=0; i<numberOfExclamations; i++){
            //result = result+"!";
            result = result+".";
        }
        return result;
    }

    public static void main(String[] args) {
        // start window
        StartWindow.show();
    }


    public static void mainPart(){
        // main program
        System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("*opening telegram page");
        driver.get("https://web.telegram.org/z/");
        System.out.println("*please log in, open any chat, and open list of members.");
        WebDriverWait wait = new WebDriverWait(driver,55555);
        WebDriverWait wait5 = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));
        System.out.println("*members list detected");
        instructionWindow.close();
        WebElement membersListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));;
        List<WebElement> membersList = membersListElement.findElements(By.className("ListItem"));;
        WebElement temp;
        int scrolls=0; // number of scrolls needed to perform
        int a=0;
        do { // for each member
            for (int i = 1 + a; i < 29 + a; i++) {
                for (int s = 0; s < scrolls; s++) { // scrolling
                    membersListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));
                    membersList = membersListElement.findElements(By.className("ListItem"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", membersList.get(membersList.size() - 1));
                    //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", membersList.get(membersList.size() - 12));
                    //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", membersList.get(membersList.size() - 1));
                    System.out.println("scrolled");
                }
                System.out.println("opening chat with member");
                try {
                    membersListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));
                    membersList = membersListElement.findElements(By.className("ListItem"));
                    try {
                        membersList.get(i).click(); // possible exception here
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("IndexOutOfBoundsException was caught. Scrolling to first and moving to next member (continue)");
                        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", membersList.get(membersList.size() - 5));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", membersList.get(1));
                        System.out.println("scrolled to beginning and continuing");
                        //membersListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("members-list")));
                        //membersList = membersListElement.findElements(By.className("ListItem"));
                        //membersList.get(i).click();
                        continue;
                    }
                    catch(ElementNotInteractableException e){continue;}
                    System.out.println("waiting for phone icon");
                    try {
                        wait5.until(ExpectedConditions.presenceOfElementLocated(By.className("icon-phone")));
                    }
                    catch(TimeoutException e){
                        e.printStackTrace();
                        System.out.println("TimeoutException. Phone icon was not found");
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("back-button"))).click();
                        continue;
                    }
                    temp = driver.findElements(By.id("editable-message-text")).get(driver.findElements(By.id("editable-message-text")).size() - 1);
                    temp.sendKeys(distortString(randomMessage()));
                    //temp.sendKeys(randomMessage());
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("send"))).click();
                    //driver.findElements(By.className("icon-send")).get(driver.findElements(By.className("icon-send")).size()-1).click();
                    System.out.println("sent!");
                    Thread.sleep(3000);
                } catch (StaleElementReferenceException e) {
                    System.out.println("StaleElementReferenceException was caught, moving to the next member");
                } catch (ElementClickInterceptedException e) {
                    System.out.println("ElementClickInterceptedException was caught, moving to the next member");
                } catch (InterruptedException e) {
                    System.out.println(":'( error with Thread.sleep():");
                    e.printStackTrace();
                }
                try {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("back-button"))).click();
                }
                catch(ElementNotInteractableException e){
                    System.out.println("ElementNotInteractableException");
                }
                i++;
            }
            a=30; // because after very first scroll there are not 30 but 60 members on the list and new ones are from 31 position
            scrolls++;
        }
        while(true);
    }
}
