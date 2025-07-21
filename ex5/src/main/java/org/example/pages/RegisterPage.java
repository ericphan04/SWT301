package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderMaleLabel = By.cssSelector("label[for='gender-radio-1']");
    private By phoneField = By.id("userNumber");
    private By subjectField = By.id("subjectsInput");
    private By addressField = By.id("currentAddress");
    private By pictureField = By.id("uploadPicture");
    private By submitButton = By.id("submit");
    private By successMsg = By.id("example-modal-sizes-title-lg");
    private By errorMsg = By.cssSelector(".flash.error");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void register(String firstName, String lastName, String email, String gender, String phone, String subject, String address, String picturePath) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        if (gender.equalsIgnoreCase("male")) {
            click(genderMaleLabel);
        }
        type(phoneField, phone);
        type(subjectField, subject);
        type(addressField, address);
        if (picturePath != null && !picturePath.isEmpty()) {
            driver.findElement(pictureField).sendKeys(picturePath);
        }
        click(submitButton);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }
} 