
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

   int mId;
   ArrayList<Integer> mNeighborNum;
   ArrayList<Integer> mWeight;
   Map<Integer, Node> mNeighbor = new ConcurrentHashMap();
   int degree;
   boolean covered = false;

   public Node(int id, ArrayList neighborNum, ArrayList weight) {
      mId = id;
      mNeighborNum = neighborNum;
      degree = 0;
      mWeight = weight;
   }

   public void output() {
      System.out.print("Vertex: " + mId + "\tNeighbors:");
      for (Node y : mNeighbor.values()) {
         System.out.print(" " + y.mId);
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

   public void setNeighbor(Node vertex) {
      mNeighbor.put(vertex.mId, vertex);
   }

   public boolean isCovered() {
      return covered;
   }

   public void coverVertex() {
      covered = true;
   }

   public void decDeg() {
      for (Node x : mNeighbor.values()) {
         x.degree--;
         x.mNeighbor.remove(mId);
         degree--;
         mNeighbor.remove(x.mId);
      }
   }
}
