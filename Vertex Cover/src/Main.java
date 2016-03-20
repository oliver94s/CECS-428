/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olive_000
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

   public static void main(String[] args) throws IOException {
      String fileName = "graph2.txt";
      Map<Integer, Vertex> vert = new ConcurrentHashMap();

      int vertexCovered = 0;
      try {
         FileReader fileReader = new FileReader(fileName);

         BufferedReader bufferedReader = new BufferedReader(fileReader);

         String line = bufferedReader.readLine();

         for (String retval : line.split("x")) {

            ArrayList<Integer> neighbors = new ArrayList();

            String[] vertex = retval.split(":");
            int v = Integer.parseInt(vertex[0].replaceAll("\\s", ""));

            for (String n : vertex[1].split(",")) {
               n = n.replaceAll("\\s", "");
               neighbors.add(Integer.parseInt(n));
            }
            vert.put(v, new Vertex(v, neighbors));
         }
      }
      catch (FileNotFoundException ex) {
         System.out.println("hey");
      }

      //WHERE THE CODING BEINGS
      int count = 0;
      //Sets up the degrees of the vertex
      for (Vertex x : vert.values()) {
         ArrayList<Integer> num = x.getNum();
         for (int i : num) {
            x.setNeighbor(vert.get(i));
            x.incDeg();
//            count++; // The number of edges in the entire graph
         }
      }
//      count = 2;
      boolean cont = true;
      while (cont) {
         boolean degOne = false;
         for (Vertex x : vert.values()) {
            if (x.getDeg() == 1) { // When this is Vertex degree == 1
               for (Vertex y : x.mNeighbor.values()) { // Look at its parent
//                  count -= x.getDeg(); // decrement count by current num of Deg
//                  count -= y.getDeg(); // decrement count by parent num of deg
                  vertexCovered++; // a vertex will be covered
                  y.coverVertex(); // go to parent and cover
                  y.decDeg(); // go to parent and initialize the removal of edges
               }
               degOne = true;
            }
         }
//         System.out.println(vertexCovered);
         if (!degOne) {
            int max = 0;
            int degCount = 0;
            int maxVertex;
            //find the highest degree
            for (Vertex x : vert.values()) {
               for (Vertex y: x.mNeighbor.values()){
                  if (y.degree == 2){
                     degCount++;
                  }
               }
               if (max < degCount){
                  max = degCount;
                  maxVertex = x.mVertex;
               }
               degCount = 0;
//               if (max < x.getDeg()) {
//                  max = x.getDeg();
//               }
            }
//            System.out.println(max);
            //finds the first instance of Max Deg and kills it
            for (Vertex x : vert.values()) {
               if (x.mVertex == max) {
                  vertexCovered++;
                  x.decDeg();
                  x.coverVertex();
                  break;
               }
            }
         }
         for (Vertex x : vert.values()) {
            if (x.degree != 0) {
               cont = true;
               break;
            }
            cont = false;
         }
         System.out.println("Covered: " + vertexCovered);
//         System.out.println("# of Edges: " + count);
      }

      
      PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
      
      for (Vertex x : vert.values()) {
         if (x.isCovered()) {
            writer.print(x.mVertex + "x");
         }
      }
      
      writer.close();

      System.out.println("Done");
      System.out.println("Vertex Covered: " + vertexCovered);
   }
}
