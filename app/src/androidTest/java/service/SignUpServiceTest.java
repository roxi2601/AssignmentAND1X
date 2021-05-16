package service;

import com.example.assignmentand1x.viewModel.SignUpViewModel;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class SignUpServiceTest {

    @Mock
    SignUpViewModel viewModel;
    SignUpService signUpService = new SignUpService(viewModel);

    @Test
    public void validateUserForm() {
        //given
        String firstName = "";
        String lastName = "";
        String username = "";
        String password = "";
        //when
        String actual = signUpService.validateUserForm(username, firstName, lastName, password);
        //then
        assertEquals(SignUpService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_EmptyFirstName() {
        //given
        String firstName = "";
        String lastName = "test";
        String username = "test";
        String password = "test";
        //when
        String actual = signUpService.validateUserForm(username, firstName, lastName, password);
        //then
        assertEquals(SignUpService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_EmptyLastName() {
        //given
        String firstName = "test";
        String lastName = "";
        String username = "test";
        String password = "test";
        //when
        String actual = signUpService.validateUserForm(username, firstName, lastName, password);
        //then
        assertEquals(SignUpService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_EmptyUsername() {
        //given
        String firstName = "test";
        String lastName = "test";
        String username = "";
        String password = "test";
        //when
        String actual = signUpService.validateUserForm(username, firstName, lastName, password);
        //then
        assertEquals(SignUpService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_EmptyPassword() {
        //given
        String firstName = "test";
        String lastName = "test";
        String username = "test";
        String password = "";
        //when
        String actual = signUpService.validateUserForm(username, firstName, lastName, password);
        //then
        assertEquals(SignUpService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_PasswordIsTooShort() {
        //given
        String firstName = "test";
        String lastName = "test";
        String username = "test";
        String password = "te";
        //when
        String actual = signUpService.validateUserForm(username, firstName, lastName, password);
        //then
        assertEquals(SignUpService.PASSWORD_CONSTRAINT_MSG, actual);
    }

    @Test
    public void createUser_DoesNotExist() {
        //given
        String firstName = "test";
        String lastName = "test";
        String username = "test";
        String password = "testtest";
        //when
        signUpService.createUser(username, firstName, lastName, password);
        //  signUpService.getUser(username).observeForever(account);
        //then

    }

}