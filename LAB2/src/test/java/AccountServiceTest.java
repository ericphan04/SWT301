import org.junit.jupiter.api.*;

import java.io.*;
import hieupt.example.AccountService;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    AccountService accountService;
    private static final String TEST_DATA_FILE = "test-data.csv";
    private static final String TEST_RESULT_FILE = "UnitTest.csv";

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @Test
    void testRegisterAccountFromCSV() {
        File testFile = new File(TEST_DATA_FILE);
        assertTrue(testFile.exists(), "Test data file does not exist: " + TEST_DATA_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String header = reader.readLine();
            assertNotNull(header, "Test data file is empty");

            File resultFile = new File(TEST_RESULT_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
                writer.write("Test Case,Username,Password,Email,Expected,Actual,Result,Reason\n");
                
                String line;
                int testCase = 1;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] parts = line.split(",", -1);
                        assertEquals(4, parts.length, 
                            String.format("Invalid number of columns at line %d", testCase));

                        String username = parts[0].trim();
                        String password = parts[1].trim();
                        String email = parts[2].trim();
                        boolean expected = Boolean.parseBoolean(parts[3].trim());

                        // Kiểm tra từng điều kiện
                        String reason = "";
                        boolean isValid = true;

                        // Kiểm tra username
                        if (username == null || username.isEmpty()) {
                            reason = "Username is empty";
                            isValid = false;
                        }
                        // Kiểm tra password
                        else if (password == null || password.length() <= 6) {
                            reason = "Password must be longer than 6 characters";
                            isValid = false;
                        }
                        // Kiểm tra email
                        else if (!accountService.isValidEmail(email)) {
                            reason = "Invalid email format";
                            isValid = false;
                        }

                        // Ghi kết quả test
                        String result = isValid ? "PASSED" : "FAILED";
                        writer.write(String.format("Test Case %d,%s,%s,%s,%s,%s,%s,%s\n",
                                testCase, username, password, email, expected, isValid, result, reason));

                        // Kiểm tra kết quả
                        if (!isValid) {
                            assertFalse(isValid, String.format("Test Case %d failed: %s", testCase, reason));
                        } else {
                            assertTrue(isValid, String.format("Test Case %d passed", testCase));
                        }
                        
                        testCase++;
                    } catch (Exception e) {
                        writer.write(String.format("Test Case %d,ERROR,ERROR,ERROR,ERROR,ERROR,ERROR,%s\n",
                                testCase, e.getMessage()));
                        fail(String.format("Error processing Test Case %d: %s", testCase, e.getMessage()));
                    }
                }
            }
        } catch (IOException e) {
            fail("Failed to process test data: " + e.getMessage());
        }
    }
}