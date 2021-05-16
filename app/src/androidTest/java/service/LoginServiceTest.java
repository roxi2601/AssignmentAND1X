package service;

import com.example.assignmentand1x.viewModel.LoginViewModel;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class LoginServiceTest {

    @Mock
    LoginViewModel viewModel;
    LoginService loginService = new LoginService(viewModel);

    @Test
    public void validateUserForm() {
        //given
        String username = "";
        String password = "";
        //when
        String actual = loginService.validateUserForm(username, password);
        //then
        assertEquals(LoginService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_EmptyUsername() {
        //given
        String username = "";
        String password = "testtest";
        //when
        String actual = loginService.validateUserForm(username, password);
        //then
        assertEquals(LoginService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateUserForm_EmptyPassword() {
        //given
        String username = "test";
        String password = "";
        //when
        String actual = loginService.validateUserForm(username, password);
        //then
        assertEquals(LoginService.BLANK_FIELD_MSG, actual);
    }
}