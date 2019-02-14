package com.sc.fe.analyze.data.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ExtensionFilePK", description = "Combined Primary Key of ExtensionFiles")
@PrimaryKeyClass
public class ExtensionFilePK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5975930711338764582L;

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = "extension_id")
    @ApiModelProperty("First Primary Key")
    private int extensionId;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name = "filetype_id")
    @ApiModelProperty("Second Primary Key")
    private int filetypeId;

    /**
     * This method gets the ExtensionId from the Database.
     *
     * @return
     */
    public int getExtensionId() {
        return extensionId;
    }

    /**
     * This method takes the extensionId and sets the extensionId in
     * the Database.
     *
     * @param extensionId set the extensionId
     */
    public void setExtensionId(int extensionId) {
        this.extensionId = extensionId;
    }

    /**
     * This method gets the FileTypeId from the Database.
     *
     * @return
     */
    public int getFiletypeId() {
        return filetypeId;
    }

    /**
     * This takes the filetypeId and sets the Database.
     *
     * @param filetypeId sets the file_type
     */
    public void setFiletypeId(int filetypeId) {
        this.filetypeId = filetypeId;
    }
}
