public class Transition {
    public static int LEFT = -1;
    public static int RIGHT = 1;

    public char input;
    public char write;
    public int direction;
    public int target;

    public Transition(char input, char write, int direction, int target) {
        this.input = input;
        this.write = write;
        this.direction = direction;
        this.target = target;
    }
}
