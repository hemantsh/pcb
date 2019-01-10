package com.sc.fe.analyze.to;

public class LayersInformation{
   public Integer layerSequence;
   public String fileNamee;
   public String polarityy;
   
   //For process matrix file,use this variables
   public Integer row;
   public String context;
   public String type;
   public String name;
   public String polarity;
   public String start_name,end_name,old_name;

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


    public String toString() {
    	return row+"  "+context+ "  "+type + "    " +name + "   "+ polarity + "   "+ start_name  + "   "+end_name+ "   "+old_name;
    }
}