package hieupt.example;

public class AccountService {
    public boolean registerAccount(String username, String password, String email) {
        // Kiểm tra username không được rỗng
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra password phải lớn hơn 6 ký tự
        if (password == null || password.length() <= 6) {
            return false;
        }
        
        // Kiểm tra email hợp lệ
        if (!isValidEmail(email)) {
            return false;
        }
        
        return true;
    }
    
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra email có chứa @ và domain
        // Email phải có định dạng: text@domain.com
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
