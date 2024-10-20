package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;

    private LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        DriverSingleton.getInstance("firefox");
        driver = DriverSingleton.getDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage();
    }

    @AfterClass
    public void finish(){
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }

    // --positive test--
    @Test(priority = 4)
    public void testLoginValid(){
        loginPage.loginUser("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.getTxtProduct(), "Products");
    }

    // --negative test--
    // username is wrong
    @Test(priority = 1)
    public void testLoginInvalidUsername(){
        loginPage.loginUser("stand_use", "secret_sauce");
        String actual = loginPage.getTxtInvalidUser();
        String expected = "Epic sadface: Username and password do not match any user in this service";
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }

    // password is wrong
    @Test(priority = 2)
     public void testLoginInvalidPassword(){
        loginPage.loginUser("standard_user", "secretsauce1");
        String actual = loginPage.getTxtInvalidUser();
        String expected = "Epic sadface: Username and password do not match any user in this service";
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }

    // username required
    @Test(priority = 3)
    public void testBlankUsername() {
        loginPage.loginUser("", "");
        String actual = loginPage.getTxtInvalidUser();
        String expected = "Epic sadface: Username is required";
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }

}
