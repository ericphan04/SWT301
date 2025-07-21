package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.pages.ProfilePage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Profile Tests")
public class ProfileTest extends BaseTest {
    static ProfilePage profilePage;

    @BeforeAll
    static void initPage() {
        profilePage = new ProfilePage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Upload ảnh thành công")
    void testUploadPicture() {
        profilePage.navigate();
        profilePage.fillRequiredFields();
        String filePath = System.getProperty("user.dir") + "/src/test/resources/test.jpg";
        profilePage.uploadPicture(filePath);
        profilePage.save();
        assertTrue(profilePage.getSuccessMessage().contains("Thanks for submitting the form"));
    }
} 