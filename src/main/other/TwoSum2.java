package main.other;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwoSum2 {

    public static void main(String[] args) {
        ArrayList<Integer> array = readIntegerArrayFromFile();

        int sum = 0;

        PriorityQueue<Integer> heapLow = new PriorityQueue<Integer>();
        PriorityQueue<Integer> heapHigh = new PriorityQueue<Integer>();

        for (Integer x_i : array) {
            if (heapLow.size() > 0) {
                if (x_i > -(heapLow.peek())) {
                    heapHigh.add(x_i);
                } else {
                    heapLow.add(-x_i);
                }
            } else {
                heapLow.add(-x_i);
            }

            if (heapLow.size() > heapHigh.size() + 1) {
                heapHigh.add(-heapLow.poll());
            } else if (heapHigh.size() > heapLow.size()) {
                heapLow.add(-heapHigh.poll());
            }

            sum += -heapLow.peek();
        }

        System.out.println("*** RESULT => " + sum % 10000 + " ***");
    }

    /**
     * Reads the Integer array to be used as input for the assignment
     *
     * @return A Long array
     */
    private static ArrayList<Integer> readIntegerArrayFromFile() {
        ArrayList<Integer> integerArray = new ArrayList<Integer>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/main/resources/other/Median.txt");

            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                integerArray.add(Integer.valueOf(line));
            }

            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TwoSum2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TwoSum2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(TwoSum2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return integerArray;
    }
}
