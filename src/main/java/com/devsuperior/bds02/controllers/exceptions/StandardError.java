package com.devsuperior.bds02.controllers.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String message;

    public StandardError () {
    }

    public String getError () {
        return error;
    }

    public void setError (String error) {
        this.error = error;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }
}
