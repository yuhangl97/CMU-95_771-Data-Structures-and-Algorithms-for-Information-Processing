import java.io.File;

/********************************************************************************
 *  Name: Yuhang Liang
 *  Andrew ID: yuhangl
 ********************************************************************************
 *  Documentation:
 *
 *  - Command Instructions:
 *  java LZWCompression -c shortwords.txt zippedFile.txt
 *      - compress shortwords.txt to make new file named zippedFile.txt
 *  java LZWCompression -d zippedFile.txt unzippedFile.txt
 *      - decompress zippedFile.txt to make new file named unzippedFile.txt
 *  java LZWCompression -c -v shortwords.txt zippedFile.txt
 *      - compress shortwords.txt to make new file named zippedFile.txt with files
 *        information
 *  java LZWCompression -d -v zippedFile.txt unzippedFile.txt
 *      - decompress zippedFile.txt to make new file named unzippedFile.txt with
 *        files information
 *
 *  - Compress Steps:
 *  In order to compress the data, as long as the input is not over, the
 *  program will continue to perform the following operations:
 *  1. Find the longest prefix string s in the symbol table for unprocessed input;
 *  2. Output 12-bit value of s (code);
 *  3. Continue to scan the character c after s;
 *  4. Set the value of s + c (connecting s and c) in the symbol table to the next
 *     code value.
 *  If HashMap runs out of coded values, refresh HashMap and continue collecting.
 *
 * - Decompress Steps:
 *  1. Output the current string val;
 *  2. Read a code x from the input;
 *  3. Set s to the value associated with x in the symbol table;
 *  4. In the symbol table, set the next unassigned code value to val + c, where c
 *     is the first letter of s;
 *  5. Set the current string val to s.
 *  If String list runs out of coded values, refresh string list and continue collecting.
 *
 * - Efficiency: Compression Ratio = After zipped size / Before zipped size ( the less the better)
 * 1. shortwords.txt
 *  Before: 50 bytes; After: 57 bytes;
 *  Compression Ratio: 114.0 %
 *  Reason: The file is too small
 *
 * 2. CrimeLatLonXY.csv
 *  Before: 2608940, bytes; After: 1284804 bytes;
 *  Compression Ratio: 49.2 %
 *
 * 3. words.html
 *  Before: 1070450 bytes; After: 2493531 bytes;
 *  Compression Ratio: 42.9 %
 *
 * 4. 01_Overview.mp4
 *  Before: 25008838 bytes; After: 33771266 bytes;
 *  Compression Ratio: 135.0%
 ********************************************************************************/

public class LZWCompression {
    /**
     * Do compress or decompress command
     *
     * @param  -c compress file
     * @param  -d decompress file
     * @param  -v print files information
     * @param  args [-2] input file
     * @param  args [-1] output file
     */
    public static void main(String[] args) {
        try {
            // -c --> compress file
            if (args[0].equals("-c")) {
                // -v --> print file information
                if (args[1].equals("-v")) {
                    Compression.compress(new String[]{args[2], args[3]});
                    File inputFile = new File(args[2]);
                    File outputFile = new File(args[3]);
                    System.out.println("bytes read = " + inputFile.length() +
                            ", bytes written = " + outputFile.length());
                } else
                    Compression.compress(new String[]{args[1], args[2]});
            // -d --> decompress file
            } else if (args[0].equals("-d")) {
                // -v --> print file information
                if (args[1].equals("-v")) {
                    Decompression.decompression(new String[]{args[2], args[3]});
                    File inputFile = new File(args[2]);
                    File outputFile = new File(args[3]);
                    System.out.println("bytes read = " + inputFile.length() +
                            ", bytes written = " + outputFile.length());
                } else
                    Decompression.decompression(new String[]{args[1], args[2]});
            }
        } catch (Exception e) {
            System.out.println("In valid command.");
            System.out.println("Example: ");
            System.out.println("java LZWCompression -c –v shortwords.txt zippedFile.txt");
            System.out.println("java LZWCompression -c shortwords.txt zippedFile.txt");
        }
    }
}
