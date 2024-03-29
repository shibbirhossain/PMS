import java.io.IOException;
import java.time.LocalDate;

/**
 *  This class is extend from Visit class.
 *  NurseVisit is a concrete type of Visit.
 *  @author Shibbir
 *  @author Tanvir
 *  @version 1.0
 */
public class NurseVisit extends Visit {

    /**
     * constructor for NurseVisit
     * @param doctorName name of the doctor as String
     * @param dateOfVisit date of visit by patient as LocalDate
     */
    public NurseVisit(String doctorName, LocalDate dateOfVisit) {
        super(doctorName, dateOfVisit);
        this.fee = 50.00;
    }

    /**
     * Another constructor for {@link NurseVisit}
     * @param visitData all the visitData being read
     *                  from file is passed here to create {@link NurseVisit}
     */
    public NurseVisit(String visitData) {
        String[] formattedVisit = visitData.split(",");
        this.doctorName = formattedVisit[0];
        this.dateOfVisit = LocalDate.parse(formattedVisit[1]);
        this.fee = Double.parseDouble(formattedVisit[2]);
    }

    /**
     * accessor method for visitType
     *
     * @return String type of visit name
     */
    public String getVisitType() {
        return "Nurse";
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
        //String fileName = mediacareNumber+".txt";
        try {
            IOSupport.saveData(this,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
