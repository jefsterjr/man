package org.man.fota.util.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class VehicleNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(VehicleNotFoundException.class);

    private static final String DEFAULT_MESSAGE = "Vehicle not found!";

    public VehicleNotFoundException(String message) {
        super(message);
        logger.error(message);
    }

    public VehicleNotFoundException() {
        super(DEFAULT_MESSAGE);
        logger.error(DEFAULT_MESSAGE);
    }
}
