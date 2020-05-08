public class Decompression {
    private static final int R = 256; // the size of default ASCII table
    private static final int W = 12; // the size of output chunk

    /**
     * Decompress the input file and write a new file
     *
     * @param  args input file name and output file name
     */
    public static void decompression(String[] args) {
        var in = new BinaryIn(args[0]); // Input file pipeline
        var out = new BinaryOut(args[1]); // Output file pipeline

        // Using string list to store increasing ASCII table to improve
        // searching speed
        String[] fakeDic = new String[4096];
        int i;

        // store the default ASCII table entries to array list
        for (i = 0; i < R; i++)
            fakeDic[i] = "" + (char) i;
        fakeDic[i++] = " ";

        // read 12 bits from input file once
        int codeWord = in.readInt(W);
        String value = fakeDic[codeWord];

        while (true) {
            out.write(value);
            codeWord = in.readInt(W);
            if (codeWord == R) break;

            String s = fakeDic[codeWord];
            if (i == codeWord)
                s = value + value.charAt(0);
            if (i < 4096) {
                fakeDic[i++] = value + s.charAt(0);
                // refresh the string list if it is full
                if (i == 4096) {
                    fakeDic = new String[4096];
                    for (i = 0; i < R; i++)
                        fakeDic[i] = "" + (char) i;
                    fakeDic[i++] = " ";
                }
            }
            value = s;
        }

        out.close();
    }

    public static void main(String[] args) {
        Decompression.decompression(args);
    }
}