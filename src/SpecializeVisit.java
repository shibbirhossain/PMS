import java.io.IOException;
import java.time.LocalDate;

/**
 * This class is extend from Visit class.
 * SpecializeVisit is a concrete type of Visit.
 * @author Shibbir
 * @author Tanvir
 * @version 1.0
 * */
public class SpecializeVisit extends Visit {

    /**
     * constructor for SpecializeVisit
     * @param doctorName name of the doctor as String
     * @param dateOfVisit date of visit by patient as LocalDate
     * @param time time spent in appointments
     */
    public SpecializeVisit(String doctorName, LocalDate dateOfVisit, int time) {
        super(doctorName, dateOfVisit);

        this.fee = calculateFee(time);
    }

    /**
     * Another constructor for SpecializeVisit
     * @param visitData all the visitData being read
     *                  from file is passed here to create SpecializeVisit
     */
    public SpecializeVisit(String visitData) {

        String[] formattedVisit = visitData.split(",");
        String doctorName = formattedVisit[0];
        LocalDate dateOfVisit = LocalDate.parse(formattedVisit[1]);
        double fee = Double.parseDouble(formattedVisit[2]);
        this.doctorName = doctorName;
        this.dateOfVisit = dateOfVisit;
        this.fee = fee;
    }

    /**
     *  calculates the fee of the doctor and
     *  return the calculated fee
     * @param minutes appointment time in double
     * @return fee calculated fee of the doctor
     */
    public double calculateFee(double minutes){
        double left = minutes-10;

        if(minutes <= 10){
            return 100;
        }
        else{
            if(left <= 15){

                return 150;
            }
            else {
                double multiply = Math.ceil(left / 15);
                return multiply * 50 + 100;
            }
        }
    }

    /**
     * accessor method for fee
     * @return fee fee of doctor as double
     */
    public double getFee(){
        return fee;
    }

    /**
     * accessor method for visitType
     *
     * @return String type of visit name
     */
    public String getVisitType(){
        return "Specialize";
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
