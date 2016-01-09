/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sylwia
 */
public class Main {

    static Map<Integer, ArrayList<Submission>> trainMap = new HashMap<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //reading from train.csv to ArrayList of objects
        ArrayList<Submission> trainSubmissions = CsvFileReader.readCsvFile("train.csv");

        //creating map personId -> their NOT NULL submissions     
        for (Submission trainSub : trainSubmissions) {
            if (trainMap.get(trainSub.getPersonId()) != null) {
                trainMap.get(trainSub.getPersonId()).add(trainSub);
            } else {
                ArrayList<Submission> arSub = new ArrayList<>();
                arSub.add(trainSub);
                trainMap.put(trainSub.getPersonId(), arSub);
            }
        }

        //creating map personId -> their similarity to other persons
        //basing on commonly rated movies (union)
        Map<Integer, ArrayList<Similarity>> similarityMap = new HashMap<>();
        for (int personId1 : trainMap.keySet()) {
            similarityMap.put(personId1, new ArrayList<>());
            for (int personId2 : trainMap.keySet()) {
                double simil = calculateSimilarity(personId1, personId2);
                similarityMap.get(personId1).add(new Similarity(personId1, personId2, simil));
            }
        }

        //reading from task.csv to ArrayList of objects
        ArrayList<Submission> nullSubmissions = CsvFileReader.readCsvFile("task.csv");

        //here ratings will be calulated for NULLs in train.csv
        for (Submission nullSub : nullSubmissions) {
            int movieId = nullSub.getMovieId();
            double rate = 0;
            double totalSim = 0;

            //get weighted value of similarity times rate for every person that
            //rated the movie
            for (Similarity simil : similarityMap.get(nullSub.getPersonId())) {
                for (Submission trainSub : trainMap.get(simil.getPersonId2())) {
                    if (trainSub.getMovieId() == movieId) {
                        totalSim += simil.getValue();
                        rate += simil.getValue() * trainSub.getRate();
                    }
                }
            }
            rate = rate / totalSim;
            Long L = Math.round(rate);
            int i = L.intValue();
//            System.out.println("Rate for movie " + movieId + " is: " + rate + " or " + i);
            nullSub.setRate(i);
//            sub.setRate(ThreadLocalRandom.current().nextInt(0, 5 + 1));
        }

        CsvFileWriter.writeCsvFile("submission.csv", nullSubmissions);
    }

    private static double calculateSimilarity(int pers1, int pers2) {
        double simil = 0;
        int total = 0;

        for (Submission sub1 : trainMap.get(pers1)) {
            for (Submission sub2 : trainMap.get(pers2)) {
                if (sub1.getMovieId() == sub2.getMovieId()) {
                    ++total;
                    simil += (sub1.getRate() - sub2.getRate()) * (sub1.getRate() - sub2.getRate());
                }
            }
        }
        
        //we have to normalize similarity value between 1 and close to 0
        //dividing by total number of movies compared gives unit distance
        //so it doesn't matter how many movies were in common
        //for always the same number of movies division would not be needed
        simil = 1 / (1 + Math.sqrt(simil/total));
        if(pers1==70) {
            System.out.println("Total for person: " + pers2 + " is: " + total);
            System.out.println("    Similarity: " + simil);
        }
//        System.out.println("Similarity based on " + total + " movies: person " + pers1 + " person " + pers2 + " is: " + simil);
        return simil;
    }

}
