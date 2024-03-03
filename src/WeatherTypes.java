/**
 * Helper enum for different weather types and the associated message to output.
 *
 * @author Kevin Li
 */
public enum WeatherTypes {
    SUNNY("a sunny _"),
    PARTIALLY_CLOUDY("a partially cloudy _"),
    CLOUDY("a cloudy _"),
    WINDY("a windy _"),
    LIGHT_RAIN("a _ with light rain"),
    RAIN("a rainy _"),
    THUNDERSTORM("a stormy _"),
    SLEET("a _ with sleet"),
    SNOW("a snowy _"),
    HAIL("a _ with chunks of ice falling on your head");

    final String RESPONSE;

    WeatherTypes(String response) {
        this.RESPONSE = response;
    }

    String getResponse(boolean daytime) {
        return RESPONSE.replace("_", daytime ? "day" : "night");
    }
}
