
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
         
         while (line != null){
            
         }
      }
      catch (Exception ex) {
         System.out.println("hey");
      }

   }

   public static void main(String[] args) {

   }
}
