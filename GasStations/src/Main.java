
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
      while (cont) {
         boolean degOne = false;
         for (Node x : nodes.values()) {
            if (x.getDeg() == 1) { // When this is Vertex degree == 1
               for (Node y : x.mNeighbor.values()) { // Look at its parent
                  vertexCovered++; // a vertex will be covered
                  y.coverVertex(); // go to parent and cover
                  y.decDeg(); // go to parent and initialize the removal of edges
               }
               degOne = true;
            }
         }
         if (!degOne) {
            int max = 0;
            int degCount = 0;
            int maxVertex = 0;
            //find the highest degree
            for (Node x : nodes.values()) {
               for (Node y : x.mNeighbor.values()) {
                  if (y.degree == 2) {
                     degCount++;
                  }
               }
               if (max < degCount) {
                  max = degCount;
                  maxVertex = x.mId;
               }
               degCount = 0;
            }
            vertexCovered++;
            nodes.get(maxVertex).decDeg();
            nodes.get(maxVertex).coverVertex();
         }
         for (Node x : nodes.values()) {
            if (x.within || x.covered) {
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
      for (Node z : nodes.values()) {
         for (int x : z.mWeight) {
            if (x == 30) {
               System.out.println(z.mId + " " + z.degree);
               count++;
            }
         }
      }
      System.out.println(count);

   }

}
