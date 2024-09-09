package entity.tokens;

import exceptions.ValidationException;
import interfaces.Validatable;

/**
 * &#064;Classname Token
 * &#064;Description  TODO
 * &#064;Date 2024/9/9 14:13
 * &#064;Created MuJue
 */
public abstract class Token<T> implements Validatable {
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
