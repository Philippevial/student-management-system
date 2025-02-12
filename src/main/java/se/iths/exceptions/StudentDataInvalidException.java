package se.iths.exceptions;

import se.iths.error.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentDataInvalidException extends WebApplicationException {

    public StudentDataInvalidException(ErrorMessage errorMessage) {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }
}