package com.sc.fe.analyze.to;


public class LayersInformation{
   public Integer layerSequence;
   public String fileNamee;
   public String polarityy;

    public LayersInformation(String layer, String fileName, String polarity) {
       this.layerSequence=Integer.parseInt(layer);
       this.fileNamee=fileName;
       this.polarityy=polarity;
    }
   


}