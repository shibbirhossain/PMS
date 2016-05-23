import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	static Clinic clinic = new Clinic();

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

            //System.out.println("Choose an option:");
			/*System.out.println("1. Add patient");
			System.out.println("2. Delete Patient");
			System.out.println("3. List all patients");
			System.out.println("4. Add a visit");
			System.out.println("5. View patient visit");
			System.out.println("6. Save & Exit");*/
			//sc = new Scanner(System.in);
            int selectedOption = Integer.parseInt(ScannerValidateInput.validateInteger("1. Add patient\n" +
					"2. Delete Patient\n" +
					"3. List all patients\n" +
					"4. Add a visit\n" +
					"5. View patient visit\n" +
					"6. Save & Exit\n" +
					"Choose any options from(1-6):"));

			if(selectedOption <= 0) System.out.println("Please choose a positive number");
			
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
				System.out.println("Please choose from 1 to 6 only");
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
			String line;
			ArrayList<String> list = new ArrayList<String>();

			//URL path = ClassLoader.getSystemResource(Utility.FILE_NAME);
			File file = new File(Utility.FILE_NAME);

			if (!file.exists()) {
				try {
					throw new HistoryNotFoundException("No file found with such name");
				} catch (HistoryNotFoundException e) {
					//System.out.println("No File found for the patient");
					//e.printStackTrace();
				}
			} else {
				FileReader fileReader = new FileReader(file);
				//FileWriter clearThings = new FileWriter(fileName);
				BufferedReader reader = new BufferedReader(fileReader);

				while ((line = reader.readLine()) != null) {
					list.add(line);
					//System.out.println("initialize from file "+line);
					//line = reader.readLine();
				}
				//clearThings.write("");
				//System.out.println("initializeFromFile() list.size()"+list.size());
				if (reader != null) reader.close();
				if (file != null) fileReader.close();
				if (list.size() != 0) {
						patientBuilderFromFile(list);
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		catch (NullPointerException e){
			//e.printStackTrace();
		}
	}

	/**
	 * Builds all the patients by reading from file
	 * at the start of the program
	 *
	 * @param list patient data which is read from file
	 * @throws IOException input/ output exception while reading
	 * 					   from file
	 */
	public static void patientBuilderFromFile(ArrayList<String> list) throws IOException {
		Patient patient;
		for(String readByWord : list){
			patient = new Patient(readByWord);
			//patient = new Patient(patientName, medicareNumber, dateOfBirth);
			clinic.addPatient(patient);
			patient.readAllVisit();
		}
	}

	/**
	 * This method shows the total visit details of
	 * a patient.
	 *
	 */
	private static void viewPatientVisit() {

		ArrayList<Visit> visitData = new ArrayList<Visit>();
		int medicareNumber = Integer.parseInt(ScannerValidateInput.validateInteger("Please enter the medicare number of the patient you want to see visit( No string or negative number allowed ): "));
		Patient patient;
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
		Patient patient;
		Visit aVisit = null;
		int medicareNumber = Integer.parseInt(ScannerValidateInput.validateInteger("Please enter the patient medicare number you want to add visit to( No string or negative number allowed ):"));
		patient = clinic.findPatientByMedicareNumber(medicareNumber);
		//double time = 0;
		if(patient != null) {

			//System.out.println();
			int typeOfVisit = ScannerValidateInput.validateTypeOfVisit("Please choose the kind of visit:" + "\n" +
					"1)Standard visit\n" +
					"2)Nurse visit\n" +
					"3)Specialize visit\n");

				String doctorName = ScannerValidateInput.validateString("Please enter doctor name( Only letters allowed ):");
				LocalDate dateOfVisit = ScannerValidateInput.validateDate("Please enter date of visit:(YYYY-MM-DD)");

				if (typeOfVisit == 1) aVisit = new StandardVisit(doctorName, dateOfVisit);
				else if (typeOfVisit == 2) aVisit = new NurseVisit(doctorName, dateOfVisit);
				else if (typeOfVisit == 3) {
					int time = Integer.parseInt(ScannerValidateInput.validateInteger("Please enter the appointment time(in minutes): "));
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
		int medicareNumber = Integer.parseInt(ScannerValidateInput.validateInteger("Please enter medicare number of the patient(No String or negatives allowed):"));
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

		int medicareNumber;
		medicareNumber = Integer.parseInt(ScannerValidateInput.validateInteger("Please enter medicare Number(Only numbers, no negatives allowed): "));
		Patient patient;
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
			String patientName = ScannerValidateInput.validateString("Please enter patient name(Only letters allowed):");
			LocalDate dateOfBirth = ScannerValidateInput.validateDate("Enter date of birth(YYYY-MM-DD):");

			System.out.println("Patient name is " + patientName + " " + medicareNumber + " " + dateOfBirth);
			patient = new Patient(patientName, medicareNumber, dateOfBirth);
			clinic.addPatient(patient);
			System.out.println(patient.toString());
		}
	}
}