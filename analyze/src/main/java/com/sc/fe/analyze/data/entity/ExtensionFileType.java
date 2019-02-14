package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ExtensionFileType", description = "Table Structure of ExtensionFiletype")
@Table(value = "extension_file")
public class ExtensionFileType {

    @PrimaryKey
    private ExtensionFilePK key;

    private String extension;
    private String file;

    /**
     * Gets the Extension.
     *
     * @return
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the Extension in the extension variable.
     *
     * @param extension sets the extension.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Gets the fileType.
     *
     * @return
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the fileType in the file variable.
     *
     * @param file sets the file.
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Gets the ExtensionId.
     *
     * @return
     */
    public int getExtensionId() {
        return getKey().getExtensionId();
    }

    /**
     * Sets the ExtensionId in the PrimaryKey.
     *
     * @param extensionId sets the extensionId.
     */
    public void setExtensionId(int extensionId) {
        this.getKey().setExtensionId(extensionId);
    }

    /**
     * Gets the FiletypeId.
     *
     * @return
     */
    public int getFiletypeId() {
        return getKey().getFiletypeId();
    }

    /**
     * Sets the FiletypeId in the PrimaryKey.
     *
     * @param filetypeId sets the filetypeId.
     */
    public void setFiletypeId(int filetypeId) {
        this.getKey().setFiletypeId(filetypeId);
    }

    /**
     * Gets the Primary Key, if null then initialize the new PrimaryKey.
     *
     * @return
     */
    public ExtensionFilePK getKey() {
        if (key == null) {
            key = new ExtensionFilePK();
        }
        return key;
    }

    /**
     * Set the ExtensionFileKey.
     *
     * @param key sets the key.
     */
    public void setKey(ExtensionFilePK key) {
        this.key = key;
    }

}
