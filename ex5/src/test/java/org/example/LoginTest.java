package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.pages.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests")
public class LoginTest extends BaseTest {
    static LoginPage loginPage;

    @BeforeAll
    static void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Đăng nhập thành công với tài khoản hợp lệ")
    void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("testuser", "Test@1234");
        // Kiểm tra chuyển trang hoặc hiển thị thông tin user (tùy app thực tế)
        // assertTrue(...);
    }

    @Test
    @Order(2)
    @DisplayName("Báo lỗi khi nhập sai thông tin")
    void testLoginFail() {
        loginPage.navigate();
        loginPage.login("testuser", "sai_pass");
        assertTrue(loginPage.getErrorMessage().contains("Invalid username or password")
            || loginPage.getErrorMessage().length() > 0);
    }
} 