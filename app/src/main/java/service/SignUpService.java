package service;

import androidx.lifecycle.LiveData;

import com.example.assignmentand1x.model.Account;
import com.example.assignmentand1x.viewModel.SignUpViewModel;

//class created for testing fields
public class SignUpService {

    SignUpViewModel signUpViewModel;

    public SignUpService(SignUpViewModel signUpViewModel) {
        this.signUpViewModel = signUpViewModel;
    }

    public static final String BLANK_FIELD_MSG = "First name, last name, username and password cannot be empty";
    public static final String PASSWORD_CONSTRAINT_MSG = "Password has to contain at least 5 signs";

    public String validateUserForm(String username, String firstName, String lastName, String password) {
        if (username.equals("") || firstName.equals("") || lastName.equals("")
                || password.equals("")) {
            return BLANK_FIELD_MSG;
        }
        if (password.length() < 5) {
            return PASSWORD_CONSTRAINT_MSG;
        }
        return null;
    }

    public void createUser(String username, String firstName, String lastName, String password) {

        signUpViewModel.createAccount(new Account(firstName, lastName,
                username, password));
    }

    public LiveData<Account> getUser(String username) {
        return signUpViewModel.getAccount(username);
    }
}
