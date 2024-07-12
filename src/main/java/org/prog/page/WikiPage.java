package org.prog.page;

import org.openqa.selenium.WebDriver;

public class WikiPage extends AbstractPage {

    private final static String URL = "https://www.wikipedia.org/";

    public WikiPage(WebDriver driver) {
        super(driver, URL);
    }
}
