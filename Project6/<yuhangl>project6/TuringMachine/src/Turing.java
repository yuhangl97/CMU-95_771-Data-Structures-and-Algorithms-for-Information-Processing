import java.util.Arrays;
import java.util.LinkedList;

public class Turing {
    private int stateNum;
    private int curNum = 0;
    private char[] tape = new char[100];
    private LinkedList<State> states = new LinkedList<>();

    public Turing(int stateNum) {
        this.stateNum = stateNum;
        Arrays.fill(tape, 'B');
        for (int i = 0; i < stateNum; i++)
            states.add(new State(i));
    }

    public void addState(State newS) {
        states.removeLast();
        states.add(curNum++, newS);
    }

    public String execute(String inTape) {
//        int inPointer = 0;
//        int outPointer = 0;
        int pointer = 0;
        Transition transition;
        State curState = states.getFirst();

        for (int i = 0; i < inTape.length(); i++) {
            tape[i] = inTape.charAt(i);
        }

        while (true) {
            transition = curState.jump(tape[pointer]);

            if (transition == null)
                break;
            else {
                curState = states.get(transition.target);
                tape[pointer] = transition.write;
                pointer += transition.direction;
                if (pointer < 0)
                    pointer = 0;

//                System.out.println("---------------------------------------");
//                StringBuilder result = new StringBuilder();
//                for (char c : tape) {
//                    result.append(c);
//                }
//                System.out.println(result);
//                System.out.println("pointer:" + pointer);
//                System.out.println("state: " + curState.ID);
//                inPointer += subResult.direction;
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : tape) {
            result.append(c);
        }

        return result.toString();
    }
}
