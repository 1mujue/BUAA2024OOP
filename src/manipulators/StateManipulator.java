package manipulators;

import entity.State;

/**
 * &#064;Classname StateManipulator
 * &#064;Description  TODO
 * &#064;Date 2024/9/4 21:47
 * &#064;Created MuJue
 */
public class StateManipulator {
    private static final StateManipulator stateManipulator = new StateManipulator();
    private StateManipulator(){;}
    public static StateManipulator getInstance(){
        return stateManipulator;
    }
    private final State state = State.getInstance();
    public State getState(){
        return state;
    }
    public String getStateId(){
        return state.getId();
    }
    public void setStateId(String id){
        state.setId(id);
    }
    public void setStatePermission(String permission){
        state.setPermission(permission);
    }
    public String getStatePermission(){
        return state.getPermission();
    }
    public void clearState(){
        state.setId(null);
        state.setPermission(null);
    }
}
