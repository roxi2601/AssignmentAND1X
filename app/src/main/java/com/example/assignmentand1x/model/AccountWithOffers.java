package com.example.assignmentand1x.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AccountWithOffers {
    @Embedded public Account account;
    @Relation(
            parentColumn = "accountId",
            entityColumn = "offerAccountId"
    )
    public List<Offer> offers;
}
