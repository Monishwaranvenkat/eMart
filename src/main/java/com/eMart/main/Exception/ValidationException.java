package com.eMart.main.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String Message;
}
