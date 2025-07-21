package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    private By uploadPictureField = By.id("uploadPicture");
    private By saveButton = By.id("submit");
    private By successMsg = By.id("example-modal-sizes-title-lg");
    private By subjectField = By.id("subjectsInput");
    private By addressField = By.id("currentAddress");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void uploadPicture(String filePath) {
        driver.findElement(uploadPictureField).sendKeys(filePath);
    }

    public void fillRequiredFields() {
        type(subjectField, "Maths");
        type(addressField, "Test Address");
    }

    public void save() {
        click(saveButton);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }
} 