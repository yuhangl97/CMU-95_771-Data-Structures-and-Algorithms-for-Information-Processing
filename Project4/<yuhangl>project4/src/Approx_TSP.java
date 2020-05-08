import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Approx_TSP {
    public static void main(String[] args) throws IOException {
        CrimeReader crimeReader = new CrimeReader();
        List<String> crimes = new ArrayList<>();
        WeightedGraph graph = new WeightedGraph();

        // Get the user's input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter start date");
        String startDate = in.nextLine();
        System.out.println("Enter end date");
        String endDate = in.nextLine();

        // Search crime records during corresponding time
        System.out.println();
        System.out.println("Crime records between " + startDate + " and " + endDate);
        crimeReader.initialize();
        crimes = crimeReader.getCrimes(startDate, endDate);
        for (String str : crimes)
            System.out.println(str);

        // Build the corresponding graph
        graph.graphInit(crimes);

        // Calculate Hamiltonian Cycle
        System.out.println("\nHamiltonian Cycle (not necessarily optimum):");
        ArrayList<WeightedGraph.Node> mst = graph.getMST();
        String corOfHamCycle = "";
        for (WeightedGraph.Node node : mst) {
            System.out.print(node.getLabel() + " ");
            corOfHamCycle += node.getLocation() + ",0.000000\n";
        }
        System.out.println(mst.get(0).getLabel());
        corOfHamCycle += mst.get(0).getLocation() + ",0.000000\n";
        System.out.println("Length Of cycle: " + graph.getLength(mst) + " miles\n");

        // Calculate the optimal solution
        System.out.println("\nLooking at every permutation to find the optimal solution");
        int length = crimes.size();
        int[] path = new int[length-1];
        for (int i = 0; i < length - 1; i++) {
            path[i] = i + 1;
        }
        AllSortHelper helper = new AllSortHelper(path);
        helper.allSort(0, path.length - 1);
        ArrayList<int[]> allPath = helper.getAllSortStore();
        double minLength = Double.MAX_VALUE;
        int[] minPath = null;
        for (int i = 0; i < allPath.size(); i++) {
            int[] originalPath = allPath.get(i);
            int[] curPath = new int[originalPath.length + 2];
            for (int j = 0; j < originalPath.length; j++) {
                curPath[j+1] = originalPath[j];
            }
            double current = graph.getPathDistance(curPath);
            if (current < minLength) {
                minPath = curPath;
                minLength = current;
            }
        }
        String corOfOptCycle = "";
        System.out.println("The best permutation");
        for (int node : minPath) {
            System.out.print(node + " ");
            String[] ss = (crimes.get(node)).split(",");
            corOfOptCycle += ss[8] + "," + ss[7] + "\n";
        }
        System.out.println("\n");
        System.out.println("Optimal Cycle length = " + minLength + " miles");
        System.out.println("\n");

        //Display the output to Google Earth
        System.out.println("The corresponding KML file: \n");
        String KMLFile = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
                "<Document>\n" +
                "<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n" +
                "<LineStyle>\n" +
                "<color>73FF0000</color>\n" +
                "<width>5</width>\n" +
                "</LineStyle>\n" +
                "</Style>\n" +
                "<Style id=\"style5\">\n" +
                "<LineStyle>\n" +
                "<color>507800F0</color>\n" +
                "<width>5</width>\n" +
                "</LineStyle>\n" +
                "</Style>\n" +
                "<Placemark>\n" +
                "<name>TSP Path</name>\n" +
                "<description>TSP Path</description>\n" +
                "<styleUrl>#style6</styleUrl>\n" +
                "<LineString>\n" +
                "<tessellate>1</tessellate>\n" +
                "<coordinates>\n" +
                corOfHamCycle +
                "</coordinates>\n" +
                "</LineString>\n" +
                "</Placemark>\n" +
                "<Placemark>\n" +
                "<name>Optimal Path</name>\n" +
                "<description>Optimal Path</description>\n" +
                "<styleUrl>#style5</styleUrl>\n" +
                "<LineString>\n" +
                "<tessellate>1</tessellate>\n" +
                "<coordinates>\n" +
                corOfOptCycle +
                "</coordinates>\n" +
                "</LineString>\n" +
                "</Placemark>\n" +
                "</Document>\n" +
                "</kml>\n";
        System.out.println(KMLFile);
        Approx_TSP.generateKML(KMLFile);
    }

    public static void generateKML(String data) {
        byte[] sourceByte = data.getBytes();
        if (sourceByte != null){
            try {
                File file = new File("src/PGHCrimes.kml");
                if (!file.exists()) {
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(sourceByte);
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
