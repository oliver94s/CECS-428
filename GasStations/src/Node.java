
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class Node {

   int ID;
   ArrayList<Integer> mNeighborNum;
   Map<Integer, Node> mNeighbor = new ConcurrentHashMap();
   int degree;
   boolean covered = false;

   public Vertex(int vertex, ArrayList neighborNum) {
      mVertex = vertex;
      mNeighborNum = neighborNum;
      degree = 0;
   }

   public void output() {
      System.out.print("Vertex: " + mVertex + "\tNeighbors:");
      for (Vertex y : mNeighbor.values()) {
         System.out.print(" " + y.mVertex);
      }
      System.out.println("\tDegree: " + getDeg());
   }

   public ArrayList getNum() {
      return mNeighborNum;
   }

   public int getDeg() {
      return degree;
   }

   public void incDeg() {
      degree++;
   }

   public void setNeighbor(Vertex vertex) {
      mNeighbor.put(vertex.mVertex, vertex);
   }

   public boolean isCovered() {
      return covered;
   }

   public void coverVertex() {
      covered = true;
   }

   public void decDeg() {
      for (Vertex x : mNeighbor.values()) {
         x.degree--;
         x.mNeighbor.remove(mVertex);
         degree--;
         mNeighbor.remove(x.mVertex);
      }
   }

}