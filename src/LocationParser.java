import java.io.*;
import java.util.*;

/**
 * Static helper class for parsing location strings.
 * 
 * @author Kevin Li
 */
public class LocationParser {
    private static final Set<String> LOCATION_SET = new HashSet<>();

    /**
     * Get whether or not the location string is a numerical id.
     *
     * @param place the parsed location string
     * @return true if place only contains digits, false otherwise
     */
    public static boolean isId(String place) {
        for (int i = 0; i < place.length(); i++) {
            if (!Character.isDigit(place.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fill the set of locations using data from the CSV file. Numerical ids
     * are ignored.
     *
     * @param csvFileName the name of the CSV file
     * @throws IOException if an I/O error occurs
     */
    public static void fillLocationsCSV(String csvFileName) throws IOException {
        LOCATION_SET.clear();
        // Read from the CSV file, putting in each country, name, subcountry entry
        // into LOCATION_SET (lowercased)
        BufferedReader reader = new BufferedReader(new FileReader(csvFileName));
        reader.readLine(); // skip headers
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            String[] tokens = line.split(",");
            boolean inQuote = false;
            String placeSoFar = null;
            for (String token : tokens) {
                if (inQuote) {
                    placeSoFar += "," + token;
                }
                else {
                    placeSoFar = token;
                }
                if (token.contains("\"")) {
                    inQuote = !inQuote;
                }
                if (!inQuote && !isId(placeSoFar)) {
                    LOCATION_SET.add(placeSoFar.toLowerCase());
                }
            }
        }
        reader.close();
        return;
    }

    /**
     * Get the word or phrase pertaining to the indicated location.
     * @param str the input string line
     * @return the location word or phrase
     */
    public static String getLocation(String input) {
        String inputLower = input.toLowerCase();
        for (String location : LOCATION_SET) {
            int index = inputLower.indexOf(location);
            if (index >= 0) {
                return input.substring(index, index + location.length());
            }
        }
        return null;
    }

}
