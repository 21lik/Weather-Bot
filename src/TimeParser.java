import java.util.Calendar;

/**
 * Static helper class for parsing date strings.
 * 
 * @author Kevin Li
 */
public class TimeParser {
    private static final String[] DAYS_OF_WEEK = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    private static final String[] MONTHS_OF_YEAR = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
    /**
     * Get the word or phrase pertaining to the indicated time or day.
     * @param str the input string line
     * @return the time word or phrase
     */
    static String getTime(String str) {
        // TODO: complete
        return "today"; // TODO: default
    }

    /**
     * Get the appropriate indicative for the time token. This would be "is"
     * for the present, "was" for the past, and "will be" for the future.
     * 
     * @param time the inputted time string
     * @return the respective indicative string
     */
    static String getIndicative(String time) {
        time = time.toLowerCase();
        // Relative time words
        if (time.contains("yesterday") || time.contains("last")) {
            return "was";
        }
        else if (time.contains("today")) {
            return "is";
        }
        else if (time.contains("tomorrow") || time.contains("next")) {
            return "will be";
        }
        else if (time.contains("this")) {
            for (int i = 0; i < 7; i++) {
                if (time.contains(DAYS_OF_WEEK[i])) {
                    return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == i ? "is" : "will be"; // we assume "this xxxday", if not today, refers to the first xxxday not before today
                }
            }
            if (time.contains("week") || time.contains("month") || time.contains("year")) {
                return "is";
            }
        }
        else {
            for (int i = 0; i < 12; i++) {
                if (time.contains(MONTHS_OF_YEAR[i])) {
                    return Calendar.getInstance().get(Calendar.MONTH) == i ? "is" : "was";
                }
            }
        }
        return "is"; // TODO: this method does not consider all cases
    }

    /**
     * Get whether or not the time explicitly implies nighttime.
     *
     * @param time the inputted time string
     * @return true if time contains night, false otherwise
     */
    static boolean isNight(String time) {
        return time.toLowerCase().contains("night");
    }
}
