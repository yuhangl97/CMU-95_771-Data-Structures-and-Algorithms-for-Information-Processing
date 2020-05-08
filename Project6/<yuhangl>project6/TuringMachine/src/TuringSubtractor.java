public class TuringSubtractor {
    public static void main(String args[]) {
        Turing machine2 = new Turing(7);    // A two state machine
        State s0 = new State(0);
        State s1 = new State(1);
        State s2 = new State(2);
        State s3 = new State(3);
        State s4 = new State(4);
        State s5 = new State(5);
        State s6 = new State(6);

        s0.addTransition(new Transition('0','B',Transition.RIGHT,1));
        s0.addTransition(new Transition('1','B',Transition.RIGHT,5));
        machine2.addState(s0);                 // Add the state to the machine

        s1.addTransition(new Transition('0','0',Transition.RIGHT,1));
        s1.addTransition(new Transition('1','1',Transition.RIGHT,2));
        machine2.addState(s1);                 // Add the state to the machine

        s2.addTransition(new Transition('1','1',Transition.RIGHT,2));
        s2.addTransition(new Transition('0','1',Transition.LEFT,3));
        s2.addTransition(new Transition('B','B',Transition.LEFT,4));
        machine2.addState(s2);                 // Add the state to the machine

        s3.addTransition(new Transition('0','0',Transition.LEFT,3));
        s3.addTransition(new Transition('1','1',Transition.LEFT,3));
        s3.addTransition(new Transition('B','B',Transition.RIGHT,0));
        machine2.addState(s3);                 // Add the state to the machine

        s4.addTransition(new Transition('1','B',Transition.LEFT,4));
        s4.addTransition(new Transition('0','0',Transition.LEFT,4));
        s4.addTransition(new Transition('B','0',Transition.RIGHT,6));
        machine2.addState(s4);                 // Add the state to the machine

        s5.addTransition(new Transition('0','B',Transition.RIGHT,5));
        s5.addTransition(new Transition('1','B',Transition.RIGHT,5));
        s5.addTransition(new Transition('B','B',Transition.RIGHT,6));
        machine2.addState(s5);                 // Add the state to the machine

        String inTape = "0000001000";     // Define some input

        System.out.println(inTape);

        String outTape = machine2.execute(inTape);  // Execute the machine

        System.out.println(outTape);  // Show the machine’s output
    }
}
