public class TuringDecider {
    public static void main(String args[]) {
        Turing machine3 = new Turing(16);    // A two state machine
        State s0 = new State(0);
        State s1 = new State(1);
        State s2 = new State(2);
        State s3 = new State(3);
        State s4 = new State(4);
        State s5 = new State(5);
        State s6 = new State(6);
        State s7 = new State(7);
        State s8 = new State(8);
        State s9 = new State(9);
        State s10 = new State(10);
        State s11 = new State(11);
        State s12 = new State(12);
        State s13 = new State(13);
        State s14 = new State(14);
        State s15 = new State(15);

        s0.addTransition(new Transition('0','B',Transition.RIGHT,2));
        s0.addTransition(new Transition('B','0',Transition.RIGHT,1));
        s0.addTransition(new Transition('1','0',Transition.RIGHT,1));
        machine3.addState(s0);                 // Add the state to the machine

        s1.addTransition(new Transition('1','B',Transition.RIGHT,1));
        s1.addTransition(new Transition('0','B',Transition.RIGHT,1));
        s1.addTransition(new Transition('B','0',Transition.RIGHT,12));
        machine3.addState(s1);                 // Add the state to the machine

        s2.addTransition(new Transition('0','0',Transition.RIGHT,2));
        s2.addTransition(new Transition('1','1',Transition.RIGHT,10));
        machine3.addState(s2);                 // Add the state to the machine

        s3.addTransition(new Transition('1','B',Transition.LEFT,4));
        machine3.addState(s3);                 // Add the state to the machine

        s4.addTransition(new Transition('1','B',Transition.LEFT,5));
        s4.addTransition(new Transition('B','1',Transition.LEFT,7));
        s4.addTransition(new Transition('0','B',Transition.LEFT,8));
        machine3.addState(s4);                 // Add the state to the machine

        s5.addTransition(new Transition('1','1',Transition.LEFT,5));
        s5.addTransition(new Transition('B','0',Transition.RIGHT,15));
        s5.addTransition(new Transition('0','1',Transition.LEFT,6));
        machine3.addState(s5);                 // Add the state to the machine

        s6.addTransition(new Transition('0','0',Transition.LEFT,6));
        s6.addTransition(new Transition('1','1',Transition.LEFT,6));
        s6.addTransition(new Transition('B','B',Transition.RIGHT,2));
        machine3.addState(s6);                 // Add the state to the machine

        machine3.addState(s7);                 // Add the state to the machine

        s8.addTransition(new Transition('1','B',Transition.LEFT,8));
        s8.addTransition(new Transition('0','B',Transition.LEFT,8));
        s8.addTransition(new Transition('B','0',Transition.LEFT,9));
        machine3.addState(s8);                 // Add the state to the machine

        machine3.addState(s9);                 // Add the state to the machine

        s10.addTransition(new Transition('0','0',Transition.RIGHT,13));
        s10.addTransition(new Transition('1','1',Transition.RIGHT,10));
        s10.addTransition(new Transition('B','B',Transition.LEFT,3));
        machine3.addState(s10);                 // Add the state to the machine

        machine3.addState(s11);                 // Add the state to the machine

        machine3.addState(s12);                 // Add the state to the machine

        s13.addTransition(new Transition('0','0',Transition.RIGHT,13));
        s13.addTransition(new Transition('1','1',Transition.RIGHT,13));
        s13.addTransition(new Transition('B','B',Transition.LEFT,14));
        machine3.addState(s13);                 // Add the state to the machine

        s14.addTransition(new Transition('0','B',Transition.LEFT,14));
        s14.addTransition(new Transition('1','B',Transition.LEFT,14));
        s14.addTransition(new Transition('B','0',Transition.LEFT,12));
        machine3.addState(s14);                 // Add the state to the machine

        s15.addTransition(new Transition('B','B',Transition.RIGHT,11));
        s15.addTransition(new Transition('1','B',Transition.RIGHT,15));
        machine3.addState(s15);                 // Add the state to the machine

        String inTape = "0000111";     // Define some input

        System.out.println(inTape);

        String outTape = machine3.execute(inTape);  // Execute the machine

        System.out.println(outTape);  // Show the machine’s output
    }
}
