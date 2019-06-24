package com.sc.fe.analyze.to;

import java.io.Serializable;

/**
 *
 * @author Hemant
 */
public class TurnTimeQuantity implements Serializable {

    private static final long serialVersionUID = 1123622830426408611L;

    public TurnTimeQuantity() {
    }
    private String time;
    private int quantity;

    public TurnTimeQuantity(String time, int quantity) {
        this.time = time;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time + ":" + quantity; //To change body of generated methods, choose Tools | Templates.
    }

    public String stringData() {
        return "Time:" + time + "-Quantity:" + quantity;
    }
}
