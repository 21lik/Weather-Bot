import java.io.*;
import java.util.*;

/**
 * A bot class for gathering information about the weather at a given time and location.
 * 
 * @author Kevin Li
 * @version 0.1.0
 */
public class WeatherBot implements Bot {
    final InputStream inputStream;
    final OutputStream outputStream;

    Scanner inputScanner;
    Writer outputWriter;

    WeatherBot(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public String initResponse() {
        return "Welcome to WeatherBot, developed by Kevin Li. I can tell you the weather at a location given a time.\n";
    }

    @Override
    public void init() throws IOException {
        this.inputScanner = new Scanner(inputStream);
        this.outputWriter = new PrintWriter(outputStream);
        outputWriter.write(initResponse());
        outputWriter.flush();
        return;
    }

    @Override
    public String getUserInput() {
        return inputScanner.nextLine();
    }

    /**
     * Get whether or not the input is an indication the user wants to exit.
     * Case is ignored.
     *
     * @param input the user input
     * @return true if the input is equal to "Quit" or "Q", false otherwise
     */
    boolean exitString(String input) {
        return input.equalsIgnoreCase("Quit") || input.equalsIgnoreCase("Q");
    }

    /**
     * Get whether or not the input is an indication the user wants to reset.
     * Case is ignored.
     *
     * @param input the user input
     * @return true if the input is equal to "Reset" or "R", false otherwise
     */
    boolean resetString(String input) {
        return input.equalsIgnoreCase("Reset") || input.equalsIgnoreCase("R");
    }

    /**
     * Get the weather, given the time and location.
     * 
     * @param inputTokens an array with two elements: the time and the location
     * @return the weather
     */
    @Override
    public String getResponse(String[] inputTokens) {
        // TODO Auto-generated method stub

        // Use REST API to search a website for the Weather data, using the
        // time and location as the inputs and parsing them into the URL.
        // Then, collect the temperature and weather, storing them in their
        // respective local variables below.
        double tempFahrenheit = 68, tempCelsius = (tempFahrenheit - 32) * 5 / 9; // TODO: default
        WeatherTypes weather = WeatherTypes.SUNNY; // TODO: default
        return inputTokens[0] + " at " + inputTokens[1] + " " + TimeParser.getIndicative(inputTokens[0]) + " " + weather.getResponse(!TimeParser.isNight(inputTokens[0])) + " at " + Long.toString(Math.round(tempFahrenheit)) + " degrees Fahrenheit (" + Long.toString(Math.round(tempCelsius)) + " degrees Celsius).\n";
    }

    @Override
    public boolean step() throws IOException {
        String time = null, location = null;
        outputWriter.write("When and where do you want to get the weather?\n");
        outputWriter.write("Type Quit or Q to exit, Reset or R to restart this step.\n");
        outputWriter.flush();
        do {
            final String input = getUserInput();
            if (exitString(input))
                return false;
            else if (resetString(input)) {
                time = null;
                location = null;
                outputWriter.write("When and where do you want to get the weather?\n");
                outputWriter.flush();
                continue;
            }
            if (time == null) {
                time = TimeParser.getTime(input);
            }
            if (location == null) {
                location = LocationParser.getLocation(input);
            }
            if (time == null && location == null) {
                outputWriter.write("Sorry, I did not get your date and location. Please try again.\n");
                outputWriter.flush();
            }
            else if (time == null) {
                outputWriter.write("What day do you want to get the weather?\n");
                outputWriter.flush();
            }
            else if (location == null) {
                outputWriter.write("Where do you want to get the weather? Please do not use relative locations (e.g. here, five miles away, etc.).\n");
                outputWriter.flush();
            }
        } while (time == null || location == null);
        final String response = getResponse(new String[]{time, location});
        outputWriter.write(response);
        outputWriter.flush();
        return true;
    }
    
    @Override
    public int run() throws IOException {
        int iterations = 0;
        while (step()) {
            iterations++;
        }
        outputWriter.write("Thank you for using WeatherBot. Have a nice day.\n");
        outputWriter.flush();
        return iterations;
    }

    @Override
    public void close() throws IOException {
        inputScanner.close();
        outputWriter.close();
        return;
    }
}
