import java.io.IOException;

/**
 * Shared interface for the Bot class. Add methods common to all chatbots here.
 */
public interface Bot {
    /**
     * Get the initial output of the chatbot. This should be outputted in init().
     *
     * @return the chatbot's initial response
     */
    String initResponse();

    /**
     * Initialize the bot.
     * @throws IOException if an I/O error occurs
     */
    void init() throws IOException;

    /**
     * Get the user's input.
     *
     * @return the user's input/request to the bot
     */
    String getUserInput();

    /**\
     * Get the response appropriate to the input.
     *
     * @param inputTokens relevant tokens from the user input string
     * @return the appropriate response
     */
    String getResponse(String[] inputTokens);

    /**
     * Run a step of the chatbot. At minimum, this consists of getting the user
     * input with getUserInput() and outputting getResponse().
     * 
     * @return false if the input indicates the user wants to exit the chatbot,
     *         true otherwise.
     * @throws IOException if an I/O error occurs
     */
    boolean step() throws IOException;

    /**
     * Run the chatbot. Return when the user quits.
     * 
     * @return the number of steps run
     * @throws IOException if an I/O error occurs
     */
    int run() throws IOException;

    /**
     * Close the chatbot. All closeables opened in init should be closed here.
     * 
     * @throws IOException if an I/O error occurs
     */
    void close() throws IOException;
}
