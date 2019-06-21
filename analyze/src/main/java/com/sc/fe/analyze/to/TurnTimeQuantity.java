/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.to;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class TurnTimeQuantity implements Serializable{

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
        return time+":"+quantity; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String stringData() {
    	return "Time:"+time+"-Quantity:"+quantity;
    }
}
