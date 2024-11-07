package ru.stqa.mantis.manager;

import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    private static WebDriver driver;
    public RestApiHelper rest;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpsessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private JamesApiHelper jamesApiHelper;
    private RestApiHelper restApiHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
        }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1280, 712));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpsessionHelper == null) {
            httpsessionHelper = new HttpSessionHelper(this);
        }
        return httpsessionHelper;
    }
    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }
    public JamesApiHelper jamesApi() {
        if (jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }
    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    public RestApiHelper rest() {
        if (restApiHelper == null) {
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }
    public String property(String name) {
        return properties.getProperty(name);
    }
}
