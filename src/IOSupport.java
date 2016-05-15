import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * We save patient data and
 * visit data for each patient through this
 * class
 *
 * @author Shibbir
 * @version 1.0
 *
 */
public class IOSupport {

	/**
	 * saveData() method saves data to file
	 * @param object object to be saved in file
	 * @param fileName save data to specified file type is String
	 * @throws IOException input output exception
	 * 					   while saving data to file
     */
	public static void saveData(Object object, String fileName)throws IOException{

		//File file = new File(fileName);
		//file.delete();
		FileWriter fileWriter = new FileWriter(fileName,true);
		//BufferedWriter bw = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(fileWriter);


		printWriter.println(object.toString());
		//System.out.println(object.toString());
		if(fileWriter != null) fileWriter.close();
		if(printWriter != null) printWriter.close();
	}
}
