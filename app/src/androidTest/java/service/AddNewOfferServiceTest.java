package service;

import com.example.assignmentand1x.viewModel.AddNewOfferViewModel;
import com.example.assignmentand1x.viewModel.LoginViewModel;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class AddNewOfferServiceTest {

    @Mock
    AddNewOfferViewModel viewModel;
    AddNewOfferService offerService = new AddNewOfferService(viewModel);

    @Test
    public void validateOfferForm() {
        //given
        String email = "";
        String title = "";
        String time = "";
        String date = "";
        String localization = "";
        String description = "";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateOfferForm_EmptyEmail() {
        //given
        String email = "";
        String title = "test";
        String time = "test";
        String date = "test";
        String localization = "test";
        String description = "test";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateOfferForm_EmptyTitle() {
        //given
        String email = "test";
        String title = "";
        String time = "test";
        String date = "test";
        String localization = "test";
        String description = "test";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateOfferForm_EmptyTime() {
        //given
        String email = "test";
        String title = "test";
        String time = "";
        String date = "test";
        String localization = "test";
        String description = "test";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateOfferForm_EmptyDate() {
        //given
        String email = "test";
        String title = "test";
        String time = "test";
        String date = "";
        String localization = "test";
        String description = "test";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateOfferForm_EmptyLocalization() {
        //given
        String email = "test";
        String title = "test";
        String time = "test";
        String date = "test";
        String localization = "";
        String description = "test";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }

    @Test
    public void validateOfferForm_EmptyDescription() {
        //given
        String email = "test";
        String title = "test";
        String time = "test";
        String date = "test";
        String localization = "test";
        String description = "";
        //when
        String actual = offerService.validateOfferForm(email, title, time, date, localization, description);
        //then
        assertEquals(AddNewOfferService.BLANK_FIELD_MSG, actual);
    }
}