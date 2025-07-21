package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.pages.RegisterPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Register Tests")
public class RegisterTest extends BaseTest {
    static RegisterPage registerPage;

    @BeforeAll
    static void initPage() {
        registerPage = new RegisterPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Đăng ký thành công với upload ảnh")
    void testRegisterSuccessWithPicture() {
        registerPage.navigate();
        String filePath = System.getProperty("user.dir") + "/src/test/resources/test.jpg";
        registerPage.register(
            "John", "Doe", "john.doe@example.com", "male", "1234567890", "Maths", "Test Address", filePath
        );
        assertTrue(registerPage.getSuccessMessage().contains("Thanks for submitting the form"));
    }

    @Test
    @Order(2)
    @DisplayName("Báo lỗi khi email không hợp lệ")
    void testRegisterInvalidEmail() {
        registerPage.navigate();
        String filePath = System.getProperty("user.dir") + "/src/test/resources/test.jpg";
        registerPage.register(
            "John", "Doe", "invalidemail", "male", "1234567890", "Maths", "Test Address", filePath
        );
        // Trang này không hiện lỗi rõ ràng, nên kiểm tra không xuất hiện modal thành công
        assertThrows(Exception.class, () -> registerPage.getSuccessMessage());
    }
} 