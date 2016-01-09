/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sylwia
 */

public class CsvFileReader {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";

    //Submission attributes index
    private static final int SUBMISSION_ID_IDX = 0;
    private static final int SUBMISSION_PERSON = 1;
    private static final int SUBMISSION_MOVIE = 2;
    private static final int SUBMISSION_RATE = 3;

    public static ArrayList<Submission> readCsvFile(String fileName) {

        BufferedReader fileReader = null;
        //Create a new list of submission to be filled by CSV file data
        ArrayList<Submission> submissions = new ArrayList<>();

        try {

            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {

                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);

                if (tokens.length > 0) {

                    //Create a new submission object and fill his  data
                    if(tokens[SUBMISSION_RATE].equals("NULL")) {
                        tokens[SUBMISSION_RATE] = "-1";
                    }
                    Submission submission = new Submission(Integer.parseInt(tokens[SUBMISSION_ID_IDX]), Integer.parseInt(tokens[SUBMISSION_PERSON]), Integer.parseInt(tokens[SUBMISSION_MOVIE]), Integer.parseInt(tokens[SUBMISSION_RATE]));

                    submissions.add(submission);

                }

            }

        } catch (Exception e) {

            System.out.println("Error in CsvFileReader !!!");

            e.printStackTrace();

        } finally {

            try {

                fileReader.close();

            } catch (IOException e) {

                System.out.println("Error while closing fileReader !!!");

                e.printStackTrace();

            }

        }
        return submissions;

    }

}
