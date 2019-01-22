package com.sc.fe.analyze.to;

/**
 *
 * @author Hemant
 */
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

    /**
     * Default Constructor
     */
    public LayersInformation() {
	   
   }

    /**
     * Parameterized Constructor
     * @param layer - the layer to initialize
     * @param fileName - the fileName to initialize
     * @param polarity - the polarity to initialize
     */
    public LayersInformation(String layer, String fileName, String polarity) {
       this.layerSequence=Integer.parseInt(layer);
       this.fileNamee=fileName;
       this.polarityy=polarity;
    }

    /**
     * Parameterized Constructor
     * @param roww - the row to initialize
     * @param contextt - the context to initialize
     * @param typee - the type to initialize
     * @param namee - the name to initialize
     * @param polarityy - the polarity to initialize
     * @param startName - the startName to initialize
     * @param endName - the endName to initialize
     * @param oldName - the oldName to initialize
     */
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
    
    /**
     *
     * @return to print the layerSequence,fileName and polarity
     */
    public String printLayers()
    {
        return layerSequence + " "+fileNamee + "  " + polarityy;
    }
    
    /**
     *
     * @return to print the row,context,type,name,polarity,start_name,end_name and old_name of the file.
     */
    public String printLayerInfo() 
    {
    	return row+"  "+context+ "  "+type + "    " +name + "   "+ polarity + "   "+ start_name  + "   "+end_name+ "   "+old_name;
    }

    /**
     *
     * @return the layerSequence
     */
    public Integer getLayerSequence() {
		return layerSequence;
	}

    /**
     *
     * @param layerSequence - the layerSequence to set
     */
    public void setLayerSequence(Integer layerSequence) {
		this.layerSequence = layerSequence;
	}

    /**
     *
     * @return the fileName
     */
    public String getFileNamee() {
		return fileNamee;
	}

    /**
     *
     * @param fileNamee - the fileName to set
     */
    public void setFileNamee(String fileNamee) {
		this.fileNamee = fileNamee;
	}

    /**
     *
     * @return the polarity
     */
    public String getPolarityy() {
		return polarityy;
	}

    /**
     *
     * @param polarityy - the polarity to set
     */
    public void setPolarityy(String polarityy) {
		this.polarityy = polarityy;
	}

    /**
     *
     * @return the row
     */
    public Integer getRow() {
		return row;
	}

    /**
     *
     * @param row - the row to set
     */
    public void setRow(Integer row) {
		this.row = row;
	}

    /**
     *
     * @return the context
     */
    public String getContext() {
		return context;
	}

    /**
     *
     * @param context - the context to set
     */
    public void setContext(String context) {
		this.context = context;
	}

    /**
     *
     * @return the type
     */
    public String getType() {
		return type;
	}

    /**
     *
     * @param type - the type to set
     */
    public void setType(String type) {
		this.type = type;
	}

    /**
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     *
     * @param name - the name to set
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     *
     * @return the polarity
     */
    public String getPolarity() {
		return polarity;
	}

    /**
     *
     * @param polarity - the polarity to set
     */
    public void setPolarity(String polarity) {
		this.polarity = polarity;
	}

    /**
     * 
     * @return the start_name
     */
    public String getStart_name() {
		return start_name;
	}

    /**
     *
     * @param start_name - the start_name to set
     */
    public void setStart_name(String start_name) {
		this.start_name = start_name;
	}

    /**
     *
     * @return the end_name
     */
    public String getEnd_name() {
		return end_name;
	}

    /**
     *
     * @param end_name - the end_name to set
     */
    public void setEnd_name(String end_name) {
		this.end_name = end_name;
	}

    /**
     *
     * @return the old_name
     */
    public String getOld_name() {
		return old_name;
	}

    /**
     *
     * @param old_name - the old_name to set
     */
    public void setOld_name(String old_name) {
		this.old_name = old_name;
	}
}