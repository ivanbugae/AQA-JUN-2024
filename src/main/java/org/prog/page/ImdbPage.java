package org.prog.page;

import org.openqa.selenium.WebDriver;

public class ImdbPage extends AbstractPage {
    private final static String URL = "https://imdb.com/";

    public ImdbPage(WebDriver driver) {
        super(driver, URL);
    }
}
