package com.sc.fe.analyze.to;

public class TurnTimeQuantity {
	
	public TurnTimeQuantity() {}
	
	public TurnTimeQuantity(int turnTime, int quantity) {
		super();
		this.turnTime = turnTime;
		this.quantity = quantity;
	}
	
	private int turnTime;
	private int quantity;
	public int getTurnTime() {
		return turnTime;
	}
	public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
