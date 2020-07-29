package org.man.fileprocessor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class FileException extends RuntimeException implements Serializable {

    Logger logger = LoggerFactory.getLogger(FileException.class);

    public FileException(Throwable cause) {
        super(cause);
        logger.error(cause.getMessage(), cause);
    }
}
