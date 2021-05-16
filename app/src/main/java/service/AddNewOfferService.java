package service;

import com.example.assignmentand1x.viewModel.AddNewOfferViewModel;

//class created for testing fields
public class AddNewOfferService {
    AddNewOfferViewModel offerViewModel;
    public static final String BLANK_FIELD_MSG = "Fields cannot be empty";

    public AddNewOfferService(AddNewOfferViewModel offerViewModel) {
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
