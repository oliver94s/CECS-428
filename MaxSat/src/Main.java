
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

      }
      catch (FileNotFoundException ex) {
         System.out.println("hey");
      }

      int count = 0;
      int[] elements = new int[1001];

      for (int x : elements) {
         x = 0;
      }

      for (ArrayList<Integer> x : literals.values()) {
         for (int y : x) {
            if (y >= 0) {
               elements[500 + y]++;
            }
            else {
               elements[Math.abs(y)]++;
            }
         }
      }
      int elementNum = 0;
      for (int i = 0; i < elements.length; i++) {
         if (count < elements[i]) {
            count = elements[i];
            elementNum = i;
         }
      }

      for (ArrayList<Integer> x : literals.values()) {
         if (elementNum < 500) {
            elementNum = elementNum * -1;
         }
         else {
            elementNum = elementNum - 500;
         }
         if (x.contains(elementNum)) {
            x.clear();
         }
         if (x.contains(elementNum * -1)){
            
         }
      }
      System.out.println(elementNum + " " + count);
      
      int empty = 0;
      for (ArrayList<Integer> x : literals.values()) {
         if (x.isEmpty()) {
            empty++;
         }
      }
      System.out.println(empty);
   }
}
