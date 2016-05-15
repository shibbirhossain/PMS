import java.io.FileNotFoundException;

/*
 * It is a customized exception type. This exception is
 * thrown if patient list or visit list file is not found
 *
 * @author Shibbir
 * @version 1.0
 * @see FileNotFoundException
 *
 */
public class HistoryNotFoundException extends FileNotFoundException {
    /*
     * Constructor for HistoryNotFoundException class
     *
     * @param message takes the error message as String
     */
    public HistoryNotFoundException(String message){
        super(message);

    }
}
