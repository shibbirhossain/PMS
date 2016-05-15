/**
 *
 * It is a customized exception type. This exception is
 * thrown if patient with same medicare number already
 * exists in the list
 *
 * @author Shibbir
 * @version 1.0
 *
 */
public class DuplicatePatientException extends Exception {


    /*
     * Constructor for DuplicatePatientException class
     *
     * @param message takes the error message as String
     */
    public DuplicatePatientException(String message){
        super(message);
        System.out.println("Already one patient exist with same medicare number");
    }
}
