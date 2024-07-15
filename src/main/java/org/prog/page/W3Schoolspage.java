package org.prog.page;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class W3Schoolspage extends AbstractPage {

    private final static String URL = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width";

    public W3Schoolspage(WebDriver driver) {
        super(driver, URL);
    }

    public void iframeMethod() {
        //>> default content
        WebElement element = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(element);
        // we're in the iframe "iframeResult"
        WebElement innerFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(innerFrame);
        //we're in the iframe inside iframe
        System.out.println(driver.findElement(By.tagName("h1")).getText());
        driver.switchTo().defaultContent();
        //we're back to default content (line 21)
    }

    public void acceptCookies() {
        List<WebElement> cookieAccept = new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("accept-choices")));
        if (!cookieAccept.isEmpty()) {
            cookieAccept.get(0).click();
        }
    }
}
