package com.credit.app.business.constants;

public final class Constants {
    public static final int CREDIT_LIMIT_MULTIPLIER = 4;
    public static final int CREDIT_LIMIT_DIVISOR = 2;

    public static final int CREDIT_SCORE_MIN = 500;
    public static final int CREDIT_SCORE_LIMIT = 1000;

    public static final double INCOME_MIN = 5000;
    public static final double INCOME_MAX = 10000;

    public static final double MIN_CREDIT_AMOUNT = 10000;
    public static final double MID_CREDIT_AMOUNT = 20000;

    public static final double MIN_GUARANTEE = (double) 10 / 100;
    public static final double LOW_GUARANTEE = (double) 20 / 100;
    public static final double MID_GUARANTEE = (double) 25 / 100;
    public static final double MAX_GUARANTEE = (double) 50 / 100;

    private Constants() {
    }

}
