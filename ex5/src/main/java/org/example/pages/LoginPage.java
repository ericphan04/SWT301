package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameField = By.id("userName");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    private By errorMsg = By.id("name"); // demoqa.com có id này cho thông báo lỗi

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        navigateTo("https://demoqa.com/login");
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }
} 