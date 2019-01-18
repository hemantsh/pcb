package com.sc.fe.analyze.to;

public class LayersInformation{
   public Integer layerSequence;
   public String fileNamee;
   public String polarityy;
   
   //For process matrix file,use this variables
   private Integer row;
   private String context;
   private String type;
   private String name;
   private String polarity;
   private String start_name,end_name,old_name;

   public LayersInformation() {
	   
   }
    public LayersInformation(String layer, String fileName, String polarity) {
       this.layerSequence=Integer.parseInt(layer);
       this.fileNamee=fileName;
       this.polarityy=polarity;
    }
    public LayersInformation(String roww,String contextt,String typee,String namee,String polarityy,
                            String startName,String endName,String oldName)
    {
       this.row=Integer.parseInt(roww);
       this.context=contextt;
       this.type=typee;
       this.name=namee;
       this.polarity=polarityy;
       this.start_name=startName;
       this.end_name=endName;
       this.old_name=oldName;
    }
    
    public String printLayers()
    {
        return layerSequence + " "+fileNamee + "  " + polarityy;
    }
    

    public String printLayerInfo() 
    {
    	return row+"  "+context+ "  "+type + "    " +name + "   "+ polarity + "   "+ start_name  + "   "+end_name+ "   "+old_name;
    }
	public Integer getLayerSequence() {
		return layerSequence;
	}
	public void setLayerSequence(Integer layerSequence) {
		this.layerSequence = layerSequence;
	}
	public String getFileNamee() {
		return fileNamee;
	}
	public void setFileNamee(String fileNamee) {
		this.fileNamee = fileNamee;
	}
	public String getPolarityy() {
		return polarityy;
	}
	public void setPolarityy(String polarityy) {
		this.polarityy = polarityy;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPolarity() {
		return polarity;
	}
	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}
	public String getStart_name() {
		return start_name;
	}
	public void setStart_name(String start_name) {
		this.start_name = start_name;
	}
	public String getEnd_name() {
		return end_name;
	}
	public void setEnd_name(String end_name) {
		this.end_name = end_name;
	}
	public String getOld_name() {
		return old_name;
	}
	public void setOld_name(String old_name) {
		this.old_name = old_name;
	}
}