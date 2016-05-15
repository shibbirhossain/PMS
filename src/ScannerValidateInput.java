import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * In this class we validate all the
 * user inputs.
 * @author Shibbir
 * @version 1.0
 */
public class ScannerValidateInput {

    /*
     * Here, is validated whether the provided
     * input is a integer or not. Even though,
     * 0 is an integer the program considers
     * it as a non-valid input. Because we expect
     * medicare number to be non zero positive number
     * and appointment time for Specialize visit should
     * be non zero positive as well.
     * @param showTest String to be displayed while taking
     *                 user input.
     *
     *  @return number returns the validated integer
     */
    public static int validateInteger(String showText) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
        	System.out.print(showText);
            while (!scanner.hasNextInt()) {
            	String input = scanner.next();
            	System.out.printf("%s is not a valid input. Please enter any number greater than zero.\n", input);
            	System.out.print(showText);
                
            }
            number = scanner.nextInt();
        } while (number <= 0);

        return number;
    }

    public static double validateDouble(String showText) {
        Scanner scanner = new Scanner(System.in);
        double number;
        do {
            System.out.print(showText);
            while (!scanner.hasNextDouble()) {
                String input = scanner.next();
                System.out.printf("%s is not a valid number.\n", input);
                System.out.print(showText);

            }
            number = scanner.nextDouble();
        } while (number <= 0);

        return number;
    }

    /*
     * Here, is validated whether the provided
     * input is a valid String or not. Our expectation
     * of String is if it consists letters from a-z or
     * A-Z, only then we consider this as a valid String
     * for patient name or doctor name. The program does
     * not accept numbers or special characters as valid
     * name.
     *
     * @param showTest String to be displayed while taking
     *                 user input.
     *
     *  @return name returns the validated String
     */
    public static String validateString(String showText){
    	
    	Scanner scanner = new Scanner(System.in);
    	String regex = "^[a-zA-Z ]+$";
    	
    	String name;
		do{	 System.out.println(showText);
    		 name = scanner.nextLine();
    	}
    	while(!name.matches(regex));
    	
    	return name;
    }
    /*
     * Here, is validated whether the provided
     * input is a valid LocalDate or not. Our expected
     * LocalDate format is of type YYYY-MM-DD
     * @param showTest String to be displayed while taking
     *                 user input.
     * @exception DateTimeParseException catches DateTimeParseException if
     *                                   the LocalDate is not in expected
     *                                   format
     * @return date returns the validated LocalDate
     */
    public static LocalDate validateDate(String showText){
    	Scanner scanner = new Scanner(System.in);
    	boolean run = true;
        LocalDate date=null;
		String input;
        while(run) {
            try {
                System.out.println(showText);
                input = scanner.next();

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");

                date = LocalDate.parse(input);
                run = false;
            } catch (DateTimeParseException e) {
                System.out.println("Date time format is not correct");
            }
        }
    	return date;
    }

    /*
     * Here, is validated whether the provided
     * input is a valid type of visit or not.
     * If the type of visit is within integer
     * 1 to 3 only then we consider this as
     * valid.
     *
     * @param showTest String to be displayed while taking
     *                 user input.
     *
     *  @return number returns the validated integer
     */
    public static int validateTypeOfVisit(String showText) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.print(showText);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("%s is not a valid number.\n", input);
                System.out.print(showText);

            }
            number = scanner.nextInt();
        } while (number <= 0 || number >3 );

        return number;
    }
    
}