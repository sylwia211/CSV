/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

/**
 *
 * @author Sylwia
 */
public class Submission {

    private int id;
    private int person;
    private int movie;
    private int rate;

    public Submission(int id, int person, int movie, int rate) {
        this.id = id;
        this.person = person;
        this.movie = movie;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return person;
    }

    public int getMovieId() {
        return movie;
    }

    public int getRate() {
        return rate;
    }
    
    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return id + ";" + person + ";" + movie + ";" + rate;
    }

}
