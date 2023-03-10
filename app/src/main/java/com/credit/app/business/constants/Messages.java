package com.credit.app.business.constants;

public final class Messages {

    public static final String CUSTOMER_MESSAGE = "Customer(s) ";

    public static final String INDIVIDUAL_EXISTS = "National ID is already registered.";
    public static final String INDIVIDUAL_NOT_FOUND = "No user found with given national ID.";

    public static final String CORPORATE_EXISTS = "Tax number is already registered.";
    public static final String CORPORATE_NOT_FOUND = "No user found with given tax number.";

    public static final String INSUFFICIENT_CREDIT_SCORE = "Insufficient credit score.";

    public static final String LISTED = " listed.";
    public static final String ADDED = " added.";
    public static final String DELETED = " deleted.";
    public static final String UPDATED = " updated.";
    public static final String NOT_FOUND = " not found.";

    public static final String LISTED_ERR = "Error while listing.";
    public static final String ADDED_ERR = "Error while adding.";
    public static final String DELETED_ERR = "Error while deleting.";
    public static final String UPDATED_ERR = "Error while updating.";

    public static final String REQUIRED = " is required.";
    public static final String INVALID = " is invalid.";
    public static final String TOO_SHORT = " is too short.";
    public static final String TOO_LONG = " is too long.";
    public static final String MIN = " can not be less than 0.";
    public static final String CHAR_LIMIT = " must be 10 characters.";

    private Messages() {
    }
}
