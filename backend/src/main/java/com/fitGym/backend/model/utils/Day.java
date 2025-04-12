package com.fitGym.backend.model.utils;

public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    Day() {

    }

    public static Day fromNumber(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            case 7:
                return SUNDAY;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            case FRIDAY:
                return "Friday";
            case SATURDAY:
                return "Saturday";
            case SUNDAY:
                return "Sunday";
            default:
                return null;
        }

    }
}
