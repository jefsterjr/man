package org.man.fileprocessor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class DirectoryNotFoundException extends Exception implements Serializable {

    Logger logger = LoggerFactory.getLogger(DirectoryNotFoundException.class);

    private static final String defaultMessage = "Directory not Found";

    public DirectoryNotFoundException(Throwable cause) {
        super(defaultMessage,cause);
        logger.error(defaultMessage, cause);
    }
}
