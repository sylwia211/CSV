/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sylwia
 */

public class CsvFileWriter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";

    private static final String NEW_LINE_SEPARATOR = "\n";

    public static void writeCsvFile(String fileName, ArrayList<Submission> submissions) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            //Write a new submission object list to the CSV file
            for (Submission submission : submissions) {
                fileWriter.append(String.valueOf(submission.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(submission.getPersonId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(submission.getMovieId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(submission.getRate()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

}
