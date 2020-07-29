package org.man.fileprocessor.model.enums;

public enum FileType {

    HARDWARE("H"), SOFTWARE("S");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public static FileType getType(String fileName) {
        if (fileName.contains("hard")) return HARDWARE;
        return SOFTWARE;
    }
}
