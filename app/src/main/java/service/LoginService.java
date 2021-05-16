package service;

import com.example.assignmentand1x.viewModel.LoginViewModel;

//class created for testing fields
public class LoginService {
    LoginViewModel loginViewModel;
    public static final String BLANK_FIELD_MSG = "Username and password cannot be empty";

    public LoginService(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public String validateUserForm(String username, String password) {
        if (username.equals("") || password.equals("")) {
            return BLANK_FIELD_MSG;
        }
        return null;
    }
}
