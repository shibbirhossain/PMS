import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 *Patient object is built in the patient class.
 * Patient consists of medicare number, date of
 * birth, patient name and ArrayList of visit for
 * each patient.
 *
 * @author Shibbir
 * @author Tanveer
 * @version 1.0
 */
public class Patient {
	private int medicareNumber;
	private LocalDate dateOfBirth;
	private String patientName;
	private ArrayList<Visit> visits;
	private ArrayList<String> visitList;
	private Clinic clinic;

	/*
	 *
	 * Constructor for Patient class
	 *
	 * @param patientName name of patient as String
	 * @param mediacareNumber medicare number as int
	 * @param dateOfBirth date of birth of patient as LocalDate
	 *
	 */
	public Patient(String patientName, int medicareNumber, LocalDate dateOfBirth) {
		this.patientName = patientName;
		this.dateOfBirth= dateOfBirth;
		this.medicareNumber = medicareNumber;
		visits = new ArrayList<Visit>();
		//clinic = new Clinic();
	}

	public Patient(String patientFile){

		String words[] = patientFile.split(",");
		this.patientName = words[0];
		this.medicareNumber = Integer.parseInt(words[1]);
		this.dateOfBirth = LocalDate.parse(words[2]);
		visits = new ArrayList<>();
		//clinic = new Clinic();
	}

	/**
	 * addVisit method adds a single visit to the
	 * visit list of the patient
	 * @param aVisit a single Visit object
     */
	public void addVisit(Visit aVisit){

		visits.add(aVisit);
		System.out.println("Visit Added");
	}


	/**
	 * total visit number made by patient
	 *
	 * @return int total number of visits made by patient
     */
	public int getTotalVisits(){
		return visits.size();
	}

	/**
	 * returns medicare number of the patient
	 *
	 * @return int medicare number of patient as int
     */
	public int getMedicareNumber(){
		return medicareNumber;
	}

	/**
	 * toString() method of Patient class
	 * @return String returns a String containing patientName, mediacareNumber, dateOfBirth
     */
	public String toString(){
		return patientName + ","+medicareNumber+","+ dateOfBirth;
	}

	/**
	 * accessor method for dateOfBirth
	 *
	 * @return LocalDate DOB of the patient
     */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	/**
	 * Accessor method for patientName
	 *
	 * @return String patientName
     */
	public String getPatientName() {
		
		return patientName;
	}

	/**
	 *
	 *
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws DateTimeParseException
     */
	public void readAllVisit() throws IOException, NumberFormatException, DateTimeParseException {
		String line;
		visitList= new ArrayList<String>();
		Visit singleVisit=null;

		File file = new File(getMedicareNumber()+".txt");

		if(!file.exists()) {

			try {
				throw new HistoryNotFoundException("No File found for such name");
			} catch (HistoryNotFoundException e) {

			}
			finally {
				//file.createNewFile();
				//file.delete();
			}
		}
		else {
			FileReader f = new FileReader(file);
			BufferedReader reader = new BufferedReader(f);


			while ((line = reader.readLine()) != null) {
				visitList.add(line);
			}
			if (!visitList.isEmpty()) {
				//int index = 0;
				for (String v : visitList) {
					String[] formattedVisit = v.split(",");


						if (formattedVisit.length != 4) {
							//break;
							try {
								throw new ReadErrorException("Corrupted data file");
							} catch (ReadErrorException e) {
								//e.printStackTrace();
								System.out.println("Corrupted data file");

							} finally {
								//System.out.println();

							}
						}
					else{
						String visitType = formattedVisit[3];
						//System.out.println(doctorName +" "+ dateOfVisit+" "+fee+" "+visitType);
						if (visitType.equals("Standard")) singleVisit = new StandardVisit(v);
						else if (visitType.equals("Nurse")) singleVisit = new NurseVisit(v);
						else if (visitType.equals("Specialize")) singleVisit = new SpecializeVisit(v);
						visits.add(singleVisit);

					}
				}
				}
			}
		}	//}


	/**
	 *  this method save all visits of the patient
	 *  in the file
	 * @throws IOException file input exception
	 * 					   while saving data to file
     */
	public void saveAllVisits() throws IOException {
		//System.out.println(getMedicareNumber()+".txt"+" Size is "+visits.size());
			String fileName = getMedicareNumber() + ".txt";
			//File file = new File(fileName);
			//file.delete();
			FileWriter fileWriter = new FileWriter(fileName);
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.print("");
			writer.close();
			for (Visit v : visits) {
				//
				v.saveVisit(fileName);
			}
	}

	/**
	 * Accessor method for ArrayList<Visit>
	 * @return ArrayList<Visit> visit list is returned
     */
	public ArrayList<Visit> getVisit() {
		return visits;
	}
}
