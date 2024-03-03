import java.io.IOException;

/**
 * A class for the chatbot application.
 * 
 * @author Kevin Li
 */
public class Application {
    public Application() throws IOException {
        LocationParser.fillLocationsCSV("world-cities_csv.csv");
        final Bot thisBot = new WeatherBot(System.in, System.out);
        thisBot.init();
        thisBot.run();
        thisBot.close();
    }
}
