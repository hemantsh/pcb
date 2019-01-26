package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PCBInformation implements Serializable {

	private static final long serialVersionUID = -1490081190840668824L;
	
	private String serviceType;
	private String zipFileName;
	private String zipFileSize;
	private int layers;
    private String pcbClass;
    private String boardType;
    private boolean itar;
    private List<TurnTimeQuantity> turnTimeQuantityList;
    
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getZipFileName() {
		return zipFileName;
	}
	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}
	public String getZipFileSize() {
		return zipFileSize;
	}
	public void setZipFileSize(String zipFileSize) {
		this.zipFileSize = zipFileSize;
	}
	public int getLayers() {
		return layers;
	}
	public void setLayers(int layers) {
		this.layers = layers;
	}
	public String getPcbClass() {
		return pcbClass;
	}
	public void setPcbClass(String pcbClass) {
		this.pcbClass = pcbClass;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public boolean isItar() {
		return itar;
	}
	public void setItar(boolean itar) {
		this.itar = itar;
	}
	
	public void addTurnTimeQuantity(TurnTimeQuantity turnTimeQuantity) {
		// TODO Auto-generated method stub
		if( this.turnTimeQuantityList == null ) {
			this.turnTimeQuantityList = new ArrayList<TurnTimeQuantity>();
		}
		this.turnTimeQuantityList.add(turnTimeQuantity);
	}
	public List<TurnTimeQuantity> getTurnTimeQuantityList() {
		return turnTimeQuantityList;
	}
	public void setTurnTimeQuantityList(List<TurnTimeQuantity> turnTimeQuantityList) {
		this.turnTimeQuantityList = turnTimeQuantityList;
	}
}
