import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is the clinic class of the PMS program.
 * Clinic class has list of all patients. In the
 * clinic class it reads patient from file,
 * Add patient to list of patients, find duplicate
 * match for patient, delete patient from file,
 * find a particular patient.
 *
 * @author Shibbir
 * @author Tanvir
 * @version 1.0
 */

public class Clinic {
	//private static final String FILE_NAME = "PatientInfo.txt";
	//private Patient patient;
	//private String clinicName;
	private ArrayList<Patient> patients;

	/**
	 * Constructor method for Patient class.
	 */
	public Clinic() {
		patients = new ArrayList<Patient>();
	}

	/**
	 * Adds patient to the list of
	 * patients
	 * @param aPatient a Patient type object
     */
	public void addPatient(Patient aPatient){
		patients.add(aPatient);
	}

	/**
	 * Delete patient from the patient list if found in the list of patients
	 *
	 * @param medicareNumber unique medicare number for a patient is
	 *                       passed to delete it from list of patients
     */
	public void deletePatient(int medicareNumber){
		Iterator<Patient> iter = patients.iterator();
        boolean isFound = false;
		while (iter.hasNext()) {
		    Patient p = iter.next();

		    if (p.getMedicareNumber() == medicareNumber) {
                isFound = true;
                iter.remove();
				System.out.println("User successfully removed.");
			}
		}
        if(!isFound) {
            System.out.println("No users found with this medicare number.");
        }
	}

	/**
	 * Checks whether there is already a patient with same
	 * medicare number
	 * @param medicareNumber unique medicare number for a patient
	 *                       type is int
	 * @return boolean returns true if found, otherwise false
     */
	public boolean isDuplicateMedicareNumber(int medicareNumber){

		Iterator<Patient> iter = patients.iterator();
		boolean isFound = false;
		while (iter.hasNext()) {
			Patient p = iter.next();

			if (p.getMedicareNumber() == medicareNumber) {
				isFound = true;

				return isFound;
			}
		}

		return isFound;
	}

	/**
	 * Accessor method for ArrayList<Patient> patients
	 * @return patients ArrayList<{@link Patient}
     */
	public ArrayList<Patient> listPatients(){
		return patients;
	}




	/**
	 *
	 * Finds the patient in the list by medicare number
	 *
	 * @param medicareNumber unique medicare number for a patient
	 *                       is passed
	 * @return Patient Patient object is returned if found in the
	 * 				   list
     */
	public Patient findPatientByMedicareNumber(int medicareNumber) {
		for(Patient p: patients){
			if(p.getMedicareNumber() == medicareNumber){
				return p;
			}
		}
		return null;
	}

	/**
	 * Saves all patients in the file
	 * @throws IOException input exception while writing
	 * 					   into file
     */
	public void saveAllPatients() throws IOException{
		FileWriter file = new FileWriter(Utility.FILE_NAME);
		file.write("");
		for(Patient p : patients){
			IOSupport.saveData(p, Utility.FILE_NAME);
			p.saveAllVisits();
		}
	}
}
