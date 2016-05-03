
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Oliver
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "instance.txt";
        Map<Integer, ArrayList<Integer>> literals = new ConcurrentHashMap();
        ArrayList<Integer> values = new ArrayList();
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            int lineNum = 0;
            while (line != null) {
                ArrayList<Integer> literal = new ArrayList();

                for (String retval : line.split(",")) {
                    int v = Integer.parseInt(retval.replaceAll("\\s", ""));
                    literal.add(v);
                }
                line = bufferedReader.readLine();
                literals.put(lineNum++, literal);
//                System.out.println(lineNum);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("hey");
        }

        int[] elements = new int[1001];
        boolean cont = true;

        while (cont) {
            cont = false;
            for (int x : elements) {
                x = 0;
            }

            for (ArrayList<Integer> x : literals.values()) {
                for (int y : x) {
                    if (y >= 0) {
                        elements[500 + y]++;
                    } else {
                        elements[Math.abs(y)]++;
                    }
                }
            }
            
            int count = 0;
            int elementNum = 0;
            for (int i = 0; i < elements.length; i++) {
                if (count < elements[i] && elements[i] != 0) {
                    count = elements[i];
                    elementNum = i;
                }
            }

            if (elementNum < 500) {
                elementNum = elementNum * -1;
            } else {
                elementNum = elementNum - 500;
            }
            values.add(elementNum);

            for (ArrayList<Integer> x : literals.values()) {
                int opp = elementNum * -1;
                boolean remove = false;
                boolean clean = false;
                for (int y : x) {
                    if (y == elementNum) {
                        remove = true;
                    }
                    if (y == opp) {
                        clean = true;
                    }
                }
                if (remove) {
                    x.clear();
                }
                if (clean) {
                    for (int i = 0; i < x.size(); i++) {
                        if (opp == x.get(i)) {
                            x.remove(i);
                        }
                    }
                }
            }

            for (ArrayList<Integer> x : literals.values()) {
                for (int y : x) {
                    int opp = elementNum * -1;
                    if (y == opp) {
                        System.out.println("hello");
                    }
                }
            }

            int empty = 0;
            for (ArrayList<Integer> x : literals.values()) {
                if (x.isEmpty()) {
                    empty++;
                }
            }
            System.out.println(empty + "\t" + values.size());
            if (values.size() > 25) {
                for (int i = 0; i < values.size(); i++) {
                    for (int j = i + 1; j < values.size(); j++) {
                        if (values.get(i) == values.get(j)) {
                            System.out.println("Same Value");
                        } else if (values.get(j) == 0) {
                            System.out.println("Zero");
                        } else if (values.get(i) == values.get(j) * -1) {
                            System.out.println("Negative");
                        }
                    }
                }
            }

            if (values.size() > 500) {
                System.out.println("huh");
            }

            for (ArrayList<Integer> x : literals.values()) {
                for (int y : x) {
                    if (y != 0) {
                        cont = true;
                        break;
                    }
                }
            }
        }
    }
}
