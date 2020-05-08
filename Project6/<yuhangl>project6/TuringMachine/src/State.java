import java.util.LinkedList;

public class State {
    public int ID;
    public LinkedList<Transition> transitions = new LinkedList<>();

    public State(int ID) {
        this.ID = ID;
    }

    public void addTransition(Transition newT) {
        transitions.add(newT);
    }

    public Transition jump(char input) {
        for (Transition transition : transitions) {
            if (transition.input == input)
                return transition;
        }

        return null;
    }
}
