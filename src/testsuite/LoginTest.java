package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        WebElement signInBtn = driver.findElement(By.xpath("//li[@class='header__nav-item header__nav-sign-in']"));
        signInBtn.click();

        String expectedMessage = "Welcome Back!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[@class='page__heading']")); //xpath for locating dashboard
        String actualMessage = actualTextElement.getText();

        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void verifyTheErrorMessage() {
        WebElement signInBtn = driver.findElement(By.xpath("//li[@class='header__nav-item header__nav-sign-in']"));
        signInBtn.click();

        //Find the Email Field Element
        WebElement emailField = driver.findElement(By.id("user[email]"));
        // Type the Username to username field element
        emailField.sendKeys("miky12121@g.com");
        //Find the Password Field Element and send password on password field
        driver.findElement(By.id("user[password]")).sendKeys("Mike123456");
        //Find the Login btn Element and click
        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();

        String expectedMessage = "Invalid email or password.";
        WebElement actualTextElement = driver.findElement(By.xpath("//li[@class='form-error__list-item']"));
        String actualMessage = actualTextElement.getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
