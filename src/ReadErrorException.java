/**
 * It is a customized exception type.
 *
 * @author Shibbir
 * @author Tanvir
 * @version 1.0
 */
public class ReadErrorException extends Exception {

    /**
     * It is the constructor for ReadErrorException class
     * @param message takes the error message as String
     */
    public ReadErrorException(String message){
        super(message);
        System.out.println(message);
    }
}
