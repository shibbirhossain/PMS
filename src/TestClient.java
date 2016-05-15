import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is the main class of the PMS program.
 * The program starts from this class and give the users choose between the following 6 options:
 * 1. Add patient
 * 2. Delete Patient
 * 3. List all patients
 * 4. Add a visit
 * 5. View patient visit
 * 6. Save & Exit
 *
 * @author Shibbir
 * @author Tanveer
 * @version 1.0
 *
 */

public class TestClient {

	
    static Patient patient;
    static Clinic clinic = new Clinic();
    static Scanner sc;

	/*
	 * This is the main method of the PMS program.
	 * Provides six different options for the user to
	 * choose from.
	 * @param args Unused.
	 * @return Nothing.
	 *
	 */
	public static void main(String[] args){
		
		boolean runProgram = true;
		initializeProgram();
		while(runProgram){
			System.out.println("Choose an option:");
			System.out.println("1. Add patient");
			System.out.println("2. Delete Patient");
			System.out.println("3. List all patients");
			System.out.println("4. Add a visit");
			System.out.println("5. View patient visit");
			System.out.println("6. Save & Exit");
			//sc = new Scanner(System.in);
			int selectedOption = ScannerValidateInput.validateInteger("");
			
			
			if(selectedOption == 1){
				createPatient();
			}
			else if(selectedOption == 2){
				deletePatient();
			}
			else if(selectedOption == 3){
				listAllPatients();
			}
			else if(selectedOption == 4){
					addVisitToPatient();
			}
			else if(selectedOption == 5){
				viewPatientVisit();
			}
			else if(selectedOption == 6){

				//wee keep them off for now
				try {
					clinic.saveAllPatients();
				} catch (IOException e) {
					e.printStackTrace();
				}
				runProgram = false;
				System.out.println("Thank you for using the system");
			}
			else{
				System.out.println("Please choose from below options");
			}
		} 
	}
	/*
	 * We initialize the program from this method.
	 * Calling the Clinic class method of initializeFromFile()
	 * to read data from the file.
	 *
	 * @throws IOException if an input or output exception occur
	 * 					   while reading from file
	 */
	private static void initializeProgram() {
		
		try {
			clinic.initializeFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method shows the total visit details of
	 * a patient.
	 *
	 */
	private static void viewPatientVisit() {

		ArrayList<Visit> visitData = new ArrayList<Visit>();
		//System.out.println();
		int medicareNumber = ScannerValidateInput.validateInteger("Please enter the medicare number of the patient you want to see visit:");
		patient = clinic.findPatientByMedicareNumber(medicareNumber);
		if(patient == null) System.out.println("No patient found with the provided medicare number");
		else {
			System.out.println("Total number of visit(s) for this patient is: " + patient.getTotalVisits());
			visitData = patient.getVisit();
			System.out.println("Visit Date" + "     " + "Doctor Name" + "     " + "    Fee");
			for (Visit v : visitData) {
				System.out.printf("%s%13s%16s", v.getDateOfVisit(), v.getDoctorName(), v.getFee());
				System.out.println();
			}
		}
	}


	/*
	 * Adds a single visit to a patient.
	 * Takes option from the patient about any of
	 * the three kinds of visit to be made.
	 *
	 */
	private static void addVisitToPatient(){
		//System.out.println();
		Visit aVisit = null;
		int medicareNumber = ScannerValidateInput.validateInteger("Please enter the patient medicare number you want to add visit to:");
		patient = clinic.findPatientByMedicareNumber(medicareNumber);
		//double time = 0;
		if(patient != null) {

			//System.out.println();
			int typeOfVisit = ScannerValidateInput.validateTypeOfVisit("Please choose the kind of visit:" + "\n" +
					"1)Standard visit\n" +
					"2)Nurse visit\n" +
					"3)Specialize visit\n");

				String doctorName = ScannerValidateInput.validateString("Please enter doctor name:");
				LocalDate dateOfVisit = ScannerValidateInput.validateDate("Please enter date of visit:(YYYY-MM-DD)");

				if (typeOfVisit == 1) aVisit = new StandardVisit(doctorName, dateOfVisit);
				else if (typeOfVisit == 2) aVisit = new NurseVisit(doctorName, dateOfVisit);
				else if (typeOfVisit == 3) {
					int time = ScannerValidateInput.validateInteger("Please enter the appointment time(in minutes)");
					aVisit = new SpecializeVisit(doctorName, dateOfVisit, time);
				}

				//patient.addVisit(doctorName, dateOfVisit, typeOfVisit);
				patient.addVisit(aVisit);
				//System.out.println("Visit added");
			//}
		}
		else{
			System.out.println("Sorry no patient found with the given medicare number");
			System.out.println("Please enter a proper medicare number again");
			addVisitToPatient();
		}
	}



	/*
	 * This method asks for the medicare number
	 * from the user and if it exists in the list,
	 * then deletes the patient from the list
	 */
	private static void deletePatient() {
		int medicareNumber = ScannerValidateInput.validateInteger("Please enter medicare number of the patient:");
		clinic.deletePatient(medicareNumber);
	}

	/*
	 * This method show all the patients
	 * list in the clinic.
	 */
	private static void listAllPatients() {
			ArrayList<Patient> patients = new ArrayList<Patient>();
			patients = clinic.listPatients();
			System.out.println("Medicare Number"+"     "+"Patient Name"+"     "+"Date of Birth"+"   "+"Total Visit(s)");
			for(Patient p: patients){
				System.out.printf("%10s%20s%18s%10s",p.getMedicareNumber(),p.getPatientName(),p.getDateOfBirth(),p.getTotalVisits());
				System.out.println();
			}
	}

	/*
	 * This method takes input from the user
	 * about the medicare number and if it
	 * does not exist then creates new patient
	 * @throws DuplicatePatientException If the user tries to create patient
	 * 									 with the medicare number that already
	 * 									 exists in the system then throws exception
	 */
	private static void createPatient() {
		//Scanner sc = new Scanner(System.in);
		
		int medicareNumber = ScannerValidateInput.validateInteger("Please enter medicare Number: ");
		if(clinic.isDuplicateMedicareNumber(medicareNumber)){
			try {
				System.out.println();
				throw new DuplicatePatientException("Already one patient exists with the same medicare number. Please try again");

			} catch (DuplicatePatientException e) {

				System.out.println();
			}
			finally {
				createPatient();
			}
		}
		else {
			System.out.println("The medicare Number is: " + medicareNumber);
			String patientName = ScannerValidateInput.validateString("Please enter patient name:");
			LocalDate dateOfBirth = ScannerValidateInput.validateDate("Enter date of birth(YYYY-MM-DD):");

			System.out.println("Patient name is " + patientName + " " + medicareNumber + " " + dateOfBirth);
			patient = new Patient(patientName, medicareNumber, dateOfBirth);
			clinic.addPatient(patient);
			System.out.println(patient.toString());
		}
	}
}