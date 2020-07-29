package org.man.fota.model.enums;

public enum ConfigurationType {

    HARDWARE, SOFTWARE;

    ConfigurationType() {

    }

    public static ConfigurationType getType(String fileName) {
        if (fileName.contains("hard")) return HARDWARE;
        return SOFTWARE;
    }
}
