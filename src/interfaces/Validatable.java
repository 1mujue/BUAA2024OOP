package interfaces;

import exceptions.ValidationException;

/**
 * &#064;Classname Validatable
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 18:24
 * &#064;Created MuJue
 */
public interface Validatable {
    void validate() throws ValidationException;
}
