package main.other;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwoSum1 {

    public static void main(String[] args) {
        ArrayList<Long> array = readNumbersArrayFromFile();
        System.out.println("Array read");

        // SORT THE ARRAY
        Collections.sort(array);

        // WE DO USING ARRAYS INSTEAD OF NATIVE JAVA HASHMAP IN ORDER TO SPEED
        // UP THE COMPUTATION
        int counter = 0;

        for (int sum = -10000; sum <= 10000; sum++) {
            int start = 0;
            int end = array.size() - 1;

            boolean found = false;

            while (!found && start < end) {
                if (array.get(start) + array.get(end) == sum) {
                    if (array.get(start).longValue() != array.get(end)
                            .longValue()) {
                        found = true;
                    }
                } else if (array.get(start) + array.get(end) > sum) {
                    end--;
                } else if (array.get(start) + array.get(end) < sum) {
                    start++;
                }
            }
            System.out.println("sum: " + sum + " counter: " + counter);

            if (found) {
                counter++;
            }
        }

        System.out.println("*** COUNTER => " + counter + " ***");
    }

    /**
     * Reads the Long array to be used as input for the assignment
     *
     * @return A Long array
     */
    private static ArrayList<Long> readNumbersArrayFromFile() {
        ArrayList<Long> longArray = new ArrayList<Long>();

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("src/main/resources/other/algo1-programming_prob-2sum.txt");

            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                longArray.add(Long.valueOf(line));
            }

            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TwoSum1.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TwoSum1.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(TwoSum1.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }

        return longArray;
    }
}
