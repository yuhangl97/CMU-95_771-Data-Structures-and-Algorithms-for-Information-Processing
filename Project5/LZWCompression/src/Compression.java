public class Compression {
    private static final int R = 256; // the size of default ASCII table
    private static final int W = 12; // the size of output chunk

    /**
     * Compress the input file and write a new file
     *
     * @param  args input file name and output file name
     */
    public static void compress(String[] args) {
        var in = new BinaryIn(args[0]); // Input file pipeline
        var out = new BinaryOut(args[1]); // Output file pipeline
        HashMap dict = new HashMap(); // store the increasing table entries
        int code = 0; // record the number of table entry

        // store the default ASCII table entries to HashTable
        for (; code < R; code++) {
            dict.put(code, "" + (char)code);
        }

        // use code = R as ending notation
        code++;

        String cur = null, prev = null;
        byte next = 0, temp = 0;

        try {
            cur = "" + (char)(in.readByte() & 0xFF);
            next = in.readByte();

            while (true) {
                prev = cur;

                // search the longest prefix
                while (dict.get(cur) != -1) {
                    prev = cur;
                    cur = cur + (char)(next & 0xFF);
                    temp = next;
                    next = in.readByte();
                }

                out.write(dict.get(prev), W);
                dict.put(code++, cur);
                if (code == 4096) {
                    code = 0;
                    dict = new HashMap();
                    for (; code < R; code++) {
                        dict.put(code, "" + (char) code);
                    }
                    code++;
                }
                cur = "" + (char)(temp & 0xFF);
            }
        } catch (Exception e) {
            // write the remaining data in the file
            if (dict.get(cur) != -1)
                out.write(dict.get(cur), W);
            else {
                out.write(dict.get(prev), W);
                out.write(dict.get("" + (char)(temp & 0xFF)), W);
            }
            // write ending notation at the end of file
            out.write(R, W);
            out.close();
        }
    }


    public static void main(String[] args) {
        Compression.compress(args);
    }
}