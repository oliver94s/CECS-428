
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
 * @author olive_000
 */
public class Main {

   public void readFile(String fileName) {
      try {
         FileReader fileReader = new FileReader(fileName);

         BufferedReader bufferedReader = new BufferedReader(fileReader);

         String line = bufferedReader.readLine();

         while (line != null) {

         }
      }
      catch (Exception ex) {
         System.out.println("hey");
      }

   }

   public static void main(String[] args) {
      Map<Integer, Node> nodes = new ConcurrentHashMap();

      int vertexCovered = 0;
      String fileName = "graph.txt";
      try {
         FileReader fileReader = new FileReader(fileName);

         BufferedReader bufferedReader = new BufferedReader(fileReader);

         String line = bufferedReader.readLine();

         while (line != null) {
            String[] node = line.split(":");
            int n = Integer.parseInt(node[0].replaceAll("\\s", ""));

            ArrayList<Integer> roads = new ArrayList();
            ArrayList<Integer> weights = new ArrayList();

            String highways = node[1].replaceAll("\\[", "");
            highways = highways.replaceAll("\\]", " ");
            String[] connection = highways.split(" ");

            for (int i = 0; i < connection.length; i++) {
               String[] pieces = connection[i].split(",");
               roads.add(Integer.parseInt(pieces[0].replaceAll("\\s", "")));
               weights.add(Integer.parseInt(pieces[1].replaceAll("\\s", "")));
            }
            
            nodes.put(n ,new Node(n, roads, weights));
            
            line = bufferedReader.readLine();
         }
         
      }
      catch (FileNotFoundException ex) {
         ex.printStackTrace();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
