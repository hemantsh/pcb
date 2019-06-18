//package com.sc.fe.analyze.data.entity;
//
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;
//
//import io.swagger.annotations.ApiModel;
////Todo This class is not going to be used anymore.....................................
//
//@ApiModel(value = "ExtensionFileType", description = "Table Structure of ExtensionFiletype")
//@Table(value = "extension_file")
//public class ExtensionFileType {
//
//    @PrimaryKey
//    private ExtensionFilePK key;
//
//    private String extension;
//    private String file;
//
//    /**
//     * Gets the Extension.
//     *
//     * @return the extension
//     */
//    public String getExtension() {
//        return extension;
//    }
//
//    /**
//     * Sets the Extension in the extension variable.
//     *
//     * @param extension Sets the extension.
//     */
//    public void setExtension(String extension) {
//        this.extension = extension;
//    }
//
//    /**
//     * Gets the fileType.
//     *
//     * @return the fileType
//     */
//    public String getFile() {
//        return file;
//    }
//
//    /**
//     * Sets the fileType in the file variable.
//     *
//     * @param file Sets the file.
//     */
//    public void setFile(String file) {
//        this.file = file;
//    }
//
//    /**
//     * Gets the ExtensionId.
//     *
//     * @return the extensionId
//     */
//    public int getExtensionId() {
//        return getKey().getExtensionId();
//    }
//
//    /**
//     * Sets the ExtensionId in the PrimaryKey.
//     *
//     * @param extensionId Sets the extensionId.
//     */
//    public void setExtensionId(int extensionId) {
//        this.getKey().setExtensionId(extensionId);
//    }
//
//    /**
//     * Gets the FiletypeId.
//     *
//     * @return the fileTypeId
//     */
//    public int getFiletypeId() {
//        return getKey().getFiletypeId();
//    }
//
//    /**
//     * Sets the FiletypeId in the PrimaryKey.
//     *
//     * @param filetypeId Sets the filetypeId.
//     */
//    public void setFiletypeId(int filetypeId) {
//        this.getKey().setFiletypeId(filetypeId);
//    }
//
//    /**
//     * Gets the Primary Key, if null then initialize the new PrimaryKey.
//     *
//     * @return the primary key.
//     */
//    public ExtensionFilePK getKey() {
//        if (key == null) {
//            key = new ExtensionFilePK();
//        }
//        return key;
//    }
//
//    /**
//     * Set the ExtensionFileKey.
//     *
//     * @param key Sets the key.
//     */
//    public void setKey(ExtensionFilePK key) {
//        this.key = key;
//    }
//}
