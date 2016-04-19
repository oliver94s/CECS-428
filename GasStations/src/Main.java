
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

   public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
      Map<Integer, Node> nodes = new ConcurrentHashMap();

      int vertexCovered = 0;
      String fileName = "graph.txt";
      int count = 0;
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
               int weight = Integer.parseInt(pieces[1].replaceAll("\\s", ""));
               if (weight < 31) {
                  roads.add(Integer.parseInt(pieces[0].replaceAll("\\s", "")));
                  weights.add(weight);
               }
            }
            nodes.put(n, new Node(n, roads, weights));

            line = bufferedReader.readLine();
         }
      }
      catch (FileNotFoundException ex) {
         ex.printStackTrace();
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      for (Node z : nodes.values()) {
         ArrayList<Integer> num = z.getNum();
         for (int i : num) {
            z.setNeighbor(nodes.get(i));
         }
      }

      boolean cont = true;
      int prevTot = 0;

//      nodes.get(641).branchOut(nodes.get(641), 0);
//
//      for (Node x : nodes.values()) {
//         if (!x.within && !x.covered) {
//            System.out.println(x.mId);
//         }
//      }

      while (cont) {
         int total = 0;

         for (Node x : nodes.values()) {
            if (!x.covered && !x.within) {
               total++;
            }
         }
         int diff = prevTot - total;

         int maxCover = 0;
         int maxCoverId = 0;

         for (Node x : nodes.values()) {
            if (!x.covered && !x.within) {
               int coverCount = 0;
               x.temp = true;
               x.testBranchOut(x, 0);
               
               for (Node y : nodes.values()) {
                  if (y.temp) {
                     coverCount++;
                  }
               }

               if (coverCount > maxCover) {
                  maxCover = coverCount;
                  maxCoverId = x.mId;
               }

               for (Node y : nodes.values()) {
                  if (y.temp) {
                     y.temp = false;
                  }
               }
            }
         }

         System.out.println(total + " " + diff);
         prevTot = total;

         nodes.get(maxCoverId).covered = true;
//         System.out.println("***********************************");
//         System.out.println("Node: " + nodes.get(maxCoverId).mId);
//         System.out.println("***********************************");
         nodes.get(maxCoverId).branchOut(nodes.get(maxCoverId), 0);
         vertexCovered++;

//         for (Node x: nodes.values()){
//            if (!x.within && !x.covered){
//               System.out.println(x.mId);
//            }
//         }
         
         for (Node x : nodes.values()) {
            if (!x.within && !x.covered) {
               cont = true;
               break;
            }
            cont = false;
         }
      }

      PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

      for (Node x : nodes.values()) {
         if (x.isCovered()) {
            writer.print(x.mId + "x");
         }
      }

      writer.close();

      System.out.println("Done");
      System.out.println("Vertex Covered: " + vertexCovered);
   }

}
