import java.time.LocalDate;
/*
 *This is the Visit class for
 * each patient. It is an
 * abstract Visit class.
 *
 * @author Shibbir
 * @version 1.0
 *
 */
public abstract class Visit {

	protected String doctorName;
	protected double fee;
	protected LocalDate dateOfVisit;
	//private Patient patient;

	/*
	 * Constructor for Visit class.
	 * @param doctorName holds the doctor name as String
	 * @param dateOfVisit holds the date of visit for the
	 *
	 */
    public Visit(String doctorName,  LocalDate dateOfVisit) {
		 this.doctorName = doctorName;
		 this.dateOfVisit = dateOfVisit;
	}

	/**
	 * constructor for visit
	 */
	protected Visit(){

	}

	/*
	 * accessor method for variable doctorName
	 * @return doctorName name of the doctor as String
	 */
	public String getDoctorName(){
    	return doctorName;
    }

	/*
	 * accessor method for variable fee
	 * @return double fee of the doctor
	 */
	public double getFee(){ return fee; }

	/*
	 * accessor method for variable dateOfVisit
	 * @return LocalDate date of visit
	 */
    public LocalDate getDateOfVisit(){
    	return dateOfVisit;
    }

	/*
	 * abstract accessor method for variable visit type.
	 * Will be implemented in child class.
	 *
	 * @return String type of visit
	 */
	public abstract String getVisitType();

	/*
	 * abstract method for toString().
	 * Will be implemented in child class.
	 *
	 * @return String prints in child class toString()
	 */
    public abstract String toString();


	/*
	 * abstract method save patient visit.
	 * Will be implemented in child class.
	 *
	 * @param fileName takes the file name to save patient
	 * 					visit
	 * @return Nothing
	 */
	public abstract void saveVisit(String fileName);
}
