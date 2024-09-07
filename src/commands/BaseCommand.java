package commands;

import exceptions.ExecutionException;
import exceptions.ValidationException;
import interfaces.Executable;
import interfaces.Validatable;
import utils.Outputer;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

/**
 * &#064;Classname BaseCommand
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 10:35
 * &#064;Created MuJue
 */
public abstract class BaseCommand implements Executable, Validatable {
    protected List<String> parameters;
    public List<String> getParameters() {
        return parameters;
    }
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}
