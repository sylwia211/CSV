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
public class Similarity {
    
    int id1;
    int id2;
    double value;

    public Similarity(int id1, int id2, double value) {
        this.id1 = id1;
        this.id2 = id2;
        this.value = value;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getPersonId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}
