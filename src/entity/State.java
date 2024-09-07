package entity;

import commands.BaseCommand;
import enums.PERMISSION;
import exceptions.ValidationException;
import validators.PermissionValidator;

/**
 * &#064;Classname State
 * &#064;Description  TODO
 * &#064;Date 2024/8/17 15:22
 * &#064;Created MuJue
 */
public class State {
    private String id = null;
    private PERMISSION permission = null;
    private static final State state = new State() ;
    private State(){;}

    public static State getInstance(){
        return state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PERMISSION getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = PERMISSION.getInstance(permission);
    }
    public void setPermission(PERMISSION permission){
        this.permission = permission;
    }

    public void validate(BaseCommand command) throws ValidationException{
        PermissionValidator.getInstance().setCurrentPermission(permission);
        command.validate();
    }
}
