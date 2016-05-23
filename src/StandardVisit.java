import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 *  This class is extend from Visit class.
 *  StandardVisit is a concrete type of Visit.
 *  @author Shibbir
 *  @author Tanvir
 *  @version 1.0
 */
public class StandardVisit extends Visit{


    /**
     * constructor for StandardVisit
     * @param doctorName name of the doctor as String
     * @param dateOfVisit date of visit by patient as LocalDate
     */
    public StandardVisit(String doctorName,  LocalDate dateOfVisit) {
        super(doctorName, dateOfVisit);
        this.fee = 25.00;
    }

    /**
     * Another constructor for StandardVisit
     * @param visitData all the visitData being read
     *                  from file is passed here to create StandardVisit
     */
    public StandardVisit(String visitData) {
        String[] formattedVisit = visitData.split(",");
        this.doctorName = formattedVisit[0];
        this.dateOfVisit = LocalDate.parse(formattedVisit[1]);
        this.fee = Double.parseDouble(formattedVisit[2]);
        //String visitType = formattedVisit[3];
    }

    /**
     * accessor method for visitType
     *
     * @return String type of visit name
     */
    public String getVisitType() {
        return "Standard";
    }
    /**
     * accessor method for fee
     * @return fee fee of doctor as double
     */
    public double getFee(){
        return fee;
    }
    /**
     * toString() shows this class data in specified format
     * @return String "doctorName,dateOfVisit,fee,visitType" as String
     */
    public String toString() {
        return getDoctorName()+","+getDateOfVisit()+","+getFee()+","+getVisitType();
    }

    /**
     * Save the visit by the patient
     * @param fileName file name for visit data
     *                 to be saved in
     */
    public void saveVisit(String fileName) {

        try {
            IOSupport.saveData(this,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
