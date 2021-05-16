package service;

import com.example.assignmentand1x.viewModel.EditOfferViewModel;

//class created for testing fields
public class EditOfferService {
    EditOfferViewModel offerViewModel;
    public static final String BLANK_FIELD_MSG = "Fields cannot be empty";

    public EditOfferService(EditOfferViewModel offerViewModel) {
        this.offerViewModel = offerViewModel;
    }

    public String validateOfferForm(String email, String title, String time, String date,
                                    String localization, String description) {
        if (email.equals("") || title.equals("") || time.equals("") || date.equals("")
                || localization.equals("") || description.equals("")) {
            return BLANK_FIELD_MSG;
        }
        return null;
    }
}
